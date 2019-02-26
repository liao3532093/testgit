package com.vsofo.automation.entity;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/8/3
 * @类描述: 立即执行结果详情
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/8/3
 * @修改描述:
 */
public class PilExeItem {
    private int id;
    private String message;

    public PilExeItem() {
    }

    public PilExeItem(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "PilExeItem{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
