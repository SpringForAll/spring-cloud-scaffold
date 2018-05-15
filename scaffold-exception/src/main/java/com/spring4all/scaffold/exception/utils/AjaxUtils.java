package com.spring4all.scaffold.exception.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fangzhibin
 */
public class AjaxUtils {

  private static final String XML_HTTP_REQUEST = "XMLHttpRequest";

  public static boolean check(final HttpServletRequest request) {
    String xmlHttpRequest = request.getHeader("X-Requested-With");
    return XML_HTTP_REQUEST.equalsIgnoreCase(xmlHttpRequest);
  }
}
