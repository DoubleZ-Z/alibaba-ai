package com.ai.uitl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by Levent8421
 * Date: 2024/3/13 17:02
 * ClassName: ViewResult
 * Description:
 * Result for view
 *
 * @author levent8421
 */
public class ViewResult implements Serializable {
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int REQUEST_TIMEOUT = 408;
    public static final int CONFLICT = 409;
    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final int NOT_IMPLEMENTED = 501;
    public static final Map<Integer, String> CODE_MSG_TABLE = new HashMap<>();

    static {
        CODE_MSG_TABLE.put(OK, "OK");
        CODE_MSG_TABLE.put(BAD_REQUEST, "BAD_REQUEST");
        CODE_MSG_TABLE.put(UNAUTHORIZED, "UNAUTHORIZED");
        CODE_MSG_TABLE.put(FORBIDDEN, "FORBIDDEN");
        CODE_MSG_TABLE.put(NOT_FOUND, "NOT_FOUND");
        CODE_MSG_TABLE.put(REQUEST_TIMEOUT, "REQUEST_TIMEOUT");
        CODE_MSG_TABLE.put(CONFLICT, "CONFLICT");
        CODE_MSG_TABLE.put(INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
        CODE_MSG_TABLE.put(NOT_IMPLEMENTED, "NOT_IMPLEMENTED");
    }

    public static ViewResult of(int code, String msg, Object data) {
        return new ViewResult(code, msg, data);
    }

    public static ViewResult of(int code, Object data) {
        String msg = CODE_MSG_TABLE.get(code);
        msg = msg == null ? String.valueOf(code) : msg;
        return new ViewResult(code, msg, data);
    }

    public static ViewResult ok() {
        return of(OK, null);
    }

    public static ViewResult ok(Object data) {
        return of(OK, data);
    }

    public static ViewResult unauthorized(String msg) {
        return of(UNAUTHORIZED, msg);
    }

    public static ViewResult forbidden(String msg) {
        return of(FORBIDDEN, msg);
    }

    public static ViewResult notFound(String msg) {
        return of(NOT_FOUND, msg);
    }

    public static ViewResult badRequest(String msg) {
        return of(BAD_REQUEST, msg, null);
    }

    public static ViewResult internalServerError(String msg) {
        return of(INTERNAL_SERVER_ERROR, msg, null);
    }

    public static ViewResult notImplemented(String msg) {
        return of(NOT_IMPLEMENTED, msg, null);
    }

    private int code;
    private String msg;
    private Object data;

    public ViewResult() {
    }

    public ViewResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
