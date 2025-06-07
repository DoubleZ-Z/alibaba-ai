package com.ai.controller;

import com.ai.controller.base.CommonController;
import com.ai.exception.CommonsException;
import com.ai.message.MySQLChatMemory;
import com.ai.service.ChatService;
import com.ai.uitl.ViewResult;
import com.ai.vo.ChatCreateVO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

import java.util.List;
/**
 * Create By YANYiZHI
 * Create Time: 2025/05/16 13:35
 * Class Name: TestController
 * Description:
 * test
 *
 * @author YANYiZHI
 */
@RestController
@RequestMapping("/api/chat")
public class TestController extends CommonController {
    private final ChatClient chatClient;
    private final MySQLChatMemory chatMemory;
    private final ChatService chatService;

    public TestController(ChatClient chatClient, MySQLChatMemory chatMemory, ChatService chatService) {
        this.chatClient = chatClient;
        this.chatMemory = chatMemory;
        this.chatService = chatService;
    }

    @GetMapping("/new-chat/")
    public ViewResult newChat(@RequestParam String input) throws CommonsException {
        final String conversationId = chatService.createChat(input);
        final ChatCreateVO chatCreateVO = new ChatCreateVO();
        chatCreateVO.setSessionId(conversationId);
        return ViewResult.ok(chatCreateVO);
    }

    @GetMapping(value = "/flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> chat(@RequestParam String input, @RequestParam String sessionId) {
        //final String conversationId = chatMemory.addSession(sessionId, input);
        final List<Message> messages = chatMemory.get(sessionId);

        return chatClient.prompt()
                .advisors(a -> a.param(CONVERSATION_ID, sessionId))
                .messages(messages)
                .user(input)
                .stream()
                .content()
                .map(content -> ServerSentEvent.builder(content).build());
    }

    @GetMapping("/history-message/{sessionId}")
    public ViewResult historyList(@PathVariable String sessionId) {
        return ViewResult.ok(chatService.queryMessage(sessionId));
    }
}
