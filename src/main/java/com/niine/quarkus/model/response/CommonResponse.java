package com.niine.quarkus.model.response;

import com.niine.quarkus.model.entities.Vehicle;

public class CommonResponse<T> {
    private T data;
    private String message;
    private String code;

    public CommonResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public CommonResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
