package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.ExampleItem;
import com.vsofo.automation.mapper.ExampleMapper;
import com.vsofo.automation.service.ExampleService;
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
public class ExampleServiceImpl implements ExampleService {
    @Autowired
    private ExampleMapper mExampleMapper;

    @Override
    public List<ExampleItem> selectCaseDetailAll(String message) throws Exception {
        return mExampleMapper.selectCaseDetailAll(message);
    }

    @Override
    public long selectCaseDetailCount(String message) throws Exception {
        return mExampleMapper.selectCaseDetailCount(message);
    }

    @Override
    public long saveCaseDetailInfo(String message) throws Exception {
        return mExampleMapper.saveCaseDetailInfo(message);
    }

    @Override
    public ExampleItem selectCaseDetailById(String message) throws Exception {
        return mExampleMapper.selectCaseDetailById(message);
    }

    @Override
    public long openCloseCaseDetailById(String message) throws Exception {
        return mExampleMapper.openCloseCaseDetailById(message);
    }
}
