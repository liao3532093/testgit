package com.vsofo.automation.service;

import com.vsofo.automation.entity.ExampleItem;

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
public interface ExampleService {
    /**
     * 分页查询用例详情
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<ExampleItem> selectCaseDetailAll(String message) throws Exception;

    /**
     * 分页查询用例详情个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectCaseDetailCount(String message) throws Exception;

    /**
     * 保存用例详情
     *
     * @param message
     * @return
     * @throws Exception
     */
    long saveCaseDetailInfo(String message) throws Exception;

    /**
     * 通过ID查询用例详情
     *
     * @param message
     * @return
     * @throws Exception
     */
    ExampleItem selectCaseDetailById(String message) throws Exception;

    /**
     * 启用禁用
     *
     * @param message
     * @return
     * @throws Exception
     */
    long openCloseCaseDetailById(String message) throws Exception;
}
