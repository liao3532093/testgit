package com.vsofo.automation.entity;

import java.util.List;

/**
 * @项目名称:
 * @作者: Administrator
 * @描述:
 * @SVN版本号:
 * @修改人: Administrator
 * @修改时间: 2016/12/27
 * @修改的内容:
 */
public class ResponseItem<T> {
    private String msg;  //返回成功或错误信息
    private int code;  //返回成功或错误代号
    private List<T> rows;  //返回成功后的数据
    private T obj;
    private long total;  //返回数据条目

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
