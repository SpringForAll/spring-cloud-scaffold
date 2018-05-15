package com.spring4all.scaffold.logger.constant;

/**
 * scaffold log constant
 *
 * @author fangzhibin
 */
public class ScaffoldLoggerConstant {

  /**
   * the MDC name for the errorCode
   */
  public static final String ERROR_CODE_NAME = "X-HIK_ERROR_CODE";

  /**
   * the MDC name for the errorCode
   */
  public static final String TRACE_NAME = "X-HIK_TRACE";

  /**
   * span info copy from the spring cloud sleuth
   */
  public static final String PARENT_ID_NAME = "X-B3-ParentSpanId";
  public static final String TRACE_ID_NAME = "X-B3-TraceId";
  public static final String SPAN_ID_NAME = "X-B3-SpanId";

  /**
   * the prefix for the errorCode string
   */
  public static final String ERROR_CODE_PREFIX = "[";

  /**
   * the suffix for the errorCode string
   */
  public static final String ERROR_CODE_SUFFIX = "]";

  /**
   * the prefix for the trace string
   */
  public static final String TRACE_PREFIX = "<";

  /**
   * the suffix for the trace string
   */
  public static final String TRACE_SUFFIX = ">";

  /**
   * the suffix for the trace string
   */
  public static final String DELIMITER = ",";

  /**
   * the space for the trace string
   */
  public static final String SPACE = " ";

}
