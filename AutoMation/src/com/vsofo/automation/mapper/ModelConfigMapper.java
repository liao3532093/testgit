package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.ModelConfig;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/28
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/28
 * @修改描述:
 */
public interface ModelConfigMapper {
    /**
     * 保存用例模块参数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long saveModelConfigInfo(String message) throws Exception;

    /**
     * 保存时删除
     *
     * @param message
     * @return
     * @throws Exception
     */
    long removeModelConfigById(String message) throws Exception;

    /**
     * 按用例模块ID查询
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<ModelConfig> selectModelConfigByMid(String message) throws Exception;
}
