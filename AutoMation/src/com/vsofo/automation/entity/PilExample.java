package com.vsofo.automation.entity;

import com.vsofo.automation.utils.LogInfo;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/6
 * @类描述: 渠道用例
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/6
 * @修改描述:
 */
@LogInfo(key = {"platId", "pilExaName"},
        val = {"平台ID", "用例标题"})
public class PilExample {
    private int id;
    private int platId;
    private String pilExaName;
    private int state = 1;
    private int userId;
    private String platName;

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

    public String getPilExaName() {
        return pilExaName;
    }

    public void setPilExaName(String pilExaName) {
        this.pilExaName = pilExaName;
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

    @Override
    public String toString() {
        return "PilExample{" +
                "id=" + id +
                ", platId=" + platId +
                ", pilExaName='" + pilExaName + '\'' +
                ", state=" + state +
                ", userId=" + userId +
                ", platName='" + platName + '\'' +
                '}';
    }
}
