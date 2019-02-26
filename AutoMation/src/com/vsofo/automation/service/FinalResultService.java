package com.vsofo.automation.service;

import com.vsofo.automation.entity.FinalResult;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/4
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/4
 * @修改描述:
 */
public interface FinalResultService {
    /**
     * 加载用例参数
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<FinalResult> selectCaseDicParams(String message) throws Exception;

    /**
     * 加载预设参数
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<FinalResult> selectExpTypeInfo(String message) throws Exception;

    /**
     * 取得用例标题
     *
     * @param message
     * @return
     * @throws Exception
     */
    String selectCaseDicName(String message) throws Exception;
}
