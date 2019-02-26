package com.vsofo.automation.entity;

import com.vsofo.automation.utils.LogInfo;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/26
 * @类描述: 用例信息
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/26
 * @修改描述:
 */
@LogInfo(key = {"platID", "interfaceID", "title", "detail"},
        val = {"平台ID", "接口ID", "用例标题", "用例描述"})
public class ExampleItem {
    private int id;
    private int platID;  //所属平台ID
    private String platName;  //所属平台名称
    private int interfaceID;  //所属接口ID
    private String interName;  //所属接口名称
    private String title;  //用例标题
    private String detail;  //用例描述
    private String isEnable = "1"; //是否可用
    private int userId;  //新增人用户ID
    private String addTime;  //新增时间

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

    public int getInterfaceID() {
        return interfaceID;
    }

    public void setInterfaceID(int interfaceID) {
        this.interfaceID = interfaceID;
    }

    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
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
        return "ExampleItem{" +
                "id=" + id +
                ", platID=" + platID +
                ", platName='" + platName + '\'' +
                ", interfaceID=" + interfaceID +
                ", interName='" + interName + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", isEnable='" + isEnable + '\'' +
                ", userId=" + userId +
                ", addTime='" + addTime + '\'' +
                '}';
    }
}
