package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.ImplementInfo;
import com.vsofo.automation.mapper.ImplementMapper;
import com.vsofo.automation.service.ImplementService;
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
public class ImplementServiceImpl implements ImplementService {
    @Autowired
    private ImplementMapper mImplementMapper;

    @Override
    public List<ImplementInfo> selectImplementInfoAll(String message) throws Exception {
        return mImplementMapper.selectImplementInfoAll(message);
    }

    @Override
    public long selectImplementInfoCount(String message) throws Exception {
        return mImplementMapper.selectImplementInfoCount(message);
    }

    @Override
    public List<String> implCaseId30All(String message) throws Exception {
        return mImplementMapper.implCaseId30All(message);
    }
}
