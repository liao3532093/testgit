package com.vsofo.automation.entity;

import com.vsofo.automation.utils.LogInfo;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/27
 * @类描述: 用例模块
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/27
 * @修改描述:
 */
@LogInfo(key = {"caseId", "modelId"},
        val = {"用例ID", "模块ID"})
public class CaseModel {
    private int id;
    private int caseId;  //所属用例ID
    private String caseName;  //所属用例名称
    private int modelId;  //模块ID
    private String modelName;  //模块名称
    private String modelUrl;  //模块请求地址
    private int orderNum;  //执行顺序
    private String isEnable; //是否可用
    private int userId;  //新增人用户ID
    private String addTime;  //新增时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
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

    @Override
    public String toString() {
        return "CaseModel{" +
                "id=" + id +
                ", caseId=" + caseId +
                ", caseName='" + caseName + '\'' +
                ", modelId=" + modelId +
                ", modelName='" + modelName + '\'' +
                ", modelUrl='" + modelUrl + '\'' +
                ", orderNum=" + orderNum +
                ", isEnable='" + isEnable + '\'' +
                ", userId=" + userId +
                ", addTime='" + addTime + '\'' +
                '}';
    }
}
