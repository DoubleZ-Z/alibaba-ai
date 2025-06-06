package com.ai.exception;


import com.ai.util.HttpStatusCode;

/**
 * Create by Levent8421
 * Date: 2022/3/17 18:07
 * ClassName: BadRequestException
 * Description:
 * 非法请求异常
 *
 * @author levent8421
 */
public class BadRequestException extends WebException {
    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

    public BadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public int httpStatus() {
        return HttpStatusCode.BAD_REQUEST;
    }
}
