package com.vsofo.automation.controller;

import com.vsofo.automation.utils.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/9
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/9
 * @修改描述:
 */
@Controller
@RequestMapping("/syspermission")
public class SystemPermissionController {
    private String permissionTypes;

    /**
     * @param name
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/isPermission")
    @ResponseBody
    public String isPermission(String name, HttpServletRequest request) throws Exception {
        permissionTypes = (String) request.getSession().getAttribute(SessionUtils.USERPOWER);
        if (isUserSystemPermission(request)) {
            return "500";
        }
        switch (name) {
            case Permission.USER:
                return isUserShowPermission();
            case Permission.PERMISSION:
                return isPerShowPermission();
            case Permission.PREDIC:
                return isPredicShowPermission();
            case Permission.SCRIPT:
                return isScriptShowPermission();
            case Permission.LOG:
                return isLogShowPermission();
            case Permission.PLATMANAGE:
                return isPlatfShowPermission();
            case Permission.PLATCHECK:
                return isCheckShowPermission();
            case Permission.PLATINTERFACE:
                return isInterShowPermission();
            case Permission.PLATRQUEST:
                return isRequestShowPermission();
            case Permission.EXACONFIG:
                return isCaseShowPermission();
            case Permission.EXECDETAILS:
                return isImplShowPermission();
            case Permission.CASEDETAILS:
                return isCaseStatisShowPermission();
            case Permission.INTERDETAILS:
                return isCaseInterShowPermission();
            case Permission.SYSTEMLOG:
                return isSysLogShowPermission();
            case Permission.CHANCASELIST:
                return isChanCaseListPermission();
            case Permission.CHANMODELLIST:
                return isChanModelPermission();
            case Permission.CHANEXECLIST:
                return isChanExecutePermission();
        }
        return "";
    }

    /**
     * 登录是否已过期
     *
     * @param request
     * @return
     */
    private boolean isUserSystemPermission(HttpServletRequest request) {
        return request.getSession().getAttribute(SessionUtils.USER) == null;
    }

    /**
     * 查看用户信息
     *
     * @return
     */
    private String isUserShowPermission() {
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
     */
    public String isPerShowPermission() {
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.POWERCLASSMANAGE)) {
            return "1";
        }
        return "-1";
    }

    /**
     * 查看预测类型
     *
     * @return
     */
    public String isPredicShowPermission() {
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
     */
    public String isScriptShowPermission() {
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
     */
    public String isLogShowPermission() {
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.LOGMANAGE)) {
            return "1";
        }
        return "-1";
    }

    /**
     * 查看平台管理
     *
     * @return
     */
    public String isPlatfShowPermission() {
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
     * 查看校验模块
     *
     * @return
     */
    public String isCheckShowPermission() {
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
     * 查看接口配置
     *
     * @return
     */
    public String isInterShowPermission() {
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
     */
    public String isRequestShowPermission() {
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
     * 查看用例配置
     *
     * @return
     */
    public String isCaseShowPermission() {
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
     */
    public String isImplShowPermission() {
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.IMPLINFOLISTVIEW)) {
            return "1";
        }
        return "-1";
    }

    /**
     * 查看用例平台统计
     *
     * @return
     */
    public String isCaseStatisShowPermission() {
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.CASEPLATFORMLISTVIEW)) {
            return "1";
        }
        return "-1";
    }

    /**
     * 查看用例接口统计
     *
     * @return
     */
    public String isCaseInterShowPermission() {
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.CASEINTERFACELISTVIEW)) {
            return "1";
        }
        return "-1";
    }

    /**
     * 查看系统日志
     *
     * @return
     */
    public String isSysLogShowPermission() {
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.SYSTEMLOGLISTVIEW)) {
            return "1";
        }
        return "-1";
    }

    /**
     * 查看渠道用例列表
     *
     * @return
     */
    public String isChanCaseListPermission() {
        if (!StringUtils.isEmpty(permissionTypes)) {
            if (!permissionTypes.contains(JurisdictionUtils.CHANCASELISTVIEW)) {
                return "1";
            } else if (permissionTypes.contains(JurisdictionUtils.CHANCASELISTVIEW)
                    && !permissionTypes.contains(JurisdictionUtils.CHANCASEMANAGE)) {
                return "2";
            }
        }
        return "-1";
    }

    /**
     * 查看渠道模块列表
     *
     * @return
     */
    public String isChanModelPermission() {
        if (!StringUtils.isEmpty(permissionTypes)) {
            if (!permissionTypes.contains(JurisdictionUtils.CHANMODELLISTVIEW)) {
                return "1";
            } else if (permissionTypes.contains(JurisdictionUtils.CHANMODELLISTVIEW)
                    && !permissionTypes.contains(JurisdictionUtils.CHANMODELMANAGE)) {
                return "2";
            }
        }
        return "-1";
    }

    /**
     * 查看渠道执行详情列表
     *
     * @return
     */
    public String isChanExecutePermission() {
        if (StringUtils.isEmpty(permissionTypes) || !permissionTypes.contains(JurisdictionUtils.CHANEXECLISTVIEW)) {
            return "1";
        }
        return "-1";
    }
}
