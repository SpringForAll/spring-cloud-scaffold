package com.spring4all.scaffold.exception.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring4all.scaffold.common.BaseResult;
import com.spring4all.scaffold.common.BusinessException;
import com.spring4all.scaffold.exception.utils.AjaxUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.util.NestedServletException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fangzhibin
 */
public class ExceptionFilter implements Filter {

    private ObjectMapper objectMapper;

    public ExceptionFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Throwable exception) {
            if(request instanceof HttpServletRequest && response instanceof HttpServletResponse) {
                HttpServletRequest httpServletRequest = (HttpServletRequest) request;
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                if(exception instanceof NestedServletException) {
                    exception = ((NestedServletException) exception).getRootCause();
                }
                if(exception instanceof BusinessException) {
                    BusinessException businessException = (BusinessException) exception;
                    httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    if(AjaxUtils.check(httpServletRequest)) {
                        httpServletResponse.setHeader("content-type", "application/json;charset=UTF-8");
                        BaseResult<String> result = new BaseResult<>(BaseResult.ERROR_TYPE, businessException.getCode(), businessException.getMsg());
                        PrintWriter out = response.getWriter();
                        out.print(objectMapper.writeValueAsString(result));
                    } else {
                        httpServletResponse.setHeader("content-type", "application/json;charset=UTF-8");
                        httpServletResponse.setHeader("code", businessException.getCode());
                        httpServletResponse.setHeader("message", businessException.getMsg());
                        httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    }
                } else {
                    throw new ServletException(exception);
                }

            }
        }
    }

    @Override
    public void destroy() {

    }
}
