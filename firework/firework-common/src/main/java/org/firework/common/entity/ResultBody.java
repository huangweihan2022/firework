package org.firework.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Controller统一参数返回
 */
@Data
public class ResultBody<T> implements Serializable {
    /**
     * 响应状态
     */
    private boolean status;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T result;

    public ResultBody(boolean status, String message,T result){
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public static ResultBody<?> fail(String message) {
        return new ResultBody(false, message, null);
    }

    public static ResultBody<?> ok(Object result) {
        return new ResultBody(true, null, result);
    }
}