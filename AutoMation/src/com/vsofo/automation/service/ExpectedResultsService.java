package com.vsofo.automation.service;

import com.vsofo.automation.entity.ExpectedResults;

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
public interface ExpectedResultsService {
    /***
     * 分页查询预测结果
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<ExpectedResults> selectExpectedResultsAll(String message) throws Exception;

    /**
     * 分页查询预测结果个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectExpectedResultsCount(String message) throws Exception;

    /***
     * 保存预测结果
     *
     * @param message
     * @return
     * @throws Exception
     */
    long saveExpectedResults(String message) throws Exception;

    /**
     * 启用和禁用预测结果
     *
     * @param message
     * @return
     * @throws Exception
     */
    long openCloseExpectedResults(String message) throws Exception;

    /**
     * 按ID查询预测结果
     *
     * @param message
     * @return
     * @throws Exception
     */
    ExpectedResults selectExpectedResultsById(String message) throws Exception;
}
