package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.RequestModular;
import com.vsofo.automation.mapper.RequestModularMapper;
import com.vsofo.automation.service.RequestModularService;
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
public class RequestModularServiceImpl implements RequestModularService {
    @Autowired
    private RequestModularMapper mRequestModularMapper;

    @Override
    public List<RequestModular> selectModlarAll(String message) throws Exception {
        return mRequestModularMapper.selectModlarAll(message);
    }

    @Override
    public long selectModlarCount(String message) throws Exception {
        return mRequestModularMapper.selectModlarCount(message);
    }

    @Override
    public long saveModlarInfo(String message) throws Exception {
        return mRequestModularMapper.saveModlarInfo(message);
    }

    @Override
    public long startCloseModlar(String message) throws Exception {
        return mRequestModularMapper.startCloseModlar(message);
    }

    @Override
    public RequestModular selectModlarById(String message) throws Exception {
        return mRequestModularMapper.selectModlarById(message);
    }

    @Override
    public List<RequestModular> selectModlarByIds(String message) throws Exception {
        return mRequestModularMapper.selectModlarByIds(message);
    }

    @Override
    public List<RequestModular> selectModlarByMid(String message) throws Exception {
        return mRequestModularMapper.selectModlarByMid(message);
    }

    @Override
    public long unifiedUpdateModlarInfo(String message) throws Exception {
        return mRequestModularMapper.unifiedUpdateModlarInfo(message);
    }
}
