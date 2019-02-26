package com.vsofo.automation.entity;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/11
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/11
 * @修改描述:
 */
public class PermissionType {
    /**
     * name : ["UserListView","UserListManage","PowerClassManage"]
     * id : 1
     */

    private String id;
    private List<String> name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PermissionType{" +
                "id='" + id + '\'' +
                ", name=" + name +
                '}';
    }
}
