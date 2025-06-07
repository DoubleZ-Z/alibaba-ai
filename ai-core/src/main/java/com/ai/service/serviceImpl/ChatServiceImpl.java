package com.ai.service.serviceImpl;

import com.ai.dto.MessageDTO;
import com.ai.entity.ChatSessions;
import com.ai.exception.CommonsException;
import com.ai.mapper.ChatMessagesMapper;
import com.ai.mapper.ChatSessionsMapper;
import com.ai.service.ChatService;
import com.ai.util.QuarkSnowflake;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create By YANYiZHI
 * Create Time: 2025/05/23 15:07
 * Class Name: ChatServiceImpl
 * Description:
 *
 * @author YANYiZHI
 */
@Service
public class ChatServiceImpl implements ChatService {
    private final ChatMessagesMapper chatMessagesMapper;
    private final ChatSessionsMapper chatSessionsMapper;
    private final QuarkSnowflake quarkSnowflake = new QuarkSnowflake();

    public ChatServiceImpl(ChatMessagesMapper chatMessagesMapper, ChatSessionsMapper chatSessionsMapper) {
        this.chatMessagesMapper = chatMessagesMapper;
        this.chatSessionsMapper = chatSessionsMapper;
    }

    @Override
    public List<MessageDTO> queryMessage(String sessionId) {
        return chatMessagesMapper.selectBySessionId(sessionId);
    }

    @Override
    public String createChat(String input) throws CommonsException {
        String conversationId = quarkSnowflake.nextStr();
        chatSessionsMapper.insert(new ChatSessions(conversationId, input));
        return conversationId;
    }
}
