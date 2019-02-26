package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.FinalResult;
import com.vsofo.automation.entity.PilingImplement;
import com.vsofo.automation.mapper.PilingImplMapper;
import com.vsofo.automation.service.PilingImplService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/8
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/8
 * @修改描述:
 */
public class PilingImplServiceImpl implements PilingImplService {
    @Autowired
    private PilingImplMapper mPilingImplMapper;

    @Override
    public List<PilingImplement> findPilImplementAll(String value) throws Exception {
        return mPilingImplMapper.findPilImplementAll(value);
    }

    @Override
    public long findPilImplementCount(String value) throws Exception {
        return mPilingImplMapper.findPilImplementCount(value);
    }

    @Override
    public List<FinalResult> findPilModelInfoById(String value) throws Exception {
        return mPilingImplMapper.findPilModelInfoById(value);
    }

    @Override
    public List<FinalResult> findPilModelInfoByIdResult(String value) throws Exception {
        return mPilingImplMapper.findPilModelInfoByIdResult(value);
    }

    @Override
    public List<FinalResult> findPilModelInfoByIdResultBody(String value) throws Exception {
        return mPilingImplMapper.findPilModelInfoByIdResultBody(value);
    }
}
