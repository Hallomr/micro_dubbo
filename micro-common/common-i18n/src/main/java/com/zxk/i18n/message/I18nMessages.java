package com.zxk.i18n.message;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.nio.charset.StandardCharsets;
import java.util.*;
/**
 * 国际化语言工具类
 * */
@PropertySource("classpath:i18n/message*.properties")
public class I18nMessages {

    private static final Map<String, ResourceBundle> MESSAGES = new HashMap<>();

    public static String getMessage(String key, Object... params) {

        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle resourceBundle = MESSAGES.get(locale.getLanguage());
        if (null == resourceBundle) {
            synchronized (MESSAGES) {
                resourceBundle = MESSAGES.get(locale.getLanguage());
                if (null == resourceBundle) {
                    resourceBundle = ResourceBundle.getBundle("i18n/message", locale);
                    MESSAGES.put(locale.getLanguage(), resourceBundle);
                }
            }
        }

        try {
            String str = resourceBundle.getString(key);
            str = new String(str.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            if (null != params) {
                return String.format(str, params);
            }
            return str;
        } catch (Exception e) {
            return key;
        }
    }

    public static String getLocaleMessage(String key,Locale locale) {
        locale = locale != null ? locale : Locale.getDefault();
        ResourceBundle resourceBundle = MESSAGES.get(locale.getLanguage());
        if (null == resourceBundle) {
            synchronized (MESSAGES) {
                resourceBundle = MESSAGES.get(locale.getLanguage());
                if (null == resourceBundle) {
                    resourceBundle = ResourceBundle.getBundle("i18n/message", locale);
                    MESSAGES.put(locale.getLanguage(), resourceBundle);
                }
            }
        }
        try {
            String str = resourceBundle.getString(key);
            str = new String(str.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            return str;
        } catch (Exception e) {
            return key;
        }
    }

    public static Map<String, String> getI18nLocaleMap(Locale locale) {

        ResourceBundle resourceBundle = MESSAGES.get(locale.getLanguage());
        if (null == resourceBundle) {
            synchronized (MESSAGES) {
                resourceBundle = MESSAGES.get(locale.getLanguage());
                if (null == resourceBundle) {
                    resourceBundle = ResourceBundle.getBundle("i18n/message", locale);
                    MESSAGES.put(locale.getLanguage(), resourceBundle);
                }
            }
        }

        Set<String> keySet = resourceBundle.keySet();
        Map<String, String> map = new HashMap<>();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()){
            String key = it.next();
            String value = resourceBundle.getString(key);
            value = new String(value.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            map.put(key, value);
        }

        return map;
    }

    public static void flushMessage() {
        MESSAGES.clear();
    }
}