package com.qiyulong.testutiles;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("time filter start");
        long start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("time filter: " + (System.currentTimeMillis() - start));
        log.info("time filter finish");
    }

    @Override
    public void destroy() {
        log.info("time filter destroy");
    }
}