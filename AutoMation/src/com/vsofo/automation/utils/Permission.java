package com.vsofo.automation.utils;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/6/9
 * @类描述:
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/6/9
 * @修改描述:
 */
public interface Permission {
    String USER = "user";  //验证用户管理权限
    String PERMISSION = "permission"; //验证权限类型管理
    String PREDIC = "predic";  //验证预测类型
    String SCRIPT = "script";  //验证脚本方法
    String LOG = "log";  //验证日志类型配置
    String PLATMANAGE = "platManage";  //验证平台管理
    String PLATCHECK = "platCheck";  //验证校验模块列表
    String PLATINTERFACE = "platInterface";  //验证接口配置列表
    String PLATRQUEST = "platRquest";  //验证请求模块列表
    String EXACONFIG = "exaConfig";  //验证用例配置管理
    String EXECDETAILS = "execdetails";  //验证执行详情列表
    String CASEDETAILS = "casedetails";  //验证用例平台统计
    String INTERDETAILS = "interdetails";  //验证用例接口统计
    String SYSTEMLOG = "systemLog"; //验证系统日志
    String CHANCASELIST = "chanCaseList";  //验证渠道用例列表
    String CHANMODELLIST = "chanModelList";  //验证渠道模块列表
    String CHANEXECLIST = "chanExecList"; //验证渠道执行详情列表
}
