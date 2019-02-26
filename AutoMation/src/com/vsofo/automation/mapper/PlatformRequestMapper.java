package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.PrequestItem;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/24
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/24
 * @修改描述:
 */
public interface PlatformRequestMapper {
    /**
     * 保存用例信息
     *
     * @param message
     * @return
     * @throws Exception
     */
    long savePlatRequestInfo(String message) throws Exception;

    /**
     * 分页查询所有用例信息
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PrequestItem> selectPlatRequestAll(String message) throws Exception;

    /***
     * 查询用例信息个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectPlatRequestCount(String message) throws Exception;

    /***
     * 按ID查询用例信息
     *
     * @param message
     * @return
     * @throws Exception
     */
    PrequestItem selectPlatRequestById(String message) throws Exception;

    /**
     * 查询所有请求模块的名称的ID
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PrequestItem> selectPlatRequestByNames(String message) throws Exception;

    /**
     * 启用禁用
     *
     * @param message
     * @return
     * @throws Exception
     */
    long openClosePlatRequestById(String message) throws Exception;
}
