package com.ai.mapper;

import com.ai.dto.MessageDTO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ai.entity.ChatMessages;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (chat_messages)数据Mapper
 *
 * @author YANYIZHI
 * @description mapper
 * @since 2025-05-09 15:46:47
 */
@Mapper
public interface ChatMessagesMapper extends BaseMapper<ChatMessages> {

    void insertBatch(@Param("list") List<ChatMessages> collect);

    List<MessageDTO> selectBySessionId(@Param("sessionId") String sessionId);
}
