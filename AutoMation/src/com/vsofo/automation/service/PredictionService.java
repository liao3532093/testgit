package com.vsofo.automation.service;

import com.vsofo.automation.entity.PredictionItem;

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
public interface PredictionService {
    /**
     * 保存预测类型
     *
     * @param message
     * @return
     * @throws Exception
     */
    long savePredictionInfo(String message) throws Exception;

    /**
     * 分页查询预测类型
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PredictionItem> selectPredictionAll(String message) throws Exception;

    /**
     * 分页查询预测类型个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectPredictionCount(String message) throws Exception;

    /**
     * 按ID查询预测类型
     *
     * @param message
     * @return
     * @throws Exception
     */
    PredictionItem selectPredictionById(String message) throws Exception;

    /**
     * 启用或禁用
     *
     * @param message
     * @return
     * @throws Exception
     */
    long openPredictionById(String message) throws Exception;

    /**
     * 查询预测类型的ID和名称
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PredictionItem> selectPredIsEnableByName(String message) throws Exception;
}
