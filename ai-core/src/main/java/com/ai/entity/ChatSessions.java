package com.ai.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * (chat_sessions)实体类
 *
 * @author YANYIZHI
 * @description 实体类
 * @since 2025-05-09 15:46:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("chat_sessions")
public class ChatSessions extends Model<ChatSessions> implements Serializable {

    /**
     * sessionId
     */
    @TableId
    private String sessionId;
    /**
     * createdAt
     */
    private Date createdAt;
    /**
     * lastAccessedAt
     */
    private Date lastAccessedAt;
    /**
     * metadata
     */
    private String metadata;
    /**
     * deleted
     */
    private boolean deleted;

    public ChatSessions(String sessionId, String metadata) {
        this.sessionId = sessionId;
        this.metadata = metadata;
        this.createdAt = new Date();
        this.lastAccessedAt = new Date();
    }

    private void touch() {
        this.lastAccessedAt = new Date();
    }
}
