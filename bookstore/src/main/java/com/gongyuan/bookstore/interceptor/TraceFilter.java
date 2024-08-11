package com.gongyuan.bookstore.interceptor;

import com.gongyuan.bookstore.util.constants.LoggerConstants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author: gongyuan
 * @date: 2024/8/11 11:44
 */
@Slf4j
public class TraceFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String traceId = UUID.randomUUID().toString();
        try {
            MDC.put(LoggerConstants.TRACE_ID, traceId);
            log.info(traceId);
            if (servletResponse instanceof HttpServletResponse) {
                ((HttpServletResponse) servletResponse).addHeader(LoggerConstants.TRACE_ID, traceId);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(LoggerConstants.TRACE_ID);
        }
    }
}
