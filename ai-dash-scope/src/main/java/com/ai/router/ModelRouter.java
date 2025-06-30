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
    private final Map<String, String> routerMap;
    private final ClientRouter clientRouter = new ClientRouter();

    public ModelRouter(@Qualifier("modelMap") Map<String, ChatClient> modelMap, @Qualifier("routerMap") Map<String, String> routerMap) {
        this.modelMap = modelMap;
        this.routerMap = routerMap;
        this.decisionClient = modelMap.get("chat");
    }

    public ClientRouter route(String input) {

        String routeKey = determineRouteKey(input, routerMap.keySet());

        String systemPrompt = routerMap.get(routeKey);

        String fullPrompt = systemPrompt + "\n\n用户输入：" + input;
        clientRouter.setChatClient(modelMap.get(routeKey));
        clientRouter.setFullPrompt(fullPrompt);
        return clientRouter;
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
