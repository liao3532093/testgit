package com.vsofo.automation.utils;

/**
 * @创建者: liaowenjun
 * @创建时间: 2017/5/9
 * @类描述: 权限信息
 * @版本号:
 * @修改者: liaowenjun
 * @修改时间: 2017/5/9
 * @修改描述:
 */
public interface JurisdictionUtils {
    /*用户列表查看权限*/
    String USERLISTVIEW = "UserListView";
    /*用户列表管理权限*/
    String USERLISTMANAGE = "UserListManage";
    /*权限类别管理权限*/
    String POWERCLASSMANAGE = "PowerClassManage";
    /*预测类型查看权限*/
    String FORECASTLISTVIEW = "forecastListView";
    /*预测类型管理权限*/
    String FORECASTMANAGE = "forecastManage";
    /*脚本方法查看权限*/
    String SRCIPTLISTVIEW = "srciptListView";
    /*脚本方法管理权限*/
    String SRCIPTMANAGE = "srciptManage";
    /*日志类型管理权限*/
    String LOGMANAGE = "logManage";
    /*平台列表查看权限*/
    String PLATFORMLISTVIEW = "platformListView";
    /*平台列表管理权限*/
    String PLATFORMMANAGE = "platformManage";
    /*接口列表查看权限*/
    String INTERFACELISTVIEW = "interfaceListView";
    /*接口列表管理权限*/
    String INTERFACEMANAGE = "interfaceManage";
    /*请求模块列表查看权限*/
    String REQUESTLISTVIEW = "requestListView";
    /*请求模块列表管理权限*/
    String REQUESTMANAGE = "requestManage";
    /*校验模块列表查看权限*/
    String CHECKLISTVIEW = "checkListView";
    /*校验模块列表管理权限*/
    String CHECKMANAGE = "checkManage";
    /*用例配置列表查看权限*/
    String CASECONFIGLISTVIEW = "caseConfigListView";
    /*用例配置列表管理权限*/
    String CASECONFIGMANAGE = "caseConfigManage";
    /*执行详情列表查看权限*/
    String IMPLINFOLISTVIEW = "implInfoListView";
    /*用例平台统计查看权限*/
    String CASEPLATFORMLISTVIEW = "casePlatformListView";
    /*用例接口统计查看权限*/
    String CASEINTERFACELISTVIEW = "caseinterfacelistview";
    /*渠道用例列表查看权限*/
    String CHANCASELISTVIEW = "chanCaseListView";
    /*渠道用例列表管理权限*/
    String CHANCASEMANAGE = "chanCaseManage";
    /*渠道模块管理列表查看权限*/
    String CHANMODELLISTVIEW = "chanModelListView";
    /*渠道模块管理列表管理权限*/
    String CHANMODELMANAGE = "chanModelManage";
    /*渠道执行详情列表查看权限*/
    String CHANEXECLISTVIEW = "chanExecListView";
    /*系统日志列表查看权限*/
    String SYSTEMLOGLISTVIEW = "systemLogListView";

    /**************************日志打印***************************/
    /*登录日志*/
    String LOGINLOG = "LoginLog";
    /*用户管理日志*/
    String USERLOG = "UserLog";
    /*权限类别管理日志*/
    String JURISDICTIONLOG = "JurisdictionLog";
    /*用例配置管理*/
    String CASECONFIGLISTVIEWLOG = "caseConfigListViewLog";
    /*渠道测试管理*/
    String PILINGLISTVIEWLOG = "pilingListViewLog";
    /*监控管理*/
    String CASECONFIGMANAGELOG = "caseConfigManageLog";
}
