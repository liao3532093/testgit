package com.vsofo.automation.entity;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/8
 * @类描述: 大类别权限
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/8
 * @修改描述:
 */
public class PowerClass {
    private int id;
    private String title;  //大类别名称
    private String isLock;  //是否锁定
    private int orderNo;  //排序（从小到大排序）
    private List<PowerParent> data;

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

    public List<PowerParent> getData() {
        return data;
    }

    public void setData(List<PowerParent> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PowerClass{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isLock='" + isLock + '\'' +
                ", orderNo=" + orderNo +
                ", data=" + data +
                '}';
    }
}
