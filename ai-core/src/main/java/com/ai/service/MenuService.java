package com.ai.service;

import com.ai.dto.MenuDTO;
import com.ai.exception.CommonsException;

import java.util.List;

/**
 * Create By YANYiZHI
 * Create Time: 2025/05/19 15:14
 * Class Name: MenuService
 * Description:
 * xx
 *
 * @author YANYiZHI
 */
public interface MenuService {
    List<MenuDTO> queryAllMenu();

    List<MenuDTO> deleteChatSession(String id) throws CommonsException;
}
