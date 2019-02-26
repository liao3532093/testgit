package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.PrequestItem;
import com.vsofo.automation.mapper.PlatformRequestMapper;
import com.vsofo.automation.service.PlatformRequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/24
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/24
 * @修改描述:
 */
public class PlatformRequestServiceImpl implements PlatformRequestService {
    @Autowired
    private PlatformRequestMapper mPlatformRequestMapper;

    @Override
    public long savePlatRequestInfo(String message) throws Exception {
        return mPlatformRequestMapper.savePlatRequestInfo(message);
    }

    @Override
    public List<PrequestItem> selectPlatRequestAll(String message) throws Exception {
        return mPlatformRequestMapper.selectPlatRequestAll(message);
    }

    @Override
    public long selectPlatRequestCount(String message) throws Exception {
        return mPlatformRequestMapper.selectPlatRequestCount(message);
    }

    @Override
    public PrequestItem selectPlatRequestById(String message) throws Exception {
        return mPlatformRequestMapper.selectPlatRequestById(message);
    }

    @Override
    public List<PrequestItem> selectPlatRequestByNames(String message) throws Exception {
        return mPlatformRequestMapper.selectPlatRequestByNames(message);
    }

    @Override
    public long openClosePlatRequestById(String message) throws Exception {
        return mPlatformRequestMapper.openClosePlatRequestById(message);
    }
}
