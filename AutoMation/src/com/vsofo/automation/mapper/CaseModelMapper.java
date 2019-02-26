package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.CaseModel;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/27
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/27
 * @修改描述:
 */
public interface CaseModelMapper {
    /**
     * 分页查询用例模块
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<CaseModel> selectCaseModelAll(String message) throws Exception;

    /**
     * 分页查询用例模块个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectCaseModelCount(String message) throws Exception;

    /**
     * 保存用例模块
     *
     * @param message
     * @return
     * @throws Exception
     */
    long saveCaseModelInfo(String message) throws Exception;

    /**
     * 通过ID查询模块信息
     *
     * @param message
     * @return
     * @throws Exception
     */
    CaseModel selectCaseModelByExaInfo(String message) throws Exception;

    /**
     * 查询刚插入的ID
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectCaseModelByDescId(String message) throws Exception;
}
