package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.CaseStatistics;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/3
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/3
 * @修改描述:
 */
public interface CaseStatisticsMapper {
    /**
     * 分页查询用例平台统计
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<CaseStatistics> selectCaseStatisticsAll(String message) throws Exception;

    /**
     * 分页查询用例平台统计个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectCaseStatisticsCount(String message) throws Exception;

    /**
     * 查询平台数据
     *
     * @param message
     * @return
     * @throws Exception
     */
    String[] selectCaseDetailCount(String message) throws Exception;

    /**
     * 分页查询用例接口统计
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<CaseStatistics> selectCaseInterStatisticsAll(String message) throws Exception;

    /**
     * 分页查询用例接口统计个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectCaseInterStatisticsCount(String message) throws Exception;

    /**
     * 查询平台和接口数据
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<CaseStatistics> selectInterCaseDetailCount(String message) throws Exception;

    /**
     * 通过ID去查询平台统计
     *
     * @param message
     * @return
     * @throws Exception
     */
    CaseStatistics selectCaseStatisticsById(String message) throws Exception;

    /**
     * 取得当天运行用例总数
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<CaseStatistics> selectCaseStatisticsSize(String message) throws Exception;
}
