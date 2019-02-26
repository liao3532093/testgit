package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/3
 * @类描述: 用例平台统计
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/3
 * @修改描述:
 */
public class CaseStatistics {
    private int id;
    private int successNum;  //成功数量
    private int failNum;  //失败数量
    private String execTime;  //执行时间
    private String addTime;  //统计时间
    private int platID;  //平台ID
    private int caseCount;  //用例个数
    private String platName;  //平台名称
    private int interfaceID;  //接口ID
    private String interName;  //接口名称
    private String inDate;  //开始日期
    private String outDate;  //结束日期
    private int errorNum;  //脚本异常数量

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getPlatID() {
        return platID;
    }

    public void setPlatID(int platID) {
        this.platID = platID;
    }

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(int caseCount) {
        this.caseCount = caseCount;
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

    public int getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(int errorNum) {
        this.errorNum = errorNum;
    }

    @Override
    public String toString() {
        return "CaseStatistics{" +
                "id=" + id +
                ", successNum=" + successNum +
                ", failNum=" + failNum +
                ", execTime='" + execTime + '\'' +
                ", addTime='" + addTime + '\'' +
                ", platID=" + platID +
                ", caseCount=" + caseCount +
                ", platName='" + platName + '\'' +
                ", interfaceID=" + interfaceID +
                ", interName='" + interName + '\'' +
                ", inDate='" + inDate + '\'' +
                ", outDate='" + outDate + '\'' +
                ", errorNum=" + errorNum +
                '}';
    }
}
