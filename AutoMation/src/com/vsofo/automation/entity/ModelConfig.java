package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/28
 * @类描述: 用例模块参数
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/28
 * @修改描述:
 */
public class ModelConfig {
    private int id;
    private int caseModelId;  //用例模块ID
    private int paramId;  //参数ID
    private String value;  //参数值或方法
    private int userId;  //新增人用户ID
    private String addTime;  //新增时间
    private int valueType;  //参数类型（1=值类型；2=方法类型）

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

    public int getParamId() {
        return paramId;
    }

    public void setParamId(int paramId) {
        this.paramId = paramId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    @Override
    public String toString() {
        return "ModelConfig{" +
                "id=" + id +
                ", caseModelId=" + caseModelId +
                ", paramId=" + paramId +
                ", value='" + value + '\'' +
                ", userId=" + userId +
                ", addTime='" + addTime + '\'' +
                ", valueType=" + valueType +
                '}';
    }
}
