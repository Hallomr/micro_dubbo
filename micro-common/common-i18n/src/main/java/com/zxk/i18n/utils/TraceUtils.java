package com.zxk.i18n.utils;

import com.alibaba.fastjson.JSONObject;

public class TraceUtils {
    private static final ThreadLocal<String> traceCache = new ThreadLocal<String>();

    public static String getTrace() {
        return traceCache.get();
    }

    public static Object getTraceByClazz(Class clazz){return JSONObject.parseObject(traceCache.get(),clazz);}

    public static void setTrace(String trace) {
        traceCache.set(trace);
    }

    public static void clear() {
        traceCache.remove();
    }
}