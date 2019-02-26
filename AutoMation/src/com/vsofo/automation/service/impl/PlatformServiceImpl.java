package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.PmanageItem;
import com.vsofo.automation.mapper.PlatformMapper;
import com.vsofo.automation.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by vsofo on 2017/4/19.
 */
public class PlatformServiceImpl implements PlatformService {
    @Autowired
    private PlatformMapper mPlatformMapper;

    @Override
    public long addPlatformManage(String message) throws Exception {
        return mPlatformMapper.addPlatformManage(message);
    }

    @Override
    public List<PmanageItem> selectPlatformManageAll(String message) throws Exception {
        return mPlatformMapper.selectPlatformManageAll(message);
    }

    @Override
    public long updatePlatformManageById(String message) throws Exception {
        return mPlatformMapper.updatePlatformManageById(message);
    }

    @Override
    public long disablePlatformManageById(String message) throws Exception {
        return mPlatformMapper.disablePlatformManageById(message);
    }

    @Override
    public List<PmanageItem> selectPlatformManageByName(String message) throws Exception {
        return mPlatformMapper.selectPlatformManageByName(message);
    }

    @Override
    public long selectPlatformManageCount(String message) throws Exception {
        return mPlatformMapper.selectPlatformManageCount(message);
    }

    @Override
    public PmanageItem selectPlatformManageByIdToName(String message) throws Exception {
        return mPlatformMapper.selectPlatformManageByIdToName(message);
    }
}
