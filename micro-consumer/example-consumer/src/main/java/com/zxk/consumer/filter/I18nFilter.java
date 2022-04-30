package com.zxk.consumer.filter;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.Constants;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Locale;

@Slf4j
@Order(1)
@WebFilter(filterName="i18nFilter", urlPatterns="/*")
public class I18nFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            String s = JSONObject.toJSONString(locale);
            RpcContext.getContext().setAttachment("trace",s);
        } catch (Exception e) {
            RpcContext.getContext().setAttachment("trace",Locale.getDefault());
        }finally {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}