package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.PredictionItem;
import com.vsofo.automation.mapper.PredictionMapper;
import com.vsofo.automation.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;

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
public class PredictionServiceImpl implements PredictionService {
    @Autowired
    private PredictionMapper mPredictionMapper;

    @Override
    public long savePredictionInfo(String message) throws Exception {
        return mPredictionMapper.savePredictionInfo(message);
    }

    @Override
    public List<PredictionItem> selectPredictionAll(String message) throws Exception {
        return mPredictionMapper.selectPredictionAll(message);
    }

    @Override
    public long selectPredictionCount(String message) throws Exception {
        return mPredictionMapper.selectPredictionCount(message);
    }

    @Override
    public PredictionItem selectPredictionById(String message) throws Exception {
        return mPredictionMapper.selectPredictionById(message);
    }

    @Override
    public long openPredictionById(String message) throws Exception {
        return mPredictionMapper.openPredictionById(message);
    }

    @Override
    public List<PredictionItem> selectPredIsEnableByName(String message) throws Exception {
        return mPredictionMapper.selectPredIsEnableByName(message);
    }
}
