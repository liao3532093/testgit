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
    <title>平台管理列表</title>
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
    <script src="${root}/jquery/jquery-form.js" type="text/javascript"></script>
    <script src="${root}/jquery/jquery.json.js" type="text/javascript"></script>
    <script src="${root}/jquery/jquerySession.js" type="text/javascript"></script>
    <script src="${root}/script/utils/permissionUtils.js" type="text/javascript"></script>
    <script src="${root}/script/utils/utils.js" type="text/javascript"></script>
    <script src="${root}/script/platform/platform_manage.js" type="text/javascript"></script>
    <script>
        $(function () {
            isPermission('#manage_instance', '#manage_update', '#manage_table', 'platManage');
        });
        $(document).ready(function () {
            init_manage();
        })
    </script>
</head>
<body class="easyui-layout">
<div id="manage_top">
    <form id="manage_form" method="post">
        <div class="explain-col">
            平台名称:&nbsp;&nbsp;<input id="manage_info" name="title" type="text"/>
            &nbsp;&nbsp;<input id="manage_instance" type="button" value="搜索" onclick="searchManage()"/>
            &nbsp;&nbsp;<input id="manage_update" type="button" value="添加" onclick="updateManageById(-1)"/>
        </div>
    </form>
</div>
<div region="center" class="pad-10">
    <table id="manage_table" class="easyui-datagrid"
           url="${root}/PManage/selectPlatformManageAll.asp"
           pagination="true" fit="true" nowrap="false"
           rownumbers="false" fitColumns="true" singleSelect="true" toolbar="#manage_top">
        <thead>
        <tr>
            <th field="id" width="100" align="center">平台ID</th>
            <th field="title" width="150" align="center">平台名称</th>
            <th field="exeUrl" width="150" align="center" formatter="operexecuteurl">渠道用例执行地址</th>
            <th field="isEnable" width="100" align="center" formatter="operstate">平台状态</th>
            <th field="operation" width="100" align="center" formatter="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
