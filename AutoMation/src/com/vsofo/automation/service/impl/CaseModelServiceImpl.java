package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.CaseModel;
import com.vsofo.automation.mapper.CaseModelMapper;
import com.vsofo.automation.service.CaseModelService;
import org.springframework.beans.factory.annotation.Autowired;

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
public class CaseModelServiceImpl implements CaseModelService {
    @Autowired
    private CaseModelMapper mCaseModelMapper;

    @Override
    public List<CaseModel> selectCaseModelAll(String message) throws Exception {
        return mCaseModelMapper.selectCaseModelAll(message);
    }

    @Override
    public long selectCaseModelCount(String message) throws Exception {
        return mCaseModelMapper.selectCaseModelCount(message);
    }

    @Override
    public long saveCaseModelInfo(String message) throws Exception {
        return mCaseModelMapper.saveCaseModelInfo(message);
    }

    @Override
    public CaseModel selectCaseModelByExaInfo(String message) throws Exception {
        return mCaseModelMapper.selectCaseModelByExaInfo(message);
    }

    @Override
    public long selectCaseModelByDescId(String message) throws Exception {
        return mCaseModelMapper.selectCaseModelByDescId(message);
    }
}
