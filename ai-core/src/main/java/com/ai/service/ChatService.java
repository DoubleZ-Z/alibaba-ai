package com.ai.service;

import com.ai.dto.MessageDTO;

import java.util.List;

/**
 * Create By YANYiZHI
 * Create Time: 2025/05/23 15:07
 * Class Name: ChatService
 * Description:
 * chat
 *
 * @author YANYiZHI
 */
public interface ChatService {

    List<MessageDTO> queryMessage(String sessionId);

    String createChat(String input);
}
