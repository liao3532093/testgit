package com.vsofo.automation.utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建者: liaowenjun
 * @创建时间: 2018/5/2
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2018/5/2
 * @修改描述:
 */
public class Utils {
    public static <T, D> Map<String, Object> SaveLog(String title, String name, T t, D d) {
        Map<String, Object> maps = new HashMap<>();
        maps.put("日志类型", title);
        if (t == null) {
            maps.put("添加", appendLog(d));
        } else {
            maps.put("修改前", appendLog(t));
            maps.put("修改后", appendLog(d));
        }
        maps.put("操作", name);
        return maps;
    }

    private static <T> Map<String, Object> appendLog(T t) {
        Map<String, Object> data = new HashMap<>();
        try {
            Class<?> cls = t.getClass();
            LogInfo info = cls.getAnnotation(LogInfo.class);
            List<String> keys = Arrays.asList(info.key());
            List<String> vals = Arrays.asList(info.val());
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                if (keys.contains(field.getName())) {
                    field.setAccessible(true);
                    int len = keys.indexOf(field.getName());
                    data.put(vals.get(len), field.get(t));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */
    public static String getIpConfig(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
}
