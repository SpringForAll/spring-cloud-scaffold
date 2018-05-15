package com.spring4all.scaffold.common;

/**
 * @author fangzhibin
 */
public class AuthorizationException extends RuntimeException {

  private static final long serialVersionUID = -9065661397121333085L;

  private String code;
  private String msg;

  /**
   * @param code 异常码
   * @param message 异常信息
   */
  public AuthorizationException(String code, String message) {
    super(message);
    this.code = code;
    this.msg = message;
  }

  /**
   * @param code 异常码
   * @param message 异常信息
   * @param cause 异常原因
   */
  public AuthorizationException(String code, String message, Throwable cause) {
    super(message, cause);
    this.code = code;
    this.msg = message;
  }

  /**
   * @return 异常码
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code 异常码
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return 异常信息
   */
  public String getMsg() {
    return msg;
  }

  /**
   * @param msg 异常信息
   */
  public void setMsg(String msg) {
    this.msg = msg;
  }
}
