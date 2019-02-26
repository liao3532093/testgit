package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.RequestModular;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/25
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/25
 * @修改描述:
 */
public interface RequestModularMapper {
    /**
     * 分页查询所有模块参数
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<RequestModular> selectModlarAll(String message) throws Exception;

    /***
     * 分页查询所有模块参数个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectModlarCount(String message) throws Exception;

    /**
     * 保存模块参数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long saveModlarInfo(String message) throws Exception;

    /**
     * 启用禁用模块参数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long startCloseModlar(String message) throws Exception;

    /**
     * 通过ID查询模块参数
     *
     * @param message
     * @return
     * @throws Exception
     */
    RequestModular selectModlarById(String message) throws Exception;

    /**
     * 取得所有所有模块参数的ID
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<RequestModular> selectModlarByIds(String message) throws Exception;

    /**
     * 通过请求模块配置ID查询
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<RequestModular> selectModlarByMid(String message) throws Exception;

    /**
     * 统一配置模块参数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long unifiedUpdateModlarInfo(String message) throws Exception;
}
