package com.ai.dto;

import lombok.Data;

/**
 * Create By YANYiZHI
 * Create Time: 2025/05/23 14:15
 * Class Name: MessageDTO
 * Description:
 * 消息
 *
 * @author YANYiZHI
 */
@Data
public class MessageDTO {
    private String messageId;
    private String content;
    private String role;
    private String createAt;
}
