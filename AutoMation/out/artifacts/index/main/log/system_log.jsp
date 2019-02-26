<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/18
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>系统日志管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="0">
    <link type="text/css" rel="stylesheet" href="http://r.vnetone.com/common/css/system.css"/>
    <link href="http://r.vnetone.com/admin/css/table_form.css" rel="stylesheet" type="text/css"/>
    <link href="http://r.vnetone.com/common/css/tip.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="http://r.vnetone.com/common/css/jquery.dataTables.css"/>
    <link rel="stylesheet" type="text/css" href="${root}/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${root}/jquery-easyui-1.3.3/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${root}/jquery-easyui-1.3.3/demo/demo.css">
    <link href="${root}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://r.vnetone.com/common/js/jquery/jquery-1.9.1.min.js"></script>
    <script src="http://r.vnetone.com/common/js/plus/artDialog/jquery.artDialog.js?skin=default"
            type="text/javascript"></script>
    <script src="http://r.vnetone.com/common/js/plus/artDialog/plugins/iframeTools.js"
            type="text/javascript"></script>
    <script src="http://r.vnetone.com/common/js/tip.js" type="text/javascript" language="javascript"></script>
    <script type="text/javascript" src="${root}/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${root}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${root}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="http://r.vnetone.com/common/js/plus/vdate/vdate.js" language="javascript"
            type="text/javascript"></script>
    <script src="${root}/jquery/jquery-form.js" type="text/javascript"></script>
    <script src="${root}/jquery/jquery.json.js" type="text/javascript"></script>
    <script src="${root}/jquery/jquerySession.js" type="text/javascript"></script>
    <script src="${root}/script/utils/permissionUtils.js" type="text/javascript"></script>
    <script src="${root}/script/utils/utils.js" type="text/javascript"></script>
    <script src="${root}/script/log/system_log.js" type="text/javascript"></script>
</head>
<body class="easyui-layout">
<div id="system_log_top" class="pad-10">
    <form id="system_log_form" method="post">
        <div class="explain-col">
            用户名:&nbsp;&nbsp;<input id="user_name" name="showName" type="text"/>&nbsp;&nbsp;
            详情关键词:&nbsp;&nbsp;<input id="log_info" name="info" type="text"/>&nbsp;&nbsp;
            日志分类:&nbsp;&nbsp;<select id="log_type" name="logId"></select>&nbsp;&nbsp;
            时间:&nbsp;&nbsp;
            <input type="text" class="Wdate" id="systemlog_old_date" name="inDate"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:$('input[mark=\'EndTime\']').val(),isShowClear:false,readOnly:true})"/>&nbsp;&nbsp;
            到&nbsp;&nbsp;
            <input type="text" class="Wdate" id="systemlog_new_date" name="outDate"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:$('input[mark=\'EndTime\']').val(),isShowClear:false,readOnly:true})"/>
            &nbsp;&nbsp;<input id="system_log_install" type="button" value="搜索" onclick="search_system_log()"/>
        </div>
    </form>
</div>
<div region="center">
    <table id="system_log_table" class="easyui-datagrid"
           url="${root}/systemLog/selectSystemLogAll.asp"
           pagination="true" fit="true" nowrap="false" style="width: 1800px;height: 100%"
           rownumbers="false" fitColumns="false" singleSelect="true" toolbar="#system_log_top">
        <thead>
        <tr>
            <th field="id" width="80" align="center">序号</th>
            <th field="showName" width="150" align="center">用户名</th>
            <th field="ipConfig" width="120" align="center">IP</th>
            <th field="info" width="600" align="center" formatter="operLogInfo">详情</th>
            <th field="logName" width="150" align="center">日志分类</th>
            <th field="addTime" width="180" align="center">时间</th>
            <%--<th field="operation" width="150" align="center" formatter="operation">操作</th>--%>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
