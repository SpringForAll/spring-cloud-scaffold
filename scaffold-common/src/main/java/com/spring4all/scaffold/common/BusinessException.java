package com.spring4all.scaffold.common;

/**
 * @author fangzhibin
 */
public class BusinessException extends RuntimeException {

  private String code;

  private String msg;

  private Object data;

  public BusinessException(String code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }

  public BusinessException(String code, String msg, Throwable throwable) {
    super(msg, throwable);
    this.code = code;
    this.msg = msg;
  }

  public BusinessException(String code, String msg, Object data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public BusinessException(String code, String msg, Object data, Throwable cause) {
    super(cause);
    this.code = code;
    this.msg = msg;
    this.data = data;
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

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}
