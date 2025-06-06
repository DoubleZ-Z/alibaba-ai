package com.ai.exception;

import com.ai.util.HttpStatusCode;

/**
 * Create by Levent8421
 * Date: 2022/3/17 18:05
 * ClassName: ResourceNotFoundException
 * Description:
 * 资源找不到异常
 *
 * @author levent8421
 */
public class ResourceNotFoundException extends WebException {
    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

    public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public int httpStatus() {
        return HttpStatusCode.NOT_FOUND;
    }
}
