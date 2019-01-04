package com.spring4all.scaffold.resteasy.filter;

import com.spring4all.scaffold.common.AuthorizationException;
import com.spring4all.scaffold.common.BaseErrorCode;
import com.spring4all.scaffold.common.annotation.TokenIgnore;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.core.interception.jaxrs.PostMatchContainerRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * @author fangzhibin
 */
public class ScaffoldTokenRequestFilter implements ContainerRequestFilter {

  private static final Logger LOGGER = LoggerFactory.getLogger(ScaffoldTokenRequestFilter.class);

  private static final Integer EX_TOKEN_REFRESH_INTERVAL = 20 * 60;

  private Integer exTokenRefreshInterval;

  public ScaffoldTokenRequestFilter(Integer exTokenRefreshInterval) {
    this.exTokenRefreshInterval = exTokenRefreshInterval;
  }

  @Override
  public void filter(ContainerRequestContext requestContext) {
    ResourceMethodInvoker resourceMethodInvoker = ((PostMatchContainerRequestContext) requestContext)
        .getResourceMethod();
    TokenIgnore tokenIgnore = AnnotationUtils
        .getAnnotation(resourceMethodInvoker.getMethod(), TokenIgnore.class);
    if (null == tokenIgnore) {
      String token = requestContext.getHeaderString("scaffold-token");
      if (token == null) {
        LOGGER.error("Invoke restful without Token header. RequestURI: [{}]",
            requestContext.getUriInfo().getPath());
        throw new AuthorizationException(BaseErrorCode.FORBIDDEN.getCode(),
            "Token is null,please apply a token!");
      }
    }
  }
}
