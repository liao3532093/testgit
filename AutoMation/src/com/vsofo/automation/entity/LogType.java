package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/9
 * @类描述: 日志信息
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/9
 * @修改描述:
 */
public class LogType {
    private int id;
    private String logKey;  //关键词
    private String title;  //日志名称
    private String isEnable;  //是否正常状态

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogKey() {
        return logKey;
    }

    public void setLogKey(String logKey) {
        this.logKey = logKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    public String toString() {
        return "LogType{" +
                "id=" + id +
                ", logKey='" + logKey + '\'' +
                ", title='" + title + '\'' +
                ", isEnable='" + isEnable + '\'' +
                '}';
    }
}
