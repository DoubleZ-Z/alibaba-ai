package com.ai.exception;

import com.ai.util.HttpStatusCode;

/**
 * Create by Levent8421
 * Date: 2024/3/25 19:41
 * ClassName: WebException
 * Description:
 * Web 相关异常
 *
 * @author levent8421
 */
public class WebException extends CommonsException {
    public WebException() {
    }

    public WebException(String message) {
        super(message);
    }

    public WebException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebException(Throwable cause) {
        super(cause);
    }

    public WebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int httpStatus() {
        return HttpStatusCode.INTERNAL_SERVER_ERROR;
    }
}
