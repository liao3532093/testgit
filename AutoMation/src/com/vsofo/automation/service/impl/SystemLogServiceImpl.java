package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.SystemLog;
import com.vsofo.automation.mapper.SystemLogMapper;
import com.vsofo.automation.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/10
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/10
 * @修改描述:
 */
public class SystemLogServiceImpl implements SystemLogService {
    @Autowired
    private SystemLogMapper mSystemLogMapper;

    @Override
    public long saveSystemLogInfo(String message) throws Exception {
        return mSystemLogMapper.saveSystemLogInfo(message);
    }

    @Override
    public List<SystemLog> selectSystemLogAll(String message) throws Exception {
        return mSystemLogMapper.selectSystemLogAll(message);
    }

    @Override
    public long selectSystemLogCount(String message) throws Exception {
        return mSystemLogMapper.selectSystemLogCount(message);
    }
}
