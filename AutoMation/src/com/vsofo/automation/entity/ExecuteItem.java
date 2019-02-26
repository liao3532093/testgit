package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/26
 * @类描述: 校验模块
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/26
 * @修改描述:
 */
public class ExecuteItem {
    private int id;
    private int platID;
    private String platName;
    private int expectTypeID;
    private String expectTypeName;
    private String title = "";
    private String executeSql;
    private String isEnable;
    private int userId;
    private String addTime;
    private String expectValue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlatID() {
        return platID;
    }

    public void setPlatID(int platID) {
        this.platID = platID;
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public int getExpectTypeID() {
        return expectTypeID;
    }

    public void setExpectTypeID(int expectTypeID) {
        this.expectTypeID = expectTypeID;
    }

    public String getExpectTypeName() {
        return expectTypeName;
    }

    public void setExpectTypeName(String expectTypeName) {
        this.expectTypeName = expectTypeName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExecuteSql() {
        return executeSql;
    }

    public void setExecuteSql(String executeSql) {
        this.executeSql = executeSql;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getExpectValue() {
        return expectValue;
    }

    public void setExpectValue(String expectValue) {
        this.expectValue = expectValue;
    }

    @Override
    public String toString() {
        return "ExecuteItem{" +
                "id=" + id +
                ", platID=" + platID +
                ", platName='" + platName + '\'' +
                ", expectTypeID=" + expectTypeID +
                ", expectTypeName='" + expectTypeName + '\'' +
                ", title='" + title + '\'' +
                ", executeSql='" + executeSql + '\'' +
                ", isEnable='" + isEnable + '\'' +
                ", userId=" + userId +
                ", addTime='" + addTime + '\'' +
                ", expectValue='" + expectValue + '\'' +
                '}';
    }
}
