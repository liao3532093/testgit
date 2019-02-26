package com.vsofo.automation.mapper;

import com.vsofo.automation.entity.PowerClass;
import com.vsofo.automation.entity.PowerItem;
import com.vsofo.automation.entity.PowerList;
import com.vsofo.automation.entity.PowerParent;

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
public interface PermissionMapper {
    /**
     * 保存大类别权限
     *
     * @param message
     * @return
     * @throws Exception
     */
    long savePowerClassAll(String message) throws Exception;

    /**
     * 分页查询大类别权限
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PowerClass> selectPowerClassAll(String message) throws Exception;

    /**
     * 分页查询大类别权限个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectPowerClassCount(String message) throws Exception;

    /***
     *  按ID查询大类别权限
     * @param message
     * @return
     * @throws Exception
     */
    PowerClass selectPowerClassById(String message) throws Exception;

    /**
     * 查询没有禁用的大类别权限
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PowerClass> selectOpenPowerClass(String message) throws Exception;

    /**
     * 保存权限小类别
     *
     * @param message
     * @return
     * @throws Exception
     */
    long savePowerParentInfo(String message) throws Exception;

    /**
     * 分页查询权限小类别
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PowerParent> selectPowerParentAll(String message) throws Exception;

    /**
     * 分页查询权限小类别个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectPowerParentCount(String message) throws Exception;

    /**
     * 按ID查询权限小类别
     *
     * @param message
     * @return
     * @throws Exception
     */
    PowerParent selectPowerParentById(String message) throws Exception;

    /**
     * 查询没有禁用的权限小类别
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PowerParent> selectOpenPowerParent(String message) throws Exception;

    /**
     * 保存权限关键字
     *
     * @param message
     * @return
     * @throws Exception
     */
    long savePowerItemInfo(String message) throws Exception;

    /**
     * 分页查询权限关键字
     *
     * @param message
     * @return
     * @throws Exception
     */
    List<PowerItem> selectPowerItemAll(String message) throws Exception;

    /**
     * 分页查询权限关键字个数
     *
     * @param message
     * @return
     * @throws Exception
     */
    long selectPowerItemCount(String message) throws Exception;

    /**
     * 按ID查询权限关键字
     *
     * @param message
     * @return
     * @throws Exception
     */
    PowerItem selectPowerItemById(String message) throws Exception;

    /**
     * 查询用户权限
     *
     * @param message
     * @return
     * @throws Exception
     */
    PowerList selectPowerListAll(String message) throws Exception;

    /**
     * 保存用户权限
     *
     * @param message
     * @return
     * @throws Exception
     */
    long savePowerListInfo(String message) throws Exception;
}
