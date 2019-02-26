package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.ImplementInfo;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/3
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/3
 * @修改描述:
 */
public interface ImplementMapper {
    /**
     * 分页查询执行详情
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<ImplementInfo> selectImplementInfoAll(String message) throws Exception;

    /**
     * 分页查询执行详情个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectImplementInfoCount(String message) throws Exception;

    /**
     * 查询出30天内的所有用例ID
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<String> implCaseId30All(String message) throws Exception;
}
