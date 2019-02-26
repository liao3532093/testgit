package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/25
 * @类描述: 请求参数配置
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/25
 * @修改描述:
 */
public class RequestModular {
    private int id;
    private String title;  //参数名称
    private String value;  //参数值，参数方法名
    private int modelID;  //所属模块ID
    private String isEnable;  //是否可用
    private String userId;  //新增用户ID
    private String addTime;  //新增时间
    private int valueType;  //参数类型（1=值类型；2=方法类型）

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getModelID() {
        return modelID;
    }

    public void setModelID(int modelID) {
        this.modelID = modelID;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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
        return "RequestModular{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", value='" + value + '\'' +
                ", modelID=" + modelID +
                ", isEnable='" + isEnable + '\'' +
                ", userId='" + userId + '\'' +
                ", addTime='" + addTime + '\'' +
                ", valueType=" + valueType +
                '}';
    }
}
