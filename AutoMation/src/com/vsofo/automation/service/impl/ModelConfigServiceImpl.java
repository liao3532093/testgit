package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.ModelConfig;
import com.vsofo.automation.mapper.ModelConfigMapper;
import com.vsofo.automation.service.ModelConfigService;
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
public class ModelConfigServiceImpl implements ModelConfigService {
    @Autowired
    private ModelConfigMapper mModelConfigMapper;

    @Override
    public long saveModelConfigInfo(String message) throws Exception {
        return mModelConfigMapper.saveModelConfigInfo(message);
    }

    @Override
    public long removeModelConfigById(String message) throws Exception {
        return mModelConfigMapper.removeModelConfigById(message);
    }

    @Override
    public List<ModelConfig> selectModelConfigByMid(String message) throws Exception {
        return mModelConfigMapper.selectModelConfigByMid(message);
    }
}
