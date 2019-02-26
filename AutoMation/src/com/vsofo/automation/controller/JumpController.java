package com.vsofo.automation.controller;

import com.vsofo.automation.entity.PowerList;
import com.vsofo.automation.entity.UserItem;
import com.vsofo.automation.service.PermissionService;
import com.vsofo.automation.utils.DataUtils;
import com.vsofo.automation.utils.SessionUtils;
import com.vsofo.automation.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @项目名称:
 * @作者: Administrator
 * @描述:
 * @SVN版本号:
 * @修改人: Administrator
 * @修改时间: 2017/4/18
 * @修改的内容:
 */
@Controller
@RequestMapping("/jump")
public class JumpController {
    @Autowired
    private PermissionService mPermissionService;

    @RequestMapping("/goToMain")
    public String goToMain(HttpServletRequest request) throws Exception {
        UserItem userItem = (UserItem) request.getSession().getAttribute(SessionUtils.USER);
        if (userItem == null) {
            return "WEB-INF/login";
        } else {
            request.getSession().removeAttribute(SessionUtils.POWERLIST);
            PowerList powerList = mPermissionService.selectPowerListAll(String.valueOf(userItem.getId()));
            if (powerList == null) {
                request.getSession().removeAttribute(SessionUtils.USERPOWER);
            } else {
                request.getSession().setAttribute(SessionUtils.USERPOWER, powerList.getPowerName());
            }
            return "WEB-INF/main";
        }
    }

    @RequestMapping("/goToLogin")
    public String goToLogin(HttpServletRequest request) throws Exception {
        DataUtils.ipConfig = getIpConfig(request);
        return "WEB-INF/login";
    }

    @RequestMapping("/goTo404")
    public String goTo404() throws Exception {
        return "WEB-INF/404";
    }

    /**
     * 获取session_id
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/sessionId")
    @ResponseBody
    public String getSession(HttpServletRequest request) throws Exception {
        return request.getSession().getId();
    }

    /**
     * 取得外网ip地址
     *
     * @return
     * @throws Exception
     */
    private String getIpConfig(HttpServletRequest request) throws Exception {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
        //        new Thread(() -> {
        //            String result = LHttp.newHttp().setUrl("http://ip.chinaz.com/getip.aspx").build().execute();
        //            JSONObject object = JSON.parseObject(result);
        //            DataUtils.ipConfig = object.getString("ip");
        //        }).start();
    }

    @RequestMapping("/goToPage")
    public String goToPage(String page) throws Exception {
        if (page.contains("?")) {
            page = page.substring(0, page.indexOf("?"));
        }
        return page;
    }
}
