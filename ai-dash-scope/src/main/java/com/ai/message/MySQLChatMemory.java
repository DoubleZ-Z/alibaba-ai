package com.ai.message;

import com.ai.entity.ChatMessages;
import com.ai.entity.ChatSessions;
import com.ai.mapper.ChatMessagesMapper;
import com.ai.mapper.ChatSessionsMapper;
import com.ai.util.QuarkSnowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Create By YANYiZHI
 * Create Time: 2025/05/09 15:32
 * Class Name: MySQLChatMemory
 * Description:
 * 会话持久化
 *
 * @author YANYiZHI
 */
@Configuration
public class MySQLChatMemory implements ChatMemory {
    private final ChatSessionsMapper chatSessionsMapper;
    private final ChatMessagesMapper chatMessagesMapper;
    private final QuarkSnowflake quarkSnowflake = new QuarkSnowflake();

    public MySQLChatMemory(ChatSessionsMapper chatSessionsMapper, ChatMessagesMapper chatMessagesMapper) {
        this.chatSessionsMapper = chatSessionsMapper;
        this.chatMessagesMapper = chatMessagesMapper;
    }

    @Override
    public void add(String conversationId, List<Message> messages) {
        final List<ChatMessages> collect = messages.stream().map(message -> {
            final ChatMessages chatMessages = new ChatMessages();
            chatMessages.setMessageType(message.getMessageType().getValue());
            chatMessages.setSessionId(conversationId);
            chatMessages.setContent(message.getText());
            chatMessages.setCreatedAt(new Date());
            return chatMessages;
        }).toList();
        chatMessagesMapper.insertBatch(collect);
    }

    public String addSession(String conversationId, String input) {
        final boolean exists = chatSessionsMapper.exists(new LambdaQueryWrapper<ChatSessions>().eq(ChatSessions::getSessionId, conversationId));
        if (!exists) {
            conversationId = quarkSnowflake.nextStr();
            chatSessionsMapper.insert(new ChatSessions(conversationId, input));
        }
        return conversationId;
    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        List<ChatMessages> dbMessages = chatMessagesMapper.selectList(
                new LambdaQueryWrapper<ChatMessages>()
                        .eq(ChatMessages::getSessionId, conversationId)
                        .orderByDesc(ChatMessages::getCreatedAt)
                        .last("LIMIT " + lastN)
        );

        return dbMessages.stream()
                .map(this::convertToMessage)
                .collect(Collectors.toList());
    }

    @Override
    public void clear(String conversationId) {

    }

    private Message convertToMessage(ChatMessages dbMessage) {
        String content = dbMessage.getContent();
        MessageType messageType = MessageType.fromValue(dbMessage.getMessageType());

        return switch (messageType) {
            case ASSISTANT -> new AssistantMessage(content);
            case USER -> new UserMessage(content);
            case SYSTEM -> new SystemMessage(content);
            default -> throw new IllegalArgumentException("未知消息类型: " + messageType);
        };
    }
}
