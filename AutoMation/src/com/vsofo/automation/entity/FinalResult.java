package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/4
 * @类描述: 用例预测最终结果
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/4
 * @修改描述:
 */
public class FinalResult {
    private int id;
    private int execId;  //所属执行ID
    private int execModelId;  //所属执行模块ID
    private int expectId;  //对应用例预测结果ID
    private String detail;  //执行详情
    private int result;  //最终结果
    private String addtime;  //执行时间
    private String tName;  //所属模块
    private String tResult;  //预测结果
    private String RequestURL;  //请求URL
    private String RequestParam;  //模块请求参数
    private String ReturnInfo;  //同步返回

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExecId() {
        return execId;
    }

    public void setExecId(int execId) {
        this.execId = execId;
    }

    public int getExecModelId() {
        return execModelId;
    }

    public void setExecModelId(int execModelId) {
        this.execModelId = execModelId;
    }

    public int getExpectId() {
        return expectId;
    }

    public void setExpectId(int expectId) {
        this.expectId = expectId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettResult() {
        return tResult;
    }

    public void settResult(String tResult) {
        this.tResult = tResult;
    }

    public String getRequestURL() {
        return RequestURL;
    }

    public void setRequestURL(String requestURL) {
        RequestURL = requestURL;
    }

    public String getRequestParam() {
        return RequestParam;
    }

    public void setRequestParam(String requestParam) {
        RequestParam = requestParam;
    }

    public String getReturnInfo() {
        return ReturnInfo;
    }

    public void setReturnInfo(String returnInfo) {
        ReturnInfo = returnInfo;
    }

    @Override
    public String toString() {
        return "FinalResult{" +
                "id=" + id +
                ", execId=" + execId +
                ", execModelId=" + execModelId +
                ", expectId=" + expectId +
                ", detail='" + detail + '\'' +
                ", result=" + result +
                ", addtime='" + addtime + '\'' +
                ", tName='" + tName + '\'' +
                ", tResult='" + tResult + '\'' +
                ", RequestURL='" + RequestURL + '\'' +
                ", RequestParam='" + RequestParam + '\'' +
                ", ReturnInfo='" + ReturnInfo + '\'' +
                '}';
    }
}
