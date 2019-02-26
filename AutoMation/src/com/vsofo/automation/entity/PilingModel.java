package com.vsofo.automation.entity;

import com.vsofo.automation.utils.LogInfo;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/5
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/5
 * @修改描述:
 */
@LogInfo(key = {"platId", "modelName", "modelType", "requestUrl", "requestType", "responseItem"},
        val = {"平台ID", "模块名称", "模块类型", "请求地址", "请求方式", "参数"})
public class PilingModel {
    private int id;
    private String modelName;
    private int platId;
    private int qType;
    private int modelType = -1;
    private String requestUrl;
    private String requestType;
    private String responseItem;
    private String requestItem;
    private int state;
    private int userId;
    private String platName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getPlatId() {
        return platId;
    }

    public void setPlatId(int platId) {
        this.platId = platId;
    }

    public int getqType() {
        return qType;
    }

    public void setqType(int qType) {
        this.qType = qType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getResponseItem() {
        return responseItem;
    }

    public void setResponseItem(String responseItem) {
        this.responseItem = responseItem;
    }

    public String getRequestItem() {
        return requestItem;
    }

    public void setRequestItem(String requestItem) {
        this.requestItem = requestItem;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }

    public int getModelType() {
        return modelType;
    }

    public void setModelType(int modelType) {
        this.modelType = modelType;
    }

    @Override
    public String toString() {
        return "PilingModel{" +
                "id=" + id +
                ", modelName='" + modelName + '\'' +
                ", platId=" + platId +
                ", qType=" + qType +
                ", modelType=" + modelType +
                ", requestUrl='" + requestUrl + '\'' +
                ", requestType='" + requestType + '\'' +
                ", responseItem='" + responseItem + '\'' +
                ", requestItem='" + requestItem + '\'' +
                ", state=" + state +
                ", userId=" + userId +
                ", platName='" + platName + '\'' +
                '}';
    }
}
