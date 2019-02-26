package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.ExecuteItem;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/26
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/26
 * @修改描述:
 */
public interface PlatCheckMapper {
    /**
     * 分页查询校验模块
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<ExecuteItem> selectPlatCheckAll(String message) throws Exception;

    /**
     * 分页查询校验模块个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectPlatCheckCount(String message) throws Exception;

    /**
     * 保存校验模块
     *
     * @param message
     * @return
     * @throws Exception
     */
    long savePlatCheckInfo(String message) throws Exception;

    /**
     * 启用禁用
     *
     * @param message
     * @return
     * @throws Exception
     */
    long startClosePlatCheck(String message) throws Exception;

    /**
     * 通过ID查询校验模块
     *
     * @param message
     * @return
     * @throws Exception
     */
    ExecuteItem selectPlatCheckById(String message) throws Exception;

    /**
     * 通过预测类型ID查询校验信息
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<ExecuteItem> selectCheckScriptByPerd(String message) throws Exception;
}
