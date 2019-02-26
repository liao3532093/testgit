package com.vsofo.automation.entity;

import com.vsofo.automation.utils.LogInfo;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/6
 * @类描述: 渠道预期结果
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/6
 * @修改描述:
 */
@LogInfo(key = {"details", "expId", "modelId", "sqlId"},
        val = {"说明", "预期类型ID", "渠道模块ID", "校验模块ID"})
public class PilExpResult {
    private int id;
    private String details;  //说明
    private int num;  //顺序
    private int state;  //状态
    private int userId;  //用户ID
    private int expId;  //预期类型ID
    private int modelId;  //渠道模块ID
    private int sqlId;  //校验模块ID
    private String expName;  //预期类型
    private String sqlName;  //执行脚本
    private String eValue;    //预期值

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getSqlId() {
        return sqlId;
    }

    public void setSqlId(int sqlId) {
        this.sqlId = sqlId;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
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
        return "PilExpResult{" +
                "id=" + id +
                ", details='" + details + '\'' +
                ", num=" + num +
                ", state=" + state +
                ", userId=" + userId +
                ", expId=" + expId +
                ", modelId=" + modelId +
                ", sqlId=" + sqlId +
                ", expName='" + expName + '\'' +
                ", sqlName='" + sqlName + '\'' +
                ", eValue='" + eValue + '\'' +
                '}';
    }
}
