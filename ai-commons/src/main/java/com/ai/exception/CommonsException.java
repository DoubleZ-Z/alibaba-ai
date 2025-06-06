package com.ai.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * Create by Levent8421
 * Date: 2024/3/19 16:38
 * ClassName: CommonsException
 * Description:
 * 通用异常
 *
 * @author levent8421
 */
public class CommonsException extends Exception {
    public CommonsException() {
    }

    public CommonsException(String message) {
        super(message);
    }

    public CommonsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommonsException(Throwable cause) {
        super(cause);
    }

    public CommonsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return ExceptionUtils.getMessage(this);
    }
}
