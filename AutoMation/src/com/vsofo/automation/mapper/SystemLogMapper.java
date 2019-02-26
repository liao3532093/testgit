package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.SystemLog;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/10
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/10
 * @修改描述:
 */
public interface SystemLogMapper {
    /**
     * 保存日志信息
     *
     * @param message
     * @return
     * @throws Exception
     */
    long saveSystemLogInfo(String message) throws Exception;

    /**
     * 查询日志
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<SystemLog> selectSystemLogAll(String message) throws Exception;

    /**
     * 查询日志个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectSystemLogCount(String message) throws Exception;
}
