package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/8
 * @类描述: 权限关键字
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/8
 * @修改描述:
 */
public class PowerItem {
    private int id;
    private int parentClassId;  //子类别ID
    private String keywords;  //关键词
    private String title; //权限名称

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentClassId() {
        return parentClassId;
    }

    public void setParentClassId(int parentClassId) {
        this.parentClassId = parentClassId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "PowerItem{" +
                "id=" + id +
                ", parentClassId=" + parentClassId +
                ", keywords='" + keywords + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
