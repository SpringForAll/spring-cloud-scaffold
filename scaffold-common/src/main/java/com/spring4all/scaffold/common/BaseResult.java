package com.spring4all.scaffold.common;

import java.io.Serializable;

/**
 * @author fangzhibin
 */
public class BaseResult<E> implements Serializable {

  private String code;

  private String msg;

  private E data;

  public BaseResult() {
  }

  public BaseResult(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public BaseResult(String code, String msg, E data) {
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

  public E getData() {
    return data;
  }

  public void setData(E data) {
    this.data = data;
  }

}
