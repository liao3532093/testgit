package com.vsofo.automation.entity;

/**
 * @项目名称:
 * @作者: liaowenjun
 * @描述: 分页实体
 * @SVN版本号:
 * @修改人: liaowenjun
 * @修改时间: 2016/12/28
 * @修改的内容:
 */
public class PageItem {
    private int page; // 第几页
    private int pageSize; // 每页记录数

    public PageItem() {
        super();
    }

    public PageItem(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getIndex() {
        return (page - 1) * pageSize;
    }
}
