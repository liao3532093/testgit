package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.LogType;
import com.vsofo.automation.mapper.LogTypeMapper;
import com.vsofo.automation.service.LogTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/9
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/9
 * @修改描述:
 */
public class LogTypeServiceImpl implements LogTypeService {
    @Autowired
    private LogTypeMapper mLogTypeMapper;

    @Override
    public long saveLogTypeInfo(String message) throws Exception {
        return mLogTypeMapper.saveLogTypeInfo(message);
    }

    @Override
    public List<LogType> selectLogTypeAll(String message) throws Exception {
        return mLogTypeMapper.selectLogTypeAll(message);
    }

    @Override
    public long selectLogTypeCount(String message) throws Exception {
        return mLogTypeMapper.selectLogTypeCount(message);
    }

    @Override
    public LogType selectLogTypeById(String message) throws Exception {
        return mLogTypeMapper.selectLogTypeById(message);
    }

    @Override
    public long deleteLogTypeById(String message) throws Exception {
        return mLogTypeMapper.deleteLogTypeById(message);
    }
}
