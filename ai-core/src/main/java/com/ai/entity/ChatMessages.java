package com.ai.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * (chat_messages)实体类
 *
 * @author YANYIZHI
 * @since 2025-05-09 15:46:47
 * @description 实体类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("chat_messages")
public class ChatMessages extends Model<ChatMessages> implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * sessionId
     */
    private String sessionId;
    /**
     * messageType
     */
    private String messageType;
    /**
     * content
     */
    private String content;
    /**
     * createdAt
     */
    private Date createdAt;
    /**
     * deleted
     */
    private boolean deleted;
}
