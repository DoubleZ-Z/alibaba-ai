package com.ai.exception;

import com.ai.util.HttpStatusCode;

/**
 * Create by Levent8421
 * Date: 2024/3/25 21:49
 * ClassName: ForbiddenException
 * Description:
 * 服务被拒绝
 *
 * @author levent8421
 */
public class ForbiddenException extends WebException {
    public ForbiddenException() {
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenException(Throwable cause) {
        super(cause);
    }

    public ForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public int httpStatus() {
        return HttpStatusCode.FORBIDDEN;
    }
}
