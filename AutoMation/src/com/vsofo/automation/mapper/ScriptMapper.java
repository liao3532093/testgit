package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.ScriptItem;

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
public interface ScriptMapper {
    /**
     * 分页查询脚本方法
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<ScriptItem> selectScriptMethodAll(String message) throws Exception;

    /**
     * 分页查询脚本方法个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectScriptMethodCount(String message) throws Exception;

    /**
     * 保存脚本方法
     *
     * @param message
     * @return
     * @throws Exception
     */
    long saveScriptMethod(String message) throws Exception;

    /**
     * 按ID查询脚本方法
     *
     * @param message
     * @return
     * @throws Exception
     */
    ScriptItem selectScriptMethodById(String message) throws Exception;

    /**
     * 查询所有脚本方法和ID
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<ScriptItem> selectScriptMethodByName(String message) throws Exception;

    /**
     * 启用禁用
     *
     * @param message
     * @return
     * @throws Exception
     */
    long openCloseScriptMethodById(String message) throws Exception;
}
