package com.zxk.i18n.filter;

import com.zxk.i18n.utils.TraceUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
/**
 * rpccontext 实现公用参数服务间透传
 * @since https://blog.csdn.net/u012661488/article/details/120251660
 * @since https://www.freesion.com/article/1615616215/
 * */
@Activate(group = {CommonConstants.PROVIDER,CommonConstants.CONSUMER}, order = 1)
@Slf4j
public class TraceFilter implements Filter {
 	
    @Override	
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        log.info("currentThread:{}",Thread.currentThread().getName());
        String trace = RpcContext.getContext().getAttachment("trace");
        if (StringUtils.isNotEmpty(trace)) {
            //从RpcContext里获取trace并保存
            TraceUtils.setTrace(trace);
        } else {	
            //重新设置trace, 避免信息丢失
            RpcContext.getContext().setAttachment("trace", TraceUtils.getTrace());
        }
        return invoker.invoke(invocation);	
    }	
 	
}