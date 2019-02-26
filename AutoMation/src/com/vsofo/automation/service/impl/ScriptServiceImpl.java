package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.ScriptItem;
import com.vsofo.automation.mapper.ScriptMapper;
import com.vsofo.automation.service.ScriptService;
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
public class ScriptServiceImpl implements ScriptService {
    @Autowired
    private ScriptMapper mScriptMapper;

    @Override
    public List<ScriptItem> selectScriptMethodAll(String message) throws Exception {
        return mScriptMapper.selectScriptMethodAll(message);
    }

    @Override
    public long selectScriptMethodCount(String message) throws Exception {
        return mScriptMapper.selectScriptMethodCount(message);
    }

    @Override
    public long saveScriptMethod(String message) throws Exception {
        return mScriptMapper.saveScriptMethod(message);
    }

    @Override
    public ScriptItem selectScriptMethodById(String message) throws Exception {
        return mScriptMapper.selectScriptMethodById(message);
    }

    @Override
    public List<ScriptItem> selectScriptMethodByName(String message) throws Exception {
        return mScriptMapper.selectScriptMethodByName(message);
    }

    @Override
    public long openCloseScriptMethodById(String message) throws Exception {
        return mScriptMapper.openCloseScriptMethodById(message);
    }
}
