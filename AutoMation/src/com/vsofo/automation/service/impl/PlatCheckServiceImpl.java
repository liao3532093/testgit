package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.ExecuteItem;
import com.vsofo.automation.mapper.PlatCheckMapper;
import com.vsofo.automation.service.PlatCheckService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/4/26
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/4/26
 * @修改描述:
 */
public class PlatCheckServiceImpl implements PlatCheckService {
    @Autowired
    private PlatCheckMapper mPlatCheckMapper;

    @Override
    public List<ExecuteItem> selectPlatCheckAll(String message) throws Exception {
        return mPlatCheckMapper.selectPlatCheckAll(message);
    }

    @Override
    public long selectPlatCheckCount(String message) throws Exception {
        return mPlatCheckMapper.selectPlatCheckCount(message);
    }

    @Override
    public long savePlatCheckInfo(String message) throws Exception {
        return mPlatCheckMapper.savePlatCheckInfo(message);
    }

    @Override
    public long startClosePlatCheck(String message) throws Exception {
        return mPlatCheckMapper.startClosePlatCheck(message);
    }

    @Override
    public ExecuteItem selectPlatCheckById(String message) throws Exception {
        return mPlatCheckMapper.selectPlatCheckById(message);
    }

    @Override
    public List<ExecuteItem> selectCheckScriptByPerd(String message) throws Exception {
        return mPlatCheckMapper.selectCheckScriptByPerd(message);
    }
}
