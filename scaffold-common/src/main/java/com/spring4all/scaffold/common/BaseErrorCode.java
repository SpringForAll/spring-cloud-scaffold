package com.spring4all.scaffold.common;


/**
 * @author fangzhibin
 */
public enum BaseErrorCode {

  PARAMETER_ILLEGAL("400", "paramter illegal"),

  SYSTEM_INTERNAL_ERROR("500", "system internal error"),

  FORBIDDEN("403", "Forbidden");

  private String code;

  private String msg;

  BaseErrorCode(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }
}
