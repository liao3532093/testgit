package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.CaseStatistics;
import com.vsofo.automation.mapper.CaseStatisticsMapper;
import com.vsofo.automation.service.CaseStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;

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
public class CaseStatisticsServiceImpl implements CaseStatisticsService {
    @Autowired
    private CaseStatisticsMapper mCaseStatisticsMapper;

    @Override
    public List<CaseStatistics> selectCaseStatisticsAll(String message) throws Exception {
        return mCaseStatisticsMapper.selectCaseStatisticsAll(message);
    }

    @Override
    public long selectCaseStatisticsCount(String message) throws Exception {
        return mCaseStatisticsMapper.selectCaseStatisticsCount(message);
    }

    @Override
    public String[] selectCaseDetailCount(String message) throws Exception {
        return mCaseStatisticsMapper.selectCaseDetailCount(message);
    }

    @Override
    public List<CaseStatistics> selectCaseInterStatisticsAll(String message) throws Exception {
        return mCaseStatisticsMapper.selectCaseInterStatisticsAll(message);
    }

    @Override
    public long selectCaseInterStatisticsCount(String message) throws Exception {
        return mCaseStatisticsMapper.selectCaseInterStatisticsCount(message);
    }

    @Override
    public List<CaseStatistics> selectInterCaseDetailCount(String message) throws Exception {
        return mCaseStatisticsMapper.selectInterCaseDetailCount(message);
    }

    @Override
    public CaseStatistics selectCaseStatisticsById(String message) throws Exception {
        return mCaseStatisticsMapper.selectCaseStatisticsById(message);
    }

    @Override
    public List<CaseStatistics> selectCaseStatisticsSize(String message) throws Exception {
        return mCaseStatisticsMapper.selectCaseStatisticsSize(message);
    }
}
