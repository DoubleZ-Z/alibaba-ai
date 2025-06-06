package com.ai.mapper;

import com.ai.dto.MenuDTO;
import com.ai.entity.ChatSessions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (chat_sessions)数据Mapper
 *
 * @author YANYIZHI
 * @since 2025-05-09 15:46:47
 * @description mapper
*/
@Mapper
public interface ChatSessionsMapper extends BaseMapper<ChatSessions> {

    List<MenuDTO> selectMenuAll();

}
