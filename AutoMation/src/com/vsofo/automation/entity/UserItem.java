package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/5
 * @类描述: 用户信息
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/5
 * @修改描述:
 */
public class UserItem {
    private int id;
    private String account; //登录账户
    private String password;  //登录密码
    private String showName;  //真实姓名
    private String isLock;  //是否锁定
    private String lastLoginTime;  //最后登录时间
    private String lastLoginIP;  //最后登录IP
    private String addTime;  //新增时间
    private String oldPass;  //旧密码
    private String newPass;  //新密码
    private String sessionId;  //用户登录用到的sessionid

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", showName='" + showName + '\'' +
                ", isLock='" + isLock + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", lastLoginIP='" + lastLoginIP + '\'' +
                ", addTime='" + addTime + '\'' +
                ", oldPass='" + oldPass + '\'' +
                ", newPass='" + newPass + '\'' +
                ", sessionId='" + sessionId + '\'' +
                '}';
    }
}
