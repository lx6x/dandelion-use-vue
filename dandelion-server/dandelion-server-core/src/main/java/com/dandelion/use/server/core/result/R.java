package com.dandelion.use.server.core.result;

import lombok.Getter;
import lombok.Setter;

/**
 * 通用结果返回
 *
 * @author L
 * @version 1.0
 * @date 2022/06/15 10:10
 */
@Getter
@Setter
public class R<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 说明信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    R() {

    }

    R(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 未授权返回结果
     */
    public static <T> R<T> forbidden(T data) {
        return new R<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public static <T> R<T> forbidden() {
        return forbidden(null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> R<T> unauthorized() {
        return unauthorized(null);
    }

    public static <T> R<T> unauthorized(T data) {
        return new R<>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    public static <T> R<T> success(T data) {
        return new R<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回
     */
    public static <T> R<T> success() {
        return success(null);
    }

    public static <T> R<T> success(String message, T data) {
        return new R<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> R<T> failed(IResultCode errorCode) {
        return new R<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }


    /**
     * 失败返回结果
     */
    public static <T> R<T> failed() {
        return failed(ResultCode.FAIL);
    }

    public static <T> R<T> fail(String message) {
        return new R<>(ResultCode.FAIL.getCode(), message, null);
    }

    public static <T> R<T> fail(int code, String message) {
        return new R<T>(code, message, null);
    }

}
