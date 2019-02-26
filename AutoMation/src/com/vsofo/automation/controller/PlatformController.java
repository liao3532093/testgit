package com.vsofo.automation.controller;

import com.vsofo.automation.entity.*;
import com.vsofo.automation.service.PlatformInterfaceService;
import com.vsofo.automation.service.PlatformRequestService;
import com.vsofo.automation.service.PlatformService;
import com.vsofo.automation.utils.SessionUtils;
import com.vsofo.automation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by vsofo on 2017/4/19.
 * 平台管理控制器Controller
 */
@Controller
@RequestMapping("/PManage")
public class PlatformController {
    @Autowired
    private PlatformService mPlatformService;
    @Autowired
    private PlatformInterfaceService mPlatformInterfaceService;
    @Autowired
    private PlatformRequestService mPlatformRequestService;

    /***
     * 添加平台名称
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/addPlatformManage")
    @ResponseBody
    public String addPlatformManage(PmanageItem item, HttpServletRequest request) throws Exception {
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        item.setUserId(user.getId());
        String str = StringUtils.appendUrl(new String[]{item.getTitle(), item.getExeUrl(), String.valueOf(item.getUserId())});
        long count = mPlatformService.addPlatformManage(str);
        return String.valueOf(count);
    }

    /**
     * 按ID查询平台名称
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPlatformManageByIdToName")
    @ResponseBody
    public ResponseItem<PmanageItem> selectPlatformManageByIdToName(int id) throws Exception {
        ResponseItem<PmanageItem> response = new ResponseItem<>();
        String str = StringUtils.appendUrl(new String[]{String.valueOf(id)});
        PmanageItem result = mPlatformService.selectPlatformManageByIdToName(str);
        response.setObj(result);
        return response;
    }

    /***
     * 查询全部平台名称信息
     *
     * @param title
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPlatformManageAll")
    @ResponseBody
    public ResponseItem<PmanageItem> selectPlatformManageAll(String title, String page, String rows) throws Exception {
        ResponseItem<PmanageItem> response = new ResponseItem<>();
        title = title == null ? "" : title;
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        long count = mPlatformService.selectPlatformManageCount("");
        String str = StringUtils.appendUrl(new String[]{title, String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())});
        List<PmanageItem> items = mPlatformService.selectPlatformManageAll(str);
        response.setTotal(count);
        response.setRows(items);
        return response;
    }

    /**
     * 修改平台名称
     *
     * @param item
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatePlatformManageById")
    @ResponseBody
    public String updatePlatformManageById(PmanageItem item, HttpServletRequest request) throws Exception {
        String str = StringUtils.appendUrl(new String[]{String.valueOf(item.getId()), item.getTitle(), item.getExeUrl()});
        long count = mPlatformService.updatePlatformManageById(str);
        request.getSession().removeAttribute(SessionUtils.PLATFORMMANAGE);
        return String.valueOf(count);
    }

    /**
     * 禁用平台名称
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/disablePlatformManageById")
    @ResponseBody
    public String disablePlatformManageById(int id, HttpServletRequest request) throws Exception {
        String str = StringUtils.appendUrl(new String[]{String.valueOf(id)});
        long count = mPlatformService.disablePlatformManageById(str);
        request.getSession().removeAttribute(SessionUtils.PLATFORMMANAGE);
        return String.valueOf(count);
    }

    /**
     * 查询平台名称
     *
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPlatformManageByName")
    @ResponseBody
    public ResponseItem<PmanageItem> selectPlatformManageByName(int type) throws Exception {
        ResponseItem<PmanageItem> response = new ResponseItem<>();
        if (0 == type) {  //不过滤禁用
            String str = StringUtils.appendUrl(new String[]{"0", "500"});
            List<PmanageItem> items = mPlatformService.selectPlatformManageAll(str);
            response.setRows(items);
        } else {  //过滤禁用
            List<PmanageItem> items = mPlatformService.selectPlatformManageByName("");
            response.setRows(items);
        }
        return response;
    }

    /**
     * 按ID查询平台信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPlatInterfaceById")
    @ResponseBody
    public ResponseItem<PinterFaceItem> selectPlatInterfaceById(int id) throws Exception {
        ResponseItem<PinterFaceItem> response = new ResponseItem<>();
        String str = StringUtils.appendUrl(new String[]{String.valueOf(id)});
        PinterFaceItem item = mPlatformInterfaceService.selectPlatInterfaceById(str);
        response.setObj(item);
        return response;
    }

    /**
     * 保存平台接口
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/savePlatformInterface")
    @ResponseBody
    public String savePlatformInterface(PinterFaceItem item, HttpServletRequest request) throws Exception {
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        System.out.println(item);
        String str = StringUtils.appendUrl(new String[]{String.valueOf(item.getId()), item.getTitle(),
                String.valueOf(item.getPlatId()), item.getIsEnable(), String.valueOf(user.getId())});
        long count = mPlatformInterfaceService.savePlatformInterface(str);
        return String.valueOf(count);
    }

    /***
     * 分页查询所有接口
     *
     * @param page
     * @param rows
     * @param item
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPlatInterfaceAll")
    @ResponseBody
    public ResponseItem<PinterFaceItem> selectPlatInterfaceAll(String page, String rows, PinterFaceItem item, HttpServletRequest request) throws Exception {
        ResponseItem<PinterFaceItem> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String str = StringUtils.appendUrl(new String[]{String.valueOf(pageItem.getIndex()),
                String.valueOf(pageItem.getPageSize()), item.getPlatId() <= 0 ? "-1" : String.valueOf(item.getPlatId()),
                StringUtils.isEmpty(item.getTitle()) ? "-1" : item.getTitle()});
        long count = mPlatformInterfaceService.selectPlatInterfaceCount(str);
        List<PinterFaceItem> items = mPlatformInterfaceService.selectPlatInterfaceAll(str);
        response.setTotal(count);
        response.setRows(items);
        return response;
    }

    /***
     * 查询接口信息
     *
     * @param type
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectInterfaceByName")
    @ResponseBody
    public ResponseItem<PinterFaceItem> selectInterfaceByName(int type) throws Exception {
        ResponseItem<PinterFaceItem> response = new ResponseItem<>();
        String str = StringUtils.appendUrl(new String[]{String.valueOf(type)});
        List<PinterFaceItem> items = mPlatformInterfaceService.selectInterfaceByName(str);
        response.setRows(items);
        return response;
    }

    /**
     * 禁用启用平台接口
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/openCloseInterfaceById")
    @ResponseBody
    public String openCloseInterfaceById(int id) throws Exception {
        long count = mPlatformInterfaceService.openCloseInterfaceById(String.valueOf(id));
        return String.valueOf(count);
    }

    /**
     * 保存用例信息
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/savePlatRequestInfo")
    @ResponseBody
    public String savePlatRequestInfo(PrequestItem item, HttpServletRequest request) throws Exception {
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        if (item.getRequestType() != 1) {
            item.setParamType(-1);
        }
        String[] data = new String[]{
                String.valueOf(item.getPlatId()), String.valueOf(item.getInterfaceId()), item.getTitle(),
                item.getRequestUrl(), String.valueOf(item.getRequestType()), String.valueOf(item.getParamType()),
                item.getIsEnable(), String.valueOf(user.getId()), String.valueOf(item.getId())
        };
        String str = StringUtils.appendUrl(data);
        long count = mPlatformRequestService.savePlatRequestInfo(str);
        return String.valueOf(count);
    }

    /***
     * 分页查询所有用例信息
     *
     * @param page
     * @param rows
     * @param item
     * @return
     */
    @RequestMapping("/selectPlatRequestAll")
    @ResponseBody
    public ResponseItem<PrequestItem> selectPlatRequestAll(String type, String page, String rows, PrequestItem item) throws Exception {
        ResponseItem<PrequestItem> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String name = StringUtils.isEmpty(item.getTitle()) ? "" : item.getTitle();
        item.setTitle(name);
        String types = "0".equals(type) ? "y" : "n";
        String[] data = new String[]{
                String.valueOf(item.getPlatId()), String.valueOf(item.getInterfaceId()), item.getTitle(),
                types, String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        };
        String str = StringUtils.appendUrl(data);
        List<PrequestItem> items = mPlatformRequestService.selectPlatRequestAll(str);
        long count = mPlatformRequestService.selectPlatRequestCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /***
     * 按ID查询用例信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPlatRequestById")
    @ResponseBody
    public ResponseItem<PrequestItem> selectPlatRequestById(int id) throws Exception {
        ResponseItem<PrequestItem> response = new ResponseItem<>();
        String str = StringUtils.appendUrl(new String[]{String.valueOf(id)});
        PrequestItem item = mPlatformRequestService.selectPlatRequestById(str);
        response.setObj(item);
        return response;
    }

    /**
     * 设置所属模块ID
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/setModelID")
    @ResponseBody
    public String setModelID(int id, HttpServletRequest request) throws Exception {
        request.getSession().setAttribute("id", id);
        return "1";
    }

    /**
     * 查询所有请求模块的名称的ID
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectPlatRequestByNames")
    @ResponseBody
    public ResponseItem<PrequestItem> selectPlatRequestByNames(String platId, int type, HttpServletRequest request) throws Exception {
        ResponseItem<PrequestItem> response = new ResponseItem<>();
        System.out.println("platId--" + platId);
        List<PrequestItem> requestItems = mPlatformRequestService.selectPlatRequestByNames(platId);
        if (0 < type) {  //过滤
            HttpSession session = request.getSession();
            List<CaseModel> items = (List<CaseModel>) session.getAttribute(SessionUtils.CASEMODELINFO);
            if (items != null && items.size() > 0 && requestItems != null && requestItems.size() > 0) {
                for (CaseModel ca : items) {
                    for (PrequestItem it : requestItems) {
                        if (ca.getModelId() == it.getId()) {
                            requestItems.remove(it);
                            break;
                        }
                    }
                }
            }
        }
        response.setRows(requestItems);
        return response;
    }

    /**
     * 启用禁用请求模块
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/openClosePlatRequestById")
    @ResponseBody
    public String openClosePlatRequestById(int id) throws Exception {
        long count = mPlatformRequestService.openClosePlatRequestById(String.valueOf(id));
        return String.valueOf(count);
    }
}
