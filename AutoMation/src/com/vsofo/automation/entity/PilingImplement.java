package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/8
 * @类描述: 渠道用例详情
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/8
 * @修改描述:
 */
public class PilingImplement {
    private int id;
    private int exaId;  //用例ID或平台ID
    private int state = -1;  //状态
    private String executeTime;  //执行时间
    private String exaTitle;  //渠道用例名称
    private String pTitle;  //平台名称
    private String oldTime;  //开始时间
    private String newTime;  //结束时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExaId() {
        return exaId;
    }

    public void setExaId(int exaId) {
        this.exaId = exaId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(String executeTime) {
        this.executeTime = executeTime;
    }

    public String getExaTitle() {
        return exaTitle;
    }

    public void setExaTitle(String exaTitle) {
        this.exaTitle = exaTitle;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getOldTime() {
        return oldTime;
    }

    public void setOldTime(String oldTime) {
        this.oldTime = oldTime;
    }

    public String getNewTime() {
        return newTime;
    }

    public void setNewTime(String newTime) {
        this.newTime = newTime;
    }

    @Override
    public String toString() {
        return "PilingImplement{" +
                "id=" + id +
                ", exaId=" + exaId +
                ", state=" + state +
                ", executeTime='" + executeTime + '\'' +
                ", exaTitle='" + exaTitle + '\'' +
                ", pTitle='" + pTitle + '\'' +
                ", oldTime='" + oldTime + '\'' +
                ", newTime='" + newTime + '\'' +
                '}';
    }
}
