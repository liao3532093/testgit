package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.PinterFaceItem;
import com.vsofo.automation.mapper.PlatformInterfaceMapper;
import com.vsofo.automation.service.PlatformInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by vsofo on 2017/4/20.
 */
public class PlatformInterfaceServiceImpl implements PlatformInterfaceService {
    @Autowired
    private PlatformInterfaceMapper mPlatformInterfaceMapper;

    @Override
    public long savePlatformInterface(String message) throws Exception {
        return mPlatformInterfaceMapper.savePlatformInterface(message);
    }

    @Override
    public List<PinterFaceItem> selectPlatInterfaceAll(String message) throws Exception {
        return mPlatformInterfaceMapper.selectPlatInterfaceAll(message);
    }

    @Override
    public long selectPlatInterfaceCount(String message) throws Exception {
        return mPlatformInterfaceMapper.selectPlatInterfaceCount(message);
    }

    @Override
    public PinterFaceItem selectPlatInterfaceById(String message) throws Exception {
        return mPlatformInterfaceMapper.selectPlatInterfaceById(message);
    }

    @Override
    public List<PinterFaceItem> selectInterfaceByName(String message) throws Exception {
        return mPlatformInterfaceMapper.selectInterfaceByName(message);
    }

    @Override
    public long openCloseInterfaceById(String message) throws Exception {
        return mPlatformInterfaceMapper.openCloseInterfaceById(message);
    }
}
