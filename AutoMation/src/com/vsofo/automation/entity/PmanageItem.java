package com.vsofo.automation.entity;

/**
 * Created by vsofo on 2017/4/19.
 * 平台管理
 */
public class PmanageItem {
    private int id;
    private String title = ""; //平台名称
    private String exeUrl = "";  //渠道执行地址
    private String isEnable;
    private String addTime;
    private int userId;

    public String getExeUrl() {
        return exeUrl;
    }

    public void setExeUrl(String exeUrl) {
        this.exeUrl = exeUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PmanageItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", exeUrl='" + exeUrl + '\'' +
                ", isEnable='" + isEnable + '\'' +
                ", addTime='" + addTime + '\'' +
                ", userId=" + userId +
                '}';
    }
}
