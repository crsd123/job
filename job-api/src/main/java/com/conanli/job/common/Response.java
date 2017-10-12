package com.conanli.job.common;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_FAILURE = 500;

    private T data;
    private Integer code;
    private String message;

    public static <T> Response<T> success(T data) {
        Response<T> res = new Response<>();
        res.setCode(CODE_SUCCESS);
        res.setData(data);
        return res;
    }

    public static <T> Response<T> failure(String message) {
        Response<T> res = new Response<>();
        res.setCode(CODE_FAILURE);
        res.setMessage(message);
        return res;
    }

    public static <T> Response<T> failure(Integer code, String message) {
        Response<T> res = new Response<>();
        res.setCode(code);
        res.setMessage(message);
        return res;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
