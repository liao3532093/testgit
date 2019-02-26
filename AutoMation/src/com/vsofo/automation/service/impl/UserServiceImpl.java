package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.UserItem;
import com.vsofo.automation.mapper.UserMapper;
import com.vsofo.automation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/5
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/5
 * @修改描述:
 */
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mUserMapper;

    @Override
    public long saveUserInfo(String message) throws Exception {
        return mUserMapper.saveUserInfo(message);
    }

    @Override
    public List<UserItem> selectUserInfoAll(String message) throws Exception {
        return mUserMapper.selectUserInfoAll(message);
    }

    @Override
    public long selectUserInfoCount(String message) throws Exception {
        return mUserMapper.selectUserInfoCount(message);
    }

    @Override
    public UserItem selectUserInfoById(String message) throws Exception {
        return mUserMapper.selectUserInfoById(message);
    }

    @Override
    public long openCloseUserInfoById(String message) throws Exception {
        return mUserMapper.openCloseUserInfoById(message);
    }

    @Override
    public UserItem userLogin(String message) throws Exception {
        return mUserMapper.userLogin(message);
    }

    @Override
    public long updateUserInfo(String message) throws Exception {
        return mUserMapper.updateUserInfo(message);
    }

    @Override
    public long updateUserSessionId(String message) throws Exception {
        return mUserMapper.updateUserSessionId(message);
    }

    @Override
    public String selectUserSessionId(String message) throws Exception {
        return mUserMapper.selectUserSessionId(message);
    }
}
