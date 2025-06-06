package com.ai.controller.base;

import com.ai.controller.base.err.DefaultErrorHandler;
import com.ai.uitl.ViewResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Create By YANYiZHI
 * Create Time: 2025/06/03 11:19
 * Class Name: CommonController
 * Description:
 * xx
 *
 * @author YANYiZHI
 */
public class CommonController {
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);
    private ControllerErrorHandler errorHandler;

    public CommonController() {
    }

    @ExceptionHandler({Throwable.class})
    public ViewResult onException(Throwable err, HttpServletRequest request, HttpServletResponse response) {
        log.error("[{}]:Controller error:{}", new Object[]{this.getClass(), ExceptionUtils.getMessage(err), err});
        return this.errorHandler().handleError(this, err, request, response);
    }

    private ControllerErrorHandler errorHandler() {
        if (this.errorHandler != null) {
            return this.errorHandler;
        } else {
            this.errorHandler = this.createErrorHandler();
            return this.errorHandler;
        }
    }

    protected ControllerErrorHandler createErrorHandler() {
        return new DefaultErrorHandler();
    }
}
