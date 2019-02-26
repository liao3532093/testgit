package com.vsofo.automation.service;

import com.vsofo.automation.entity.LogType;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/9
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/9
 * @修改描述:
 */
public interface LogTypeService {
    /**
     * 保存日志类型信息
     *
     * @param message
     * @return
     * @throws Exception
     */
    long saveLogTypeInfo(String message) throws Exception;

    /**
     * 分页查询日志类型
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<LogType> selectLogTypeAll(String message) throws Exception;

    /**
     * 分页查询日志类型个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectLogTypeCount(String message) throws Exception;

    /**
     * 按ID查询日志类型
     *
     * @param message
     * @return
     * @throws Exception
     */
    LogType selectLogTypeById(String message) throws Exception;

    /**
     * 删除日志类型
     *
     * @param message
     * @return
     * @throws Exception
     */
    long deleteLogTypeById(String message) throws Exception;
}
