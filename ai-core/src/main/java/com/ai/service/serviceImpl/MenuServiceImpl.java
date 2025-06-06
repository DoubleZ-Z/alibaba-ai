package com.ai.service.serviceImpl;

import com.ai.dto.MenuDTO;
import com.ai.exception.CommonsException;
import com.ai.exception.InternalServerErrorException;
import com.ai.mapper.ChatSessionsMapper;
import com.ai.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create By YANYiZHI
 * Create Time: 2025/05/19 15:15
 * Class Name: MenuServiceImpl
 * Description:
 * menu实现
 *
 * @author YANYiZHI
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final ChatSessionsMapper sessionsMapper;

    public MenuServiceImpl(ChatSessionsMapper sessionsMapper) {
        this.sessionsMapper = sessionsMapper;
    }


    @Override
    public List<MenuDTO> queryAllMenu() {
        return sessionsMapper.selectMenuAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<MenuDTO> deleteChatSession(String id) throws CommonsException {
        final int i = sessionsMapper.deleteById(id);
        if (i == 1) {
            return sessionsMapper.selectMenuAll();
        } else {
            throw new InternalServerErrorException("delete fail!");
        }
    }
}
