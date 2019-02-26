package com.vsofo.automation.utils;

import com.vsofo.automation.entity.ResponseItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @项目名称:
 * @作者: Administrator
 * @描述:
 * @SVN版本号:
 * @修改人: Administrator
 * @修改时间: 2017/4/18
 * @修改的内容:
 */
public class StringUtils {

    /***
     * 拼接数据库使用的URL
     *
     * @param datas
     * @return
     */
    public static String appendUrl(String... datas) {
        StringBuilder sb = new StringBuilder();
        for (String str : datas) {
            sb.append(str).append("|");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /***
     * 是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str != null && str.length() > 0)
            return false;
        return true;
    }

    /**
     * 字符串换行
     *
     * @param msg
     * @return
     */
    public static String wrapStr(String msg, int count) {
        if (isEmpty(msg)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int len = msg.length() % count == 0 ? msg.length() / count : msg.length() / count + 1;
        int index = 0;
        for (int i = 0; i < len; i++) {
            String str;
            if (i == len - 1) {
                str = msg.substring(index);
                sb.append(str);
            } else {
                str = msg.substring(index, index + count);
                sb.append(str).append("\n");
            }
            index += count;
        }
        return sb.toString();
    }

    /**
     * 日期向前/向后推几天
     *
     * @param munber 正数为向后，负数为向前
     * @return
     */
    public static String goToFrontDate(int munber) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(sdf.format(new Date()));
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE, munber);
            return sdf.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 日期向前/向后推几天
     *
     * @param munber 正数为向后，负数为向前
     * @param h      时
     * @param m      分
     * @param s      秒
     * @return
     */
    public static String goToFrontDate(int munber, String h, String m, String s) {
        try {
            StringBuilder sb = new StringBuilder().append("yyyy-MM-dd").append(" ")
                    .append(h).append(":").append(m).append(":").append(s);
            SimpleDateFormat sdf = new SimpleDateFormat(sb.toString());
            Date date = sdf.parse(sdf.format(new Date()));
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE, munber);
            return sdf.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获取当前日期
     *
     * @param h
     * @param m
     * @param s
     * @return
     */
    public static String goToInDate(String h, String m, String s) {
        StringBuilder sb = new StringBuilder().append("yyyy-MM-dd").append(" ")
                .append(h).append(":").append(m).append(":").append(s);
        SimpleDateFormat sdf = new SimpleDateFormat(sb.toString());
        return sdf.format(new Date());
    }

    /**
     * 替换符号
     *
     * @param str
     * @return
     */
    public static String replaceStr(String str) {
        String[] old = {" ", "<", ">", "&", "\"", "'", "￠", "£", "¥", "€", "§", "©", "®", "™", "×", "÷"};
        String[] now = {"&nbsp;", "&lt;", "&gt;", "&amp;", "&quot;", "&apos;", "&cent;", "&pound;", "&yen;", "&euro;"
                , "&sect;", "&copy;", "&reg;", "&trade;", "&times;", "&divide;"};
        for (int i = 0; i < old.length; i++) {
            str = str.replace(old[i], now[i]);
        }
        return str;
    }

    /**
     * 执行python脚本
     *
     * @param id
     * @param path
     * @return
     */
    public static ResponseItem<String> executePython(String id, String path) {
        InputStream is = null;
        BufferedReader br = null;
        ResponseItem<String> res = new ResponseItem<>();
        StringBuilder sb = new StringBuilder();
        sb.append("cd /root; ").append(path).append(" ").append(id);
        String[] cmd = {"/bin/sh", "-c", sb.toString()};
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            is = process.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            sb.setLength(0);
            String len = "";
            while ((len = br.readLine()) != null) {
                sb.append(len).append("\n");
            }
            if (sb.length() <= 0) {
                is = process.getErrorStream();
                br = new BufferedReader(new InputStreamReader(is));
                sb.setLength(0);
                while ((len = br.readLine()) != null) {
                    sb.append(len).append("\n");
                }
            }
            process.waitFor();
            res.setObj(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (is != null)
                    is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
