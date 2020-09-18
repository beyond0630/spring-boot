package com.beyond.distributedlock.common;

/**
 * @author beyond
 * @date 2020/6/10 19:13
 */
public final class ApiResult<T> {

    public static final int CODE_OK = 0;
    public static final int CODE_ERROR = -1;

    private static final ApiResult<Object> OK = new ApiResult<>(CODE_OK, "success", null);

    private int code;
    private String message;
    private T data;

    protected ApiResult() {
        this(CODE_OK, null, null);
    }

    protected ApiResult(final int code, final String message, final T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> ApiResult<T> make(final int code, final String message, final T data) {
        return new ApiResult<>(code, message, data);
    }

    public static ApiResult<Object> error(final String message) {
        return new ApiResult<>(CODE_ERROR, message, null);
    }

    public static ApiResult<Object> error(final int code, final String message) {
        return new ApiResult<>(code, message, null);
    }

    public static ApiResult<Object> ok() {
        return OK;
    }

    public static <T> ApiResult<T> ok(final T data) {
        return new ApiResult<>(CODE_OK, null, data);
    }

    public static ApiResult<Object> ok(final String message) {
        return new ApiResult<>(CODE_OK, message, null);
    }

    public static <T> ApiResult<T> ok(final String message, final T data) {
        return new ApiResult<>(CODE_OK, message, data);
    }

    public static <T> ApiResult<T> data(final T data) {
        return new ApiResult<>(CODE_OK, null, data);
    }

    public static <T> ApiResult<T> error(final T data) {
        return new ApiResult<>(CODE_ERROR, null, data);
    }
}
