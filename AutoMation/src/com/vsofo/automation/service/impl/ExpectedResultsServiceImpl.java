package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.ExpectedResults;
import com.vsofo.automation.mapper.ExpectedResultsMapper;
import com.vsofo.automation.service.ExpectedResultsService;
import org.springframework.beans.factory.annotation.Autowired;

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
public class ExpectedResultsServiceImpl implements ExpectedResultsService {
    @Autowired
    private ExpectedResultsMapper mExpectedResultsMapper;

    @Override
    public List<ExpectedResults> selectExpectedResultsAll(String message) throws Exception {
        return mExpectedResultsMapper.selectExpectedResultsAll(message);
    }

    @Override
    public long selectExpectedResultsCount(String message) throws Exception {
        return mExpectedResultsMapper.selectExpectedResultsCount(message);
    }

    @Override
    public long saveExpectedResults(String message) throws Exception {
        return mExpectedResultsMapper.saveExpectedResults(message);
    }

    @Override
    public long openCloseExpectedResults(String message) throws Exception {
        return mExpectedResultsMapper.openCloseExpectedResults(message);
    }

    @Override
    public ExpectedResults selectExpectedResultsById(String message) throws Exception {
        return mExpectedResultsMapper.selectExpectedResultsById(message);
    }
}
