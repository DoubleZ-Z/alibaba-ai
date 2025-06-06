package com.ai.controller.base;


import com.ai.uitl.ViewResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Create by Levent8421
 * Date: 2024/3/25 21:58
 * ClassName: ControllerErrorHandler
 * Description:
 * Controller Error Handler
 *
 * @author levent8421
 */
public interface ControllerErrorHandler {
    /**
     * 处理异常
     *
     * @param controller controller
     * @param error      error
     * @param response   response
     * @param request    request
     * @return view result
     */
    ViewResult handleError(Object controller, Throwable error,
                           HttpServletRequest request, HttpServletResponse response);
}
