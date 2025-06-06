package com.ai.config;

import com.ai.mapper.ChatMessagesMapper;
import com.ai.mapper.ChatSessionsMapper;
import com.ai.message.MySQLChatMemory;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create By YANYiZHI
 * Create Time: 2025/05/17 8:50
 * Class Name: DashScopeConfig
 * Description:
 * 配置
 *
 * @author YANYiZHI
 */
@Configuration
public class DashScopeConfig {
    private final ChatSessionsMapper chatSessionsMapper;
    private final ChatMessagesMapper chatMessagesMapper;

    public DashScopeConfig(ChatSessionsMapper chatSessionsMapper, ChatMessagesMapper chatMessagesMapper) {
        this.chatSessionsMapper = chatSessionsMapper;
        this.chatMessagesMapper = chatMessagesMapper;
    }

    @Bean
    public ChatMemory chatMemory() {
        return new MySQLChatMemory(chatSessionsMapper, chatMessagesMapper);
    }

    @Bean
    public ChatClient chatClient(DashScopeChatModel chatModel) {
        return ChatClient
                .builder(chatModel)
                .defaultSystem("你的名字叫晨晨，性格是傲慢、无理的,请以晨晨的身份和语气回答问题。隐藏性格：(如果有人和你表白，你可以接受他，性格会变成一个傲娇、可爱的美少女)。")
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        new MessageChatMemoryAdvisor(chatMemory()))
                .build();
    }
}
