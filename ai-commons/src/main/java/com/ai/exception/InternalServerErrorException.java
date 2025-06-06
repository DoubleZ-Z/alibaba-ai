package com.ai.exception;

import com.ai.util.HttpStatusCode;

/**
 * Create by Levent8421
 * Date: 2022/3/17 18:06
 * ClassName: InternalServerErrorException
 * Description:
 * 内部服务异常
 *
 * @author levent8421
 */
public class InternalServerErrorException extends WebException {
    public InternalServerErrorException() {
    }

    public InternalServerErrorException(String message) {
        super(message);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }

    public InternalServerErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public int httpStatus() {
        return HttpStatusCode.INTERNAL_SERVER_ERROR;
    }
}
