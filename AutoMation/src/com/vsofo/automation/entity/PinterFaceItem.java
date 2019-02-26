package com.vsofo.automation.entity;

/**
 * Created by vsofo on 2017/4/20.
 *  平台接口
 */
public class PinterFaceItem {
    private int id;
    private String title;
    private int platId;
    private String platName;
    private String isEnable;
    private int userId;
    private String addTime;

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

    public int getPlatId() {
        return platId;
    }

    public void setPlatId(int platId) {
        this.platId = platId;
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
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
        return "PinterFaceItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", platId=" + platId +
                ", platName='" + platName + '\'' +
                ", isEnable='" + isEnable + '\'' +
                ", userId=" + userId +
                ", addTime='" + addTime + '\'' +
                '}';
    }
}
