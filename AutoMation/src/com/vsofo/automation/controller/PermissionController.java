package com.vsofo.automation.controller;

import com.alibaba.fastjson.JSON;
import com.vsofo.automation.entity.*;
import com.vsofo.automation.service.PermissionService;
import com.vsofo.automation.service.SystemLogService;
import com.vsofo.automation.utils.JurisdictionUtils;
import com.vsofo.automation.utils.SessionUtils;
import com.vsofo.automation.utils.StringUtils;
import com.vsofo.automation.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/5
 * @类描述: 权限类别控制器Controller
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/5
 * @修改描述:
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService mPermissionService;
    @Autowired
    private SystemLogService mSystemLogService;

    /**
     * 保存大类别权限
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/savePowerClassAll")
    @ResponseBody
    public String savePowerClassAll(PowerClass item, HttpServletRequest request) throws Exception {
        String str = StringUtils.appendUrl(new String[]{
                item.getTitle(), StringUtils.isEmpty(item.getIsLock()) ? "0" : "1",
                String.valueOf(item.getOrderNo()),
                String.valueOf(item.getId())
        });
        long count = mPermissionService.savePowerClassAll(str);
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String pers = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId()), user.getShowName() + "更新权限" + JSON.toJSONString(item),
                JurisdictionUtils.JURISDICTIONLOG, Utils.getIpConfig(request)
        });
        mSystemLogService.saveSystemLogInfo(pers);
        return String.valueOf(count);
    }

    /**
     * 分页查询大类别权限
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPowerClassAll")
    @ResponseBody
    public ResponseItem<PowerClass> selectPowerClassAll(String page, String rows) throws Exception {
        ResponseItem<PowerClass> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String str = StringUtils.appendUrl(new String[]{
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        });
        List<PowerClass> items = mPermissionService.selectPowerClassAll(str);
        long count = mPermissionService.selectPowerClassCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 按ID查询大类别权限
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPowerClassById")
    @ResponseBody
    public ResponseItem<PowerClass> selectPowerClassById(int id) throws Exception {
        ResponseItem<PowerClass> response = new ResponseItem<>();
        PowerClass item = mPermissionService.selectPowerClassById(String.valueOf(id));
        response.setObj(item);
        return response;
    }

    /**
     * 查询没有禁用的大类别权限
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectOpenPowerClass")
    @ResponseBody
    public String selectOpenPowerClass(int id, HttpServletRequest request) throws Exception {
        request.getSession().setAttribute(SessionUtils.POWERID, id);
        List<PowerClass> items = mPermissionService.selectOpenPowerClass("");
        request.getSession().setAttribute(SessionUtils.POWERCLASS, items);
        return "1";
    }

    /**
     * 保存权限小类别
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/savePowerParentInfo")
    @ResponseBody
    public String savePowerParentInfo(PowerParent item, HttpServletRequest request) throws Exception {
        if (item.getPowerClassId() <= 0 && request.getSession().getAttribute(SessionUtils.POWERID) != null) {
            int id = (int) request.getSession().getAttribute(SessionUtils.POWERID);
            item.setPowerClassId(id);
        }
        String str = StringUtils.appendUrl(new String[]{
                item.getTitle(), StringUtils.isEmpty(item.getIsLock()) ? "0" : "1",
                String.valueOf(item.getOrderNo()), String.valueOf(item.getPowerClassId()),
                String.valueOf(item.getId())
        });
        long count = mPermissionService.savePowerParentInfo(str);
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String pers = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId()), user.getShowName() + "更新权限" + JSON.toJSONString(item),
                JurisdictionUtils.JURISDICTIONLOG, Utils.getIpConfig(request)
        });
        mSystemLogService.saveSystemLogInfo(pers);
        return String.valueOf(count);
    }

    /**
     * 分页查询权限小类别
     *
     * @param page
     * @param rows
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPowerParentAll")
    @ResponseBody
    public ResponseItem<PowerParent> selectPowerParentAll(String page, String rows, PowerParent item, HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute(SessionUtils.POWERID) != null && item.getPowerClassId() <= 0) {
            int id = (int) request.getSession().getAttribute(SessionUtils.POWERID);
            item.setPowerClassId(id);
        }
        ResponseItem<PowerParent> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt(rows));
        String str = StringUtils.appendUrl(new String[]{
                String.valueOf(item.getPowerClassId()), String.valueOf(pageItem.getIndex()),
                String.valueOf(pageItem.getPageSize())
        });
        List<PowerParent> items = mPermissionService.selectPowerParentAll(str);
        long count = mPermissionService.selectPowerParentCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 按ID查询权限小类别
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPowerParentById")
    @ResponseBody
    public ResponseItem<PowerParent> selectPowerParentById(int id) throws Exception {
        ResponseItem<PowerParent> response = new ResponseItem<>();
        PowerParent item = mPermissionService.selectPowerParentById(String.valueOf(id));
        response.setObj(item);
        return response;
    }

    /**
     * 查询没有禁用的权限小类别
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectOpenPowerParent")
    @ResponseBody
    public String selectOpenPowerParent(int id, int oid, HttpServletRequest request) throws Exception {
        request.getSession().setAttribute(SessionUtils.POWERID, oid);
        request.getSession().removeAttribute(SessionUtils.POWERCLASS);
        List<PowerParent> items = mPermissionService.selectOpenPowerParent(String.valueOf(id));
        request.getSession().setAttribute(SessionUtils.POWERCLASS, items);
        return "1";
    }

    /**
     * 保存权限关键字
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/savePowerItemInfo")
    @ResponseBody
    public String savePowerItemInfo(PowerItem item, HttpServletRequest request) throws Exception {
        if (item.getParentClassId() <= 0) {
            int id = (int) request.getSession().getAttribute(SessionUtils.POWERID);
            item.setParentClassId(id);
        }
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String str = StringUtils.appendUrl(new String[]{
                String.valueOf(item.getParentClassId()), item.getKeywords(),
                item.getTitle(), String.valueOf(item.getId())
        });
        long count = mPermissionService.savePowerItemInfo(str);
        String strs = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId()), user.getShowName() + "更新关键字" + JSON.toJSONString(item),
                JurisdictionUtils.JURISDICTIONLOG,Utils.getIpConfig(request)
        });
        mSystemLogService.saveSystemLogInfo(strs);
        return String.valueOf(count);
    }

    /**
     * 分页查询权限关键字
     *
     * @param page
     * @param rows
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPowerItemAll")
    @ResponseBody
    public ResponseItem<PowerItem> selectPowerItemAll(String page, String rows, PowerItem item, HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute(SessionUtils.POWERID) != null && item.getParentClassId() <= 0) {
            int oid = (int) request.getSession().getAttribute(SessionUtils.POWERID);
            item.setParentClassId(oid);
        }
        ResponseItem<PowerItem> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt(rows));
        String str = StringUtils.appendUrl(new String[]{
                String.valueOf(item.getParentClassId()), String.valueOf(pageItem.getIndex()),
                String.valueOf(pageItem.getPageSize())
        });
        List<PowerItem> items = mPermissionService.selectPowerItemAll(str);
        long count = mPermissionService.selectPowerItemCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 按ID查询权限关键字
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPowerItemById")
    @ResponseBody
    public ResponseItem<PowerItem> selectPowerItemById(int id) throws Exception {
        ResponseItem<PowerItem> response = new ResponseItem<>();
        PowerItem item = mPermissionService.selectPowerItemById(String.valueOf(id));
        response.setObj(item);
        return response;
    }
}
