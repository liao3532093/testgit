package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.PilExaModel;
import com.vsofo.automation.entity.PilExample;
import com.vsofo.automation.mapper.PilExampleMapper;
import com.vsofo.automation.service.PilExampleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/6
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/6
 * @修改描述:
 */
public class PilExampleServiceImpl implements PilExampleService {
    @Autowired
    private PilExampleMapper mExampleMapper;

    @Override
    public long savePilExample(String value) throws Exception {
        return mExampleMapper.savePilExample(value);
    }

    @Override
    public List<PilExample> findPilExampleAll(String value) throws Exception {
        return mExampleMapper.findPilExampleAll(value);
    }

    @Override
    public long findPilExampleCount(String value) throws Exception {
        return mExampleMapper.findPilExampleCount(value);
    }

    @Override
    public long updateExaOpenClose(String value) throws Exception {
        return mExampleMapper.updateExaOpenClose(value);
    }

    @Override
    public PilExample findPilExampleById(String value) throws Exception {
        return mExampleMapper.findPilExampleById(value);
    }

    @Override
    public long savePilExampleModel(String value) throws Exception {
        return mExampleMapper.savePilExampleModel(value);
    }

    @Override
    public List<PilExaModel> findPilExampleModelAll(String value) throws Exception {
        return mExampleMapper.findPilExampleModelAll(value);
    }

    @Override
    public long findPilExampleModelCount(String value) throws Exception {
        return mExampleMapper.findPilExampleModelCount(value);
    }

    @Override
    public long updatePilExaModelOpenClose(String value) throws Exception {
        return mExampleMapper.updatePilExaModelOpenClose(value);
    }

    @Override
    public PilExaModel findPilExampleModelById(String value) throws Exception {
        return mExampleMapper.findPilExampleModelById(value);
    }

    @Override
    public List<PilExaModel> findPilExampleModelByResultId(String value) throws Exception {
        return mExampleMapper.findPilExampleModelByResultId(value);
    }

    @Override
    public long findPilExampleModelByResultCount(String value) throws Exception {
        return mExampleMapper.findPilExampleModelByResultCount(value);
    }

    @Override
    public long updatePilModelResult(String value) throws Exception {
        return mExampleMapper.updatePilModelResult(value);
    }

    @Override
    public long deletePilExampleModelById(String value) throws Exception {
        return mExampleMapper.deletePilExampleModelById(value);
    }
}
