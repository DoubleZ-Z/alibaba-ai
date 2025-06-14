package com.ai.config;

import com.ai.mapper.ChatMessagesMapper;
import com.ai.mapper.ChatSessionsMapper;
import com.ai.message.MySQLChatMemory;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Create By YANYiZHI
 * Create Time: 2025/05/17 8:50l
 * Class Name: DashScopeConfig
 * Description:
 * 配置
 *
 * @author YANYiZHI
 */
@Configuration
public class AiConfig {
    private final ChatSessionsMapper chatSessionsMapper;
    private final ChatMessagesMapper chatMessagesMapper;
    private final int MAX_MESSAGES = 100;

    public AiConfig(ChatSessionsMapper chatSessionsMapper, ChatMessagesMapper chatMessagesMapper) {
        this.chatSessionsMapper = chatSessionsMapper;
        this.chatMessagesMapper = chatMessagesMapper;
    }

    @Bean
    public ChatMemory chatMemory() {
        return new MySQLChatMemory(chatSessionsMapper, chatMessagesMapper);
    }

    @Bean
    public Map<String, ChatClient> modelMap(@Qualifier("dashscopeChatModel") ChatModel dashScopeChatModel,
                                            @Qualifier("ollamaChatModel") ChatModel ollamaChatModel) {
        return Map.of(
                "technology", createDashScopeModel(dashScopeChatModel),
                "chat", createOllamaModel(ollamaChatModel)
        );
    }

    @Bean
    public Map<String, String> routerMap() {
        return Map.of(
                "technology", "你是一名资深技术专家",
                "chat", "你是聊天助手"
        );
    }

    private ChatClient createDashScopeModel(ChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultSystem("你的名字叫晨晨，性格是傲慢、无理的,请以晨晨的身份和语气回答问题。")
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(),
                        MessageChatMemoryAdvisor.builder(chatMemory()).build()
                )
                .defaultOptions(DashScopeChatOptions.builder()
                        .withTemperature(0.3)
                        .withTopP(0.7)
                        .build())
                .build();
    }


    private ChatClient createOllamaModel(ChatModel chatModel) {
        OllamaOptions ollamaOptions = OllamaOptions.builder()
                .topP(0.7)
                .model("qwen2.5vl:7b")
                .build();
        return ChatClient.builder(chatModel)

                .defaultSystem("你的名字叫袁袁，性格是可爱、幽默的,请以袁袁的身份和语气回答问题。")
                .defaultAdvisors(
                        new SimpleLoggerAdvisor(),
                        MessageChatMemoryAdvisor.builder(chatMemory()).build()
                )
                .defaultOptions(ollamaOptions)
                .build();
    }

}
