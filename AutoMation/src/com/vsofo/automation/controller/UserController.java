package com.vsofo.automation.controller;

import com.alibaba.fastjson.JSON;
import com.vsofo.automation.action.SessionAction;
import com.vsofo.automation.entity.*;
import com.vsofo.automation.service.PermissionService;
import com.vsofo.automation.service.SystemLogService;
import com.vsofo.automation.service.UserService;
import com.vsofo.automation.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/5
 * @类描述: 用户信息控制器Controller
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/5
 * @修改描述:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService mUserService;
    @Autowired
    private PermissionService mPermissionService;
    @Autowired
    private SystemLogService mSystemLogService;

    public UserController() {
        SessionAction.setListener(id -> {
            String str = new StringUtils().appendUrl(new String[]{
                    "", String.valueOf(id)
            });
            mUserService.updateUserSessionId(str);
        });
    }

    /**
     * 保存用户信息
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveUserInfo")
    @ResponseBody
    public String saveUserInfo(UserItem item, HttpServletRequest request) throws Exception {
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String str = "";
        String strs = "";
        if (!StringUtils.isEmpty(item.getAccount())) {
            str = StringUtils.appendUrl(new String[]{
                    item.getAccount(), item.getPassword(), item.getShowName(),
                    String.valueOf(item.getId())
            });
        } else {
            str = StringUtils.appendUrl(new String[]{
                    item.getOldPass(), item.getNewPass(), item.getShowName(),
                    String.valueOf(item.getId())
            });
        }
        long count = mUserService.saveUserInfo(str);
        strs = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId()),
                user.getShowName() + "更新了" + item.getShowName() + "的信息",
                JurisdictionUtils.USERLOG, Utils.getIpConfig(request)
        });
        mSystemLogService.saveSystemLogInfo(strs);
        return String.valueOf(count);
    }

    /**
     * 分页查询用户信息
     *
     * @param page
     * @param rows
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectUserInfoAll")
    @ResponseBody
    public ResponseItem<UserItem> selectUserInfoAll(String page, String rows, UserItem item, HttpServletRequest request) throws Exception {
        ResponseItem<UserItem> response = new ResponseItem<>();
        PageItem pageItem = new PageItem(Integer.parseInt(page), Integer.parseInt("10".equals(rows) ? "50" : rows));
        String str = StringUtils.appendUrl(new String[]{
                StringUtils.isEmpty(item.getShowName()) ? "" : item.getShowName(),
                String.valueOf(pageItem.getIndex()), String.valueOf(pageItem.getPageSize())
        });
        List<UserItem> items = mUserService.selectUserInfoAll(str);
        long count = mUserService.selectUserInfoCount(str);
        response.setRows(items);
        response.setTotal(count);
        return response;
    }

    /**
     * 按ID查询用户信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/selectUserInfoById")
    @ResponseBody
    public ResponseItem<UserItem> selectUserInfoById(int id) throws Exception {
        ResponseItem<UserItem> response = new ResponseItem<>();
        UserItem item = mUserService.selectUserInfoById(String.valueOf(id));
        response.setObj(item);
        return response;
    }

    /**
     * 启用禁用
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/openCloseUserInfoById")
    @ResponseBody
    public String openCloseUserInfoById(int id) throws Exception {
        long count = mUserService.openCloseUserInfoById(String.valueOf(id));
        return String.valueOf(count);
    }

    /**
     * 加载权限信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/loadPermissionInfo")
    @ResponseBody
    public String loadPermissionInfo(HttpServletRequest request) throws Exception {
        List<PowerClass> items = mPermissionService.selectOpenPowerClass("");
        for (PowerClass it : items) {
            List<PowerParent> its = mPermissionService.selectOpenPowerParent(String.valueOf(it.getId()));
            it.setData(its);
        }
        request.getSession().setAttribute(SessionUtils.POWERCLASS, items);
        return "1";
    }

    /**
     * 添加用户权限
     *
     * @param id
     * @param pId
     * @param permission
     * @return
     * @throws Exception
     */
    @RequestMapping("/addUserPermission")
    @ResponseBody
    public String addUserPermission(int id, String pId, String permission) throws Exception {
        List<PermissionType> lss = new ArrayList<>();
        List<PermissionType> list = null;
        List<PowerItem> items = JSON.parseArray(permission, PowerItem.class);
        PowerList powerList = mPermissionService.selectPowerListAll(String.valueOf(id));
        if (powerList == null || StringUtils.isEmpty(powerList.getPowerName())) {
            list = new ArrayList<>();
            if (items.size() > 0) {
                PermissionType permissionType = new PermissionType();
                permissionType.setId(pId);
                List<String> mList = new ArrayList<>();
                for (PowerItem it : items) {
                    mList.add(it.getKeywords());
                }
                permissionType.setName(mList);
                list.add(permissionType);
                System.out.println("添加权限" + permissionType);
            }
        } else {
            list = JSON.parseArray(powerList.getPowerName(), PermissionType.class);
            if (items.size() > 0) {
                boolean flag = false;
                System.out.println(list);
                for (PermissionType type : list) {
                    if (pId.equals(type.getId())) {
                        type.getName().clear();
                        for (PowerItem it : items) {
                            type.getName().add(it.getKeywords());
                        }
                        lss.add(type);
                        flag = false;
                        System.out.println("修改权限" + type);
                        break;
                    } else {
                        flag = true;
                    }
                }
                if (flag) {
                    PermissionType mType = new PermissionType();
                    mType.setId(pId);
                    List<String> mList = new ArrayList<>();
                    for (PowerItem it : items) {
                        mList.add(it.getKeywords());
                    }
                    mType.setName(mList);
                    lss.add(mType);
                    System.out.println("追加权限" + mType);
                }
            }
            for (PermissionType tys : list) {
                if (Integer.parseInt(pId) != Integer.parseInt(tys.getId())) {
                    lss.add(tys);
                }
            }
            list.clear();
            list.addAll(lss);
        }
        String str = StringUtils.appendUrl(new String[]{
                String.valueOf(id), list.size() > 0 ? JSON.toJSONString(list) : ""
        });
        System.out.println(str);
        long count = mPermissionService.savePowerListInfo(str);
        return String.valueOf(count);
    }

    /**
     * 加载用户权限
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/loadUserPermission")
    @ResponseBody
    public ResponseItem<List<Integer>> loadUserPermission(int id, int cid, HttpServletRequest request) throws Exception {
        List<Integer> ids = null;
        request.getSession().removeAttribute(SessionUtils.POWERLIST);
        ResponseItem<List<Integer>> response = new ResponseItem<>();
        /*加载用户权限*/
        PowerList powerList = mPermissionService.selectPowerListAll(String.valueOf(id));
        String str = StringUtils.appendUrl(new String[]{
                String.valueOf(cid), "0", "1000"
        });
        List<PowerItem> items = mPermissionService.selectPowerItemAll(str);
        if (powerList != null && items != null && items.size() > 0 && !StringUtils.isEmpty(powerList.getPowerName())) {
            ids = new ArrayList<>();
            List<PermissionType> permissionTypes = JSON.parseArray(powerList.getPowerName(), PermissionType.class);
            System.out.println("items***" + items);
            System.out.println("permissionTypes***" + permissionTypes);
            String scid = String.valueOf(items.get(0).getParentClassId());
            for (PermissionType type : permissionTypes) {
                if (scid.equals(type.getId())) {
                    for (int i = 0; i < items.size(); i++) {
                        for (String s : type.getName()) {
                            if (items.get(i).getKeywords().equals(s)) {
                                ids.add(i);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(ids);
        response.setObj(ids);
        return response;
    }

    /**
     * 用户登录
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(UserItem item, HttpServletRequest request) throws Exception {
        String str = new StringUtils().appendUrl(new String[]{
                item.getAccount(), item.getPassword(), Utils.getIpConfig(request)
        });
        try {
            UserItem user = mUserService.userLogin(str);
            if (user == null) {
                return "-1";
            } else {
                //                String sid = request.getSession().getId();
                request.getSession().setAttribute(SessionUtils.USER, user);
                //                if (StringUtils.isEmpty(user.getSessionId()) || sid.equals(user.getSessionId())) {
                //                    str = new StringUtils().appendUrl(new String[]{
                //                            sid, String.valueOf(user.getId())
                //                    });
                //                    mUserService.updateUserSessionId(str);
                //                    SystemLog log = new SystemLog();
                //                    log.setUserId(user.getId());
                //                    log.setInfo(user.getShowName() + "登录后台");
                //                    log.setLogName(JurisdictionUtils.LOGINLOG);
                //                    String strs = StringUtils.appendUrl(new String[]{
                //                            String.valueOf(log.getUserId()), log.getInfo(),
                //                            log.getLogName()
                //                    });
                //                    mSystemLogService.saveSystemLogInfo(strs);
                //                    return "1";
                //                } else {
                //                    return "-2";
                //                }
                return "1";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    /***
     * 验证用户权限
     * @param request
     * @return
     */
    @RequestMapping("/isPermission")
    @ResponseBody
    public String isPermission(HttpServletRequest request) {
        UserItem userItem = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        if (userItem == null) {
            return "1";
        }
        return "-1";
    }

    /***
     *  取得当前用户信息
     * @return
     * @throws Exception
     */
    @RequestMapping("/getUserItem")
    @ResponseBody
    public ResponseItem<UserItem> getUserItem(HttpServletRequest request) throws Exception {
        ResponseItem<UserItem> response = new ResponseItem<>();
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        response.setObj(user);
        return response;
    }

    /**
     * 修改用户信息
     *
     * @param item
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public String updateUserInfo(UserItem item, HttpServletRequest request) throws Exception {
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String str = StringUtils.appendUrl(new String[]{
                item.getPassword(), item.getNewPass(),
                item.getShowName(), String.valueOf(item.getId())
        });
        long count = mUserService.updateUserInfo(str);
        String strs = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId()),
                user.getShowName() + "修改了" + item.getShowName() + "的信息",
                JurisdictionUtils.USERLOG,Utils.getIpConfig(request)
        });
        mSystemLogService.saveSystemLogInfo(strs);
        return String.valueOf(count);
    }

    @RequestMapping("/removeLoginUser")
    @ResponseBody
    public String removeLoginUser(HttpServletRequest request) throws Exception {
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String str = StringUtils.appendUrl(new String[]{
                String.valueOf(user.getId())
        });
        String sessionId = mUserService.selectUserSessionId(str);
        str = StringUtils.appendUrl(new String[]{
                request.getSession().getId(), String.valueOf(user.getId())
        });
        mUserService.updateUserSessionId(str);
        return sessionId;
    }

    /**
     * 退出系统
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/exitSystem")
    @ResponseBody
    public String exitSystem(HttpServletRequest request) throws Exception {
        UserItem user = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        String str = new StringUtils().appendUrl(new String[]{
                "", String.valueOf(user.getId())
        });
        mUserService.updateUserSessionId(str);
        request.getSession().invalidate();
        return "1";
    }
}
