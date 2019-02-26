package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.PilExpResult;
import com.vsofo.automation.entity.PilingModel;
import com.vsofo.automation.mapper.PilingModelMapper;
import com.vsofo.automation.service.PilingModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/5
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/5
 * @修改描述:
 */
public class PilingModelServiceImpl implements PilingModelService {
    @Autowired
    private PilingModelMapper modelMapper;

    @Override
    public long savePilingModelInfo(String value) throws Exception {
        return modelMapper.savePilingModelInfo(value);
    }

    @Override
    public List<PilingModel> selectPilingModelAll(String value) throws Exception {
        return modelMapper.selectPilingModelAll(value);
    }

    @Override
    public long selectPilingModelCount(String value) throws Exception {
        return modelMapper.selectPilingModelCount(value);
    }

    @Override
    public long updateOpenClose(String value) throws Exception {
        return modelMapper.updateOpenClose(value);
    }

    @Override
    public PilingModel selectPilingModelById(String value) throws Exception {
        return modelMapper.selectPilingModelById(value);
    }

    @Override
    public List<PilingModel> findPilingModelAll(String value) throws Exception {
        return modelMapper.findPilingModelAll(value);
    }

    @Override
    public long savePilModelExpResult(String value) throws Exception {
        return modelMapper.savePilModelExpResult(value);
    }

    @Override
    public List<PilExpResult> selectPilModelExpResultAll(String value) throws Exception {
        return modelMapper.selectPilModelExpResultAll(value);
    }

    @Override
    public long selectPilModelExpResultCount(String value) throws Exception {
        return modelMapper.selectPilModelExpResultCount(value);
    }

    @Override
    public long updateModelOpenClose(String value) throws Exception {
        return modelMapper.updateModelOpenClose(value);
    }

    @Override
    public PilExpResult selectPilModelExpResultById(String value) throws Exception {
        return modelMapper.selectPilModelExpResultById(value);
    }

    @Override
    public List<PilExpResult> selectExpModelIdByExpResult(String value) throws Exception {
        return modelMapper.selectExpModelIdByExpResult(value);
    }
}
