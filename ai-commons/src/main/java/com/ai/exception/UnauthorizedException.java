package com.ai.exception;

import com.ai.util.HttpStatusCode;

/**
 * Create by Levent8421
 * Date: 2024/3/25 21:50
 * ClassName: UnauthorizedException
 * Description:
 * 未授权异常
 *
 * @author levent8421
 */
public class UnauthorizedException extends WebException {
    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public int httpStatus() {
        return HttpStatusCode.UNAUTHORIZED;
    }
}
