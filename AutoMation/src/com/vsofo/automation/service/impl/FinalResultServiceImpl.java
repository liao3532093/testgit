package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.FinalResult;
import com.vsofo.automation.mapper.FinalResultMapper;
import com.vsofo.automation.service.FinalResultService;
import org.springframework.beans.factory.annotation.Autowired;

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
public class FinalResultServiceImpl implements FinalResultService {
    @Autowired
    private FinalResultMapper mFinalResultMapper;

    @Override
    public List<FinalResult> selectCaseDicParams(String message) throws Exception {
        return mFinalResultMapper.selectCaseDicParams(message);
    }

    @Override
    public List<FinalResult> selectExpTypeInfo(String message) throws Exception {
        return mFinalResultMapper.selectExpTypeInfo(message);
    }

    @Override
    public String selectCaseDicName(String message) throws Exception {
        return mFinalResultMapper.selectCaseDicName(message);
    }
}
