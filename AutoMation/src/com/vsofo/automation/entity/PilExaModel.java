package com.vsofo.automation.entity;

import com.vsofo.automation.utils.LogInfo;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/7
 * @类描述: 渠道用例模块
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/7
 * @修改描述:
 */
@LogInfo(key = {"modelId", "exaId", "value"},
        val = {"渠道模块ID", "渠道用例ID", "参数"})
public class PilExaModel {
    private int id;
    private int modelId;
    private int exaId;
    private int orderId;
    private int state;
    private String value;
    private String modelName;
    private String details;
    private String expectValue;
    private String resultId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getExaId() {
        return exaId;
    }

    public void setExaId(int exaId) {
        this.exaId = exaId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getExpectValue() {
        return expectValue;
    }

    public void setExpectValue(String expectValue) {
        this.expectValue = expectValue;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    @Override
    public String toString() {
        return "PilExaModel{" +
                "id=" + id +
                ", modelId=" + modelId +
                ", exaId=" + exaId +
                ", orderId=" + orderId +
                ", state=" + state +
                ", value='" + value + '\'' +
                ", modelName='" + modelName + '\'' +
                ", details='" + details + '\'' +
                ", expectValue='" + expectValue + '\'' +
                ", resultId='" + resultId + '\'' +
                '}';
    }
}
