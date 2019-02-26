package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.PmanageItem;

import java.util.List;

/**
 * Created by vsofo on 2017/4/19.
 */
public interface PlatformMapper {
    /***
     * 添加平台名称
     *
     * @param message
     * @return
     * @throws Exception
     */
    long addPlatformManage(String message) throws Exception;

    /***
     * 查询全部平台名称信息
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PmanageItem> selectPlatformManageAll(String message) throws Exception;

    /**
     * 修改平台名称
     *
     * @param message
     * @return
     * @throws Exception
     */
    long updatePlatformManageById(String message) throws Exception;

    /**
     * 禁用平台名称
     *
     * @param message
     * @return
     * @throws Exception
     */
    long disablePlatformManageById(String message) throws Exception;

    /***
     * 查询平台名称过滤禁用
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PmanageItem> selectPlatformManageByName(String message) throws Exception;

    /***
     * 查询所有平台信息个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectPlatformManageCount(String message) throws Exception;

    /***
     * 按ID查询平台名称
     *
     * @param message
     * @return
     * @throws Exception
     */
    PmanageItem selectPlatformManageByIdToName(String message) throws Exception;
}
