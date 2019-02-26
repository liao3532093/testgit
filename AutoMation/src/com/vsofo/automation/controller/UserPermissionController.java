package com.vsofo.automation.controller;

import com.vsofo.automation.utils.JurisdictionUtils;
import com.vsofo.automation.utils.SessionUtils;
import com.vsofo.automation.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/11
 * @类描述: 权限信息控制器Controller
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/11
 * @修改描述:
 */
@Controller
@RequestMapping("/userPermission")
public class UserPermissionController {
    /**
     * 查看用户信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isUserShowPermission")
    @ResponseBody
    public String isUserShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (!StringUtils.isEmpty(permissionTypes)) {
            if (!permissionTypes.contains(JurisdictionUtils.USERLISTVIEW)) {
                return "1";
            } else if (permissionTypes.contains(JurisdictionUtils.USERLISTVIEW)
                    && !permissionTypes.contains(JurisdictionUtils.USERLISTMANAGE)) {
                return "2";
            }
        }
        return "-1";
    }

    /**
     * 查看权限类型
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isPerShowPermission")
    @ResponseBody
    public String isPerShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.POWERCLASSMANAGE)) {
            return "1";
        }
        return "-1";
    }

    /**
     * 查看预测类型
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isPredicShowPermission")
    @ResponseBody
    public String isPredicShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (!StringUtils.isEmpty(permissionTypes)) {
            if (!permissionTypes.contains(JurisdictionUtils.FORECASTLISTVIEW)) {
                return "1";
            } else if (permissionTypes.contains(JurisdictionUtils.FORECASTLISTVIEW)
                    && !permissionTypes.contains(JurisdictionUtils.FORECASTMANAGE)) {
                return "2";
            }
        }
        return "-1";
    }

    /**
     * 查看脚本方法
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isScriptShowPermission")
    @ResponseBody
    public String isScriptShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (!StringUtils.isEmpty(permissionTypes)) {
            if (!permissionTypes.contains(JurisdictionUtils.SRCIPTLISTVIEW)) {
                return "1";
            } else if (permissionTypes.contains(JurisdictionUtils.SRCIPTLISTVIEW)
                    && !permissionTypes.contains(JurisdictionUtils.SRCIPTMANAGE)) {
                return "2";
            }
        }
        return "-1";
    }

    /**
     * 查看日志类型
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isLogShowPermission")
    @ResponseBody
    public String isLogShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        System.out.println(JurisdictionUtils.LOGMANAGE);
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.LOGMANAGE)) {
            return "1";
        }
        return "-1";
    }

    /**
     * 查看平台管理
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isPlatfShowPermission")
    @ResponseBody
    public String isPlatfShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (!StringUtils.isEmpty(permissionTypes)) {
            if (!permissionTypes.contains(JurisdictionUtils.PLATFORMLISTVIEW)) {
                return "1";
            } else if (permissionTypes.contains(JurisdictionUtils.PLATFORMLISTVIEW)
                    && !permissionTypes.contains(JurisdictionUtils.PLATFORMMANAGE)) {
                return "2";
            }
        }
        return "-1";
    }

    /**
     * 查看接口配置
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isInterShowPermission")
    @ResponseBody
    public String isInterShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (!StringUtils.isEmpty(permissionTypes)) {
            if (!permissionTypes.contains(JurisdictionUtils.INTERFACELISTVIEW)) {
                return "1";
            } else if (permissionTypes.contains(JurisdictionUtils.INTERFACELISTVIEW)
                    && !permissionTypes.contains(JurisdictionUtils.INTERFACEMANAGE)) {
                return "2";
            }
        }
        return "-1";
    }

    /**
     * 查看请求模块
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isRequestShowPermission")
    @ResponseBody
    public String isRequestShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (!StringUtils.isEmpty(permissionTypes)) {
            if (!permissionTypes.contains(JurisdictionUtils.REQUESTLISTVIEW)) {
                return "1";
            } else if (permissionTypes.contains(JurisdictionUtils.REQUESTLISTVIEW)
                    && !permissionTypes.contains(JurisdictionUtils.REQUESTMANAGE)) {
                return "2";
            }
        }
        return "-1";
    }

    /**
     * 查看校验模块
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isCheckShowPermission")
    @ResponseBody
    public String isCheckShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (!StringUtils.isEmpty(permissionTypes)) {
            if (!permissionTypes.contains(JurisdictionUtils.CHECKLISTVIEW)) {
                return "1";
            } else if (permissionTypes.contains(JurisdictionUtils.CHECKLISTVIEW)
                    && !permissionTypes.contains(JurisdictionUtils.CHECKMANAGE)) {
                return "2";
            }
        }
        return "-1";
    }

    /**
     * 查看用例配置
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isCaseShowPermission")
    @ResponseBody
    public String isCaseShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (!StringUtils.isEmpty(permissionTypes)) {
            if (!permissionTypes.contains(JurisdictionUtils.CASECONFIGLISTVIEW)) {
                return "1";
            } else if (permissionTypes.contains(JurisdictionUtils.CASECONFIGLISTVIEW)
                    && !permissionTypes.contains(JurisdictionUtils.CASECONFIGMANAGE)) {
                return "2";
            }
        }
        return "-1";
    }

    /**
     * 查看执行详情
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isImplShowPermission")
    @ResponseBody
    public String isImplShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.IMPLINFOLISTVIEW)) {
            return "1";
        }
        return "-1";
    }

    /**
     * 查看用例平台统计
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isCaseStatisShowPermission")
    @ResponseBody
    public String isCaseStatisShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.CASEPLATFORMLISTVIEW)) {
            return "1";
        }
        return "-1";
    }

    /**
     * 查看用例接口统计
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isCaseInterShowPermission")
    @ResponseBody
    public String isCaseInterShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.CASEINTERFACELISTVIEW)) {
            return "1";
        }
        return "-1";
    }

    /**
     * 查看系统日志
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/isSysLogShowPermission")
    @ResponseBody
    public String isSysLogShowPermission(HttpServletRequest request) throws Exception {
        String permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.SYSTEMLOGLISTVIEW)) {
            return "1";
        }
        return "-1";
    }
}
