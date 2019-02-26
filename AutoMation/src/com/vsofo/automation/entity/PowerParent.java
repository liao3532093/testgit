package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/8
 * @类描述: 小类别权限
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/8
 * @修改描述:
 */
public class PowerParent {
    private int id;
    private String title;
    private int powerClassId;
    private String isLock;  //是否锁定
    private int orderNo;  //排序（从小到大排序）

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

    public int getPowerClassId() {
        return powerClassId;
    }

    public void setPowerClassId(int powerClassId) {
        this.powerClassId = powerClassId;
    }

    public String getIsLock() {
        return isLock;
    }

    public void setIsLock(String isLock) {
        this.isLock = isLock;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return "PowerParent{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", powerClassId=" + powerClassId +
                ", isLock='" + isLock + '\'' +
                ", orderNo=" + orderNo +
                '}';
    }
}
