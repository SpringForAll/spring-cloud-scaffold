package com.spring4all.scaffold.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author fangzhibin
 */
public class BasePage<E> implements Serializable {

  private Long Total;

  private List<E> list;

  public BasePage() {
  }

  public BasePage(Long total, List<E> list) {
    Total = total;
    this.list = list;
  }

  public Long getTotal() {
    return Total;
  }

  public void setTotal(Long total) {
    Total = total;
  }

  public List<E> getList() {
    return list;
  }

  public void setList(List<E> list) {
    this.list = list;
  }
}
