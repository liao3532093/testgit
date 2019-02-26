package com.vsofo.automation.service.impl;

import com.vsofo.automation.entity.PowerClass;
import com.vsofo.automation.entity.PowerItem;
import com.vsofo.automation.entity.PowerList;
import com.vsofo.automation.entity.PowerParent;
import com.vsofo.automation.mapper.PermissionMapper;
import com.vsofo.automation.service.PermissionService;
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
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper mPermissionMapper;

    @Override
    public long savePowerClassAll(String message) throws Exception {
        return mPermissionMapper.savePowerClassAll(message);
    }

    @Override
    public List<PowerClass> selectPowerClassAll(String message) throws Exception {
        return mPermissionMapper.selectPowerClassAll(message);
    }

    @Override
    public long selectPowerClassCount(String message) throws Exception {
        return mPermissionMapper.selectPowerClassCount(message);
    }

    @Override
    public PowerClass selectPowerClassById(String message) throws Exception {
        return mPermissionMapper.selectPowerClassById(message);
    }

    @Override
    public List<PowerClass> selectOpenPowerClass(String message) throws Exception {
        return mPermissionMapper.selectOpenPowerClass(message);
    }

    @Override
    public long savePowerParentInfo(String message) throws Exception {
        return mPermissionMapper.savePowerParentInfo(message);
    }

    @Override
    public List<PowerParent> selectPowerParentAll(String message) throws Exception {
        return mPermissionMapper.selectPowerParentAll(message);
    }

    @Override
    public long selectPowerParentCount(String message) throws Exception {
        return mPermissionMapper.selectPowerParentCount(message);
    }

    @Override
    public PowerParent selectPowerParentById(String message) throws Exception {
        return mPermissionMapper.selectPowerParentById(message);
    }

    @Override
    public List<PowerParent> selectOpenPowerParent(String message) throws Exception {
        return mPermissionMapper.selectOpenPowerParent(message);
    }

    @Override
    public long savePowerItemInfo(String message) throws Exception {
        return mPermissionMapper.savePowerItemInfo(message);
    }

    @Override
    public List<PowerItem> selectPowerItemAll(String message) throws Exception {
        return mPermissionMapper.selectPowerItemAll(message);
    }

    @Override
    public long selectPowerItemCount(String message) throws Exception {
        return mPermissionMapper.selectPowerItemCount(message);
    }

    @Override
    public PowerItem selectPowerItemById(String message) throws Exception {
        return mPermissionMapper.selectPowerItemById(message);
    }

    @Override
    public PowerList selectPowerListAll(String message) throws Exception {
        return mPermissionMapper.selectPowerListAll(message);
    }

    @Override
    public long savePowerListInfo(String message) throws Exception {
        return mPermissionMapper.savePowerListInfo(message);
    }
}
