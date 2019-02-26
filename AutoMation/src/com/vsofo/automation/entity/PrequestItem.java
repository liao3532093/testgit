package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/24
 * @类描述: 平台请求列表
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/24
 * @修改描述:
 */
public class PrequestItem {
    private int id;
    private int platId;
    private int interfaceId;
    private String title;
    private String requestUrl;
    private int requestType;
    private int paramType;
    private String isEnable;
    private int userId;
    private String addTime;
    private String platName;
    private String interName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlatId() {
        return platId;
    }

    public void setPlatId(int platId) {
        this.platId = platId;
    }

    public int getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(int interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public int getParamType() {
        return paramType;
    }

    public void setParamType(int paramType) {
        this.paramType = paramType;
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

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName;
    }

    @Override
    public String toString() {
        return "PrequestItem{" +
                "id=" + id +
                ", platId=" + platId +
                ", interfaceId=" + interfaceId +
                ", title='" + title + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", requestType=" + requestType +
                ", paramType=" + paramType +
                ", isEnable='" + isEnable + '\'' +
                ", userId=" + userId +
                ", addTime='" + addTime + '\'' +
                ", platName='" + platName + '\'' +
                ", interName='" + interName + '\'' +
                '}';
    }
}
