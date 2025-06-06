package com.ai.exception;


import com.ai.util.HttpStatusCode;

/**
 * Create by Levent8421
 * Date: 2024/3/25 21:50
 * ClassName: ConflictException
 * Description:
 * 冲突异常
 *
 * @author levent8421
 */
public class ConflictException extends WebException {
    public ConflictException() {
    }

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflictException(Throwable cause) {
        super(cause);
    }

    public ConflictException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public int httpStatus() {
        return HttpStatusCode.CONFLICT;
    }
}
