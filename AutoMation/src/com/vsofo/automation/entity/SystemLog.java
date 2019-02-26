package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/10
 * @类描述: 系统日志
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/10
 * @修改描述:
 */
public class SystemLog {
    private int id;
    private int userId;
    private String showName;
    private String ipConfig;
    private String info;
    private int logId;
    private String logName;
    private String addTime;
    private String inDate;
    private String outDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getIpConfig() {
        return ipConfig;
    }

    public void setIpConfig(String ipConfig) {
        this.ipConfig = ipConfig;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    @Override
    public String toString() {
        return "SystemLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", showName='" + showName + '\'' +
                ", ipConfig='" + ipConfig + '\'' +
                ", info='" + info + '\'' +
                ", logId=" + logId +
                ", logName='" + logName + '\'' +
                ", addTime='" + addTime + '\'' +
                ", inDate='" + inDate + '\'' +
                ", outDate='" + outDate + '\'' +
                '}';
    }
}
