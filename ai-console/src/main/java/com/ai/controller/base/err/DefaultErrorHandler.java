package com.ai.controller.base.err;

import com.ai.controller.base.ControllerErrorHandler;
import com.ai.exception.*;
import com.ai.uitl.ViewResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by Levent8421
 * Date: 2024/3/25 22:01
 * ClassName: DefaultErrorHandler
 * Description:
 * Error Handler
 *
 * @author levent8421
 */
public class DefaultErrorHandler implements ControllerErrorHandler {
    public interface ExceptionHandler {
        ViewResult handle(Object controller, Throwable error, HttpServletRequest request, HttpServletResponse response);
    }

    private static class ExceptionHandlerImpl implements ExceptionHandler {
        private final HttpStatus status;
        private final int code;
        private final String msg;
        private final Object data;

        private ExceptionHandlerImpl(HttpStatus status, int code, String msg) {
            this(status, code, msg, null);
        }

        private ExceptionHandlerImpl(HttpStatus status, int code, String msg, Object data) {
            this.status = status;
            this.code = code;
            this.msg = msg;
            this.data = data;
        }

        @Override
        public ViewResult handle(Object controller, Throwable error, HttpServletRequest request, HttpServletResponse response) {
            String path = request.getServletPath();
            String method = request.getMethod();
            String errMessage = ExceptionUtils.getMessage(error);
            if (error instanceof MethodArgumentNotValidException) {
                final StringBuilder stringBuilder = getStringBuilder((MethodArgumentNotValidException) error);
                errMessage = stringBuilder.toString();
            }
            String msg = String.format("%s:%s[%s]", method, path, errMessage);
            ViewResult result = ViewResult.of(code, msg, data);
            response.setStatus(status.value());
            return result;
        }

        private static StringBuilder getStringBuilder(MethodArgumentNotValidException error) {
            BindingResult result = error.getBindingResult();
            final StringBuilder stringBuilder = new StringBuilder();
            if (result.hasErrors()) {
                List<ObjectError> errors = result.getAllErrors();
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    stringBuilder.append("{").append(fieldError.getDefaultMessage()).append("}");
                });
            }
            return stringBuilder;
        }
    }

    public DefaultErrorHandler() {
        loadHandlers(handlers);
    }

    private final Map<Class<?>, ExceptionHandler> handlers = new HashMap<>();

    @Override
    public ViewResult handleError(Object controller, Throwable error, HttpServletRequest request, HttpServletResponse response) {
        ExceptionHandler handler = handlers.get(error.getClass());
        if (handler == null) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return ViewResult.internalServerError("Unknown error:" + ExceptionUtils.getMessage(error));
        }
        return handler.handle(controller, error, request, response);
    }

    protected void loadHandlers(Map<Class<?>, ExceptionHandler> handlers) {
        handlers.put(WebException.class, new ExceptionHandlerImpl(HttpStatus.OK, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unknown error!"));
        handlers.put(BadRequestException.class, new ExceptionHandlerImpl(HttpStatus.OK, HttpStatus.BAD_REQUEST.value(), "Bad request!"));
        handlers.put(ConflictException.class, new ExceptionHandlerImpl(HttpStatus.OK, HttpStatus.CONFLICT.value(), "Conflict!"));
        handlers.put(ForbiddenException.class, new ExceptionHandlerImpl(HttpStatus.OK, HttpStatus.FORBIDDEN.value(), "Forbidden!"));
        handlers.put(InternalServerErrorException.class, new ExceptionHandlerImpl(HttpStatus.OK, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error!"));
        handlers.put(ResourceNotFoundException.class, new ExceptionHandlerImpl(HttpStatus.OK, HttpStatus.NOT_FOUND.value(), "Not found!"));
        handlers.put(UnauthorizedException.class, new ExceptionHandlerImpl(HttpStatus.OK, HttpStatus.UNAUTHORIZED.value(), "Unauthorized!"));
        handlers.put(MethodArgumentNotValidException.class, new ExceptionHandlerImpl(HttpStatus.OK, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Method Argument Not Valid!"));
    }
}
