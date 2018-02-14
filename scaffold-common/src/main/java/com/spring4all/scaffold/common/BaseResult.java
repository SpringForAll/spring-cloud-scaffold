package com.spring4all.scaffold.common;

import java.beans.Transient;
import java.io.Serializable;

/**
 * @author fangzhibin
 */
public class BaseResult<E> implements Serializable {

    public static final int SUCCESS_TYPE = 0;

    public static final int ERROR_TYPE = -1;

    public static final int FAIL_TYPE = -2;

    private int type = ERROR_TYPE;

    private String code;

    private String msg;

    private E data;

    public BaseResult() {}

    public BaseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(int type, String code, String msg) {
        this.type = type;
        this.code = code;
        this.msg = msg;
    }

    public BaseResult(int type, String code, String msg, E data) {
        this.type = type;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public static <E> BaseResult<E> success(E e) {
        return new BaseResult<>(BaseResult.SUCCESS_TYPE, "0", null, e);
    }

    public static <E> BaseResult<E> error(String code, String msg) {
        return new BaseResult<>(BaseResult.ERROR_TYPE, code, msg);
    }

    public static <E> BaseResult<E> fail(String code, String msg) {
        return new BaseResult<>(BaseResult.FAIL_TYPE, code, msg);
    }

    @Transient
    public boolean isSuccess() {
        return type == SUCCESS_TYPE;
    }

    @Transient
    public boolean isError() {
        return type == ERROR_TYPE;
    }

    @Transient
    public boolean isFail() {
        return type == FAIL_TYPE;
    }
}
