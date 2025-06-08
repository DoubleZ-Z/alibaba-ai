package com.ai.router;

import lombok.Data;
import org.springframework.ai.chat.client.ChatClient;

@Data
public class ClientRouter {
    private ChatClient chatClient;
    private String fullPrompt;
}
