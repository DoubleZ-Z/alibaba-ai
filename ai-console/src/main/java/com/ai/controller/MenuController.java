package com.ai.controller;

import com.ai.controller.base.CommonController;
import com.ai.exception.CommonsException;
import com.ai.service.MenuService;
import com.ai.uitl.ViewResult;
import org.springframework.web.bind.annotation.*;

/**
 * Create By YANYiZHI
 * Create Time: 2025/05/19 14:47
 * Class Name: MenuController
 * Description:
 * 目录
 *
 * @author YANYiZHI
 */
@RestController
@RequestMapping("/api/menu")
public class MenuController extends CommonController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public ViewResult menu() {
        return ViewResult.ok(menuService.queryAllMenu());
    }

    @DeleteMapping("/{id}")
    public ViewResult deleteMenu(@PathVariable String id) throws CommonsException {
        return ViewResult.ok(menuService.deleteChatSession(id));
    }
}
