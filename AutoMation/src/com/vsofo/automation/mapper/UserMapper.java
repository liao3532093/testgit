package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.UserItem;
import sun.rmi.runtime.Log;

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
public interface UserMapper {
    /**
     * 保存用户信息
     *
     * @param message
     * @return
     * @throws Exception
     */
    long saveUserInfo(String message) throws Exception;

    /**
     * 分页查询用户信息
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<UserItem> selectUserInfoAll(String message) throws Exception;

    /**
     * 分页查询用户信息个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectUserInfoCount(String message) throws Exception;

    /**
     * 按ID查询用户信息
     *
     * @param message
     * @return
     * @throws Exception
     */
    UserItem selectUserInfoById(String message) throws Exception;

    /**
     * 启用禁用
     *
     * @param message
     * @return
     * @throws Exception
     */
    long openCloseUserInfoById(String message) throws Exception;

    /**
     * 用户登录
     *
     * @param message
     * @return
     * @throws Exception
     */
    UserItem userLogin(String message) throws Exception;

    /**
     * 修改用户信息
     *
     * @param message
     * @return
     * @throws Exception
     */
    long updateUserInfo(String message) throws Exception;

    /**
     * 修改sessionid
     *
     * @param message
     * @return
     * @throws Exception
     */
    long updateUserSessionId(String message) throws Exception;

    /**
     * 获取已登录状态的session_id
     *
     * @param message
     * @return
     * @throws Exception
     */
    String selectUserSessionId(String message) throws Exception;
}
