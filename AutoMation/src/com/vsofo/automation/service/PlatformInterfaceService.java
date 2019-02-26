package com.vsofo.automation.service;

import com.vsofo.automation.entity.PinterFaceItem;

import java.util.List;

/**
 * Created by vsofo on 2017/4/20.
 */
public interface PlatformInterfaceService {
    /**
     * 保存平台接口
     *
     * @param message
     * @return
     * @throws Exception
     */
    long savePlatformInterface(String message) throws Exception;

    /***
     * 分页查询平台接口
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PinterFaceItem> selectPlatInterfaceAll(String message) throws Exception;

    /***
     * 分页查询平台接口个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectPlatInterfaceCount(String message) throws Exception;

    /***
     * 按id进行查询
     *
     * @param message
     * @return
     * @throws Exception
     */
    PinterFaceItem selectPlatInterfaceById(String message) throws Exception;

    /***
     * 查询接口过滤禁用
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PinterFaceItem> selectInterfaceByName(String message) throws Exception;

    /**
     * 启用禁用
     *
     * @param message
     * @return
     * @throws Exception
     */
    long openCloseInterfaceById(String message) throws Exception;
}
