package com.ai.router;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class ModelRouter {
    private final ChatClient decisionClient;
    private final Map<String, ChatClient> modelMap;

    public ModelRouter(@Qualifier("modelMap") Map<String, ChatClient> modelMap) {
        this.modelMap = modelMap;
        this.decisionClient = modelMap.get("chat");
    }

    public ChatClient route(String input) {
        Map<String, String> routes = Map.of(
                "technology", "你是一名资深技术专家",
                "chat", "你是聊天助手"
        );

        String routeKey = determineRouteKey(input, routes.keySet());

        // 步骤2: 获取对应的系统提示词
        String systemPrompt = routes.get(routeKey);

        // 步骤3: 组合完整提示词
        String fullPrompt = systemPrompt + "\n\n用户问题：" + input;

        // 步骤4: 调用目标模型
        return modelMap.get(routeKey);
    }

    private String determineRouteKey(String input, Set<String> routeKeys) {
        String promptTemplate = """
                请根据用户问题内容，从以下选项中选择最合适的分类：
                可选分类：%s
                用户问题：%s
                只需返回分类名称，不要解释
                """;

        String prompt = String.format(promptTemplate, routeKeys, input);

        return decisionClient.prompt(prompt).call().content();
    }
}
