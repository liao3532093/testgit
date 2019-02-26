package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/3
 * @类描述: 执行详情
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/3
 * @修改描述:
 */
public class ImplementInfo {
    private int id;
    private int platId;  //平台ID
    private int interId;  //接口ID
    private int state = -1;  //状态
    private String inDate;  //开始日期
    private String outDate;  //结束日期
    private String pName;  //平台名称
    private String iName;  //接口名称
    private String dName;  //用例名称
    private String detail;  //用例描述
    private int caseId;  //执行的用例ID
    private int result;  //最终结果
    private String addTime; //执行时间

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

    public int getInterId() {
        return interId;
    }

    public void setInterId(int interId) {
        this.interId = interId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getiName() {
        return iName;
    }

    public void setiName(String iName) {
        this.iName = iName;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "ImplementInfo{" +
                "id=" + id +
                ", platId=" + platId +
                ", interId=" + interId +
                ", state=" + state +
                ", inDate='" + inDate + '\'' +
                ", outDate='" + outDate + '\'' +
                ", pName='" + pName + '\'' +
                ", iName='" + iName + '\'' +
                ", dName='" + dName + '\'' +
                ", detail='" + detail + '\'' +
                ", caseId=" + caseId +
                ", result=" + result +
                ", addTime='" + addTime + '\'' +
                '}';
    }
}
