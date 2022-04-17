package com.zxk.gateway.filter;import com.zxk.core.constant.Constants;import com.zxk.gateway.properties.WhiteListProperties;import lombok.extern.slf4j.Slf4j;import org.apache.commons.lang.StringUtils;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.cloud.gateway.filter.GatewayFilterChain;import org.springframework.cloud.gateway.filter.GlobalFilter;import org.springframework.core.Ordered;import org.springframework.core.annotation.Order;import org.springframework.http.HttpHeaders;import org.springframework.http.HttpStatus;import org.springframework.http.server.reactive.ServerHttpRequest;import org.springframework.stereotype.Component;import org.springframework.web.server.ServerWebExchange;import reactor.core.publisher.Mono;import java.util.function.Consumer;@Component@Slf4jpublic class CustomFilter implements GlobalFilter, Ordered {    @Autowired    private WhiteListProperties whiteListProperties;    @Override    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {        String uri = exchange.getRequest().getURI().getPath();        log.info("gateway access uri:{}",uri);        //放行白名单        for (String w : whiteListProperties.getWhitelist()) {            if(StringUtils.endsWith(uri,w)){                return chain.filter(exchange);            }        }        //token校验 更新redis缓存        //设置网关标志及用户信息到header 请求进入其他服务前可通过拦截器校验是否经过网关        String token = exchange.getRequest().getHeaders().getFirst("token");        if (token==null){            /**             * 直接拒绝             */            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);            return exchange.getResponse().setComplete();        }        Consumer<HttpHeaders> httpHeaders = httpHeader -> {            httpHeader.set("gateway_sign", Constants.GATEWAY_SIGN);        };        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();        exchange.mutate().request(serverHttpRequest).build();        /**         * 通过这个过滤器，进入过滤链中的下一个过滤器         */        return chain.filter(exchange);    }    @Override    public int getOrder() {        return 0;    }}