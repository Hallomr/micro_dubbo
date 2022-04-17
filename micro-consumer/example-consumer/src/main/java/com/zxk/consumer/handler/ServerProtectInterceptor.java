package com.zxk.consumer.handler;import com.zxk.core.constant.Constants;import lombok.extern.slf4j.Slf4j;import org.apache.commons.lang.StringUtils;import org.springframework.beans.factory.annotation.Value;import org.springframework.stereotype.Component;import org.springframework.web.servlet.HandlerInterceptor;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;@Slf4j@Componentpublic class ServerProtectInterceptor implements HandlerInterceptor {    @Value("${gateway.check.switch}")    private boolean check;    @Override    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {        if(!check) return true;        String uri = request.getRequestURI();        String gatewaySign = request.getHeader("gateway_sign");        if(StringUtils.equals(Constants.GATEWAY_SIGN, gatewaySign)){            log.info("access gateway success uri:{}",uri);            return true;        } else {            //校验失败            response.setCharacterEncoding("UTF-8");            response.setHeader("Content-Type", "application/json;charset=UTF-8");            response.getWriter().write("Illegal request");            return false;        }    }}