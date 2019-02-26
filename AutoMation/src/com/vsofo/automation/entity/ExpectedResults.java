package com.vsofo.automation.entity;

import com.vsofo.automation.utils.LogInfo;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/28
 * @类描述: 预测结果
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/28
 * @修改描述:
 */
@LogInfo(key = {"caseModelId", "detail", "expectTypeId", "sqlId"},
        val = {"用例模块ID", "预期结果详情", "预测类型ID", "执行的脚本ID"})
public class ExpectedResults {
    private int id;
    private int caseModelId;  //所属用例模块ID
    private int expectTypeId;  //预测类型ID
    private String expectTypeName;  //预测类型
    private String detail;  //预期结果详情
    private int sqlId;  //执行的脚本ID
    private String sqlName;  //执行的脚本
    private String eValue;  //预期值
    private int orderNum;  //执行的顺序
    private String isEnable;  //是否可用
    private int userId;  //新增人ID
    private String addTime;  //新增时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCaseModelId() {
        return caseModelId;
    }

    public void setCaseModelId(int caseModelId) {
        this.caseModelId = caseModelId;
    }

    public int getExpectTypeId() {
        return expectTypeId;
    }

    public void setExpectTypeId(int expectTypeId) {
        this.expectTypeId = expectTypeId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getSqlId() {
        return sqlId;
    }

    public void setSqlId(int sqlId) {
        this.sqlId = sqlId;
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

    public String getExpectTypeName() {
        return expectTypeName;
    }

    public void setExpectTypeName(String expectTypeName) {
        this.expectTypeName = expectTypeName;
    }

    public String getSqlName() {
        return sqlName;
    }

    public void setSqlName(String sqlName) {
        this.sqlName = sqlName;
    }

    public String geteValue() {
        return eValue;
    }

    public void seteValue(String eValue) {
        this.eValue = eValue;
    }

    @Override
    public String toString() {
        return "ExpectedResults{" +
                "id=" + id +
                ", caseModelId=" + caseModelId +
                ", expectTypeId=" + expectTypeId +
                ", expectTypeName='" + expectTypeName + '\'' +
                ", detail='" + detail + '\'' +
                ", sqlId=" + sqlId +
                ", sqlName='" + sqlName + '\'' +
                ", eValue='" + eValue + '\'' +
                ", orderNum=" + orderNum +
                ", isEnable='" + isEnable + '\'' +
                ", userId=" + userId +
                ", addTime='" + addTime + '\'' +
                '}';
    }
}
