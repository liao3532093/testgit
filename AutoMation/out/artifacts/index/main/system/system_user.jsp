<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/18
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用户管理</title>
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
    <script src="${root}/script/utils/isExitUser.js" type="text/javascript"></script>
    <script src="${root}/script/utils/permissionUtils.js" type="text/javascript"></script>
    <script src="${root}/script/system/user.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission('#user_install', null, '#user_table', 'user');
        });
        $(document).ready(function () {
            //改变每页数据条数,例如改为50
            $("#user_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
        });
    </script>
</head>
<body class="easyui-layout">
<div id="user_top" class="pad-10">
    <form id="prediction_form" method="post">
        <div class="explain-col">
            姓名:&nbsp;&nbsp;<input type="text" id="showName" name="showName"/>
            &nbsp;&nbsp;<input type="button" value="搜索" onclick="searchUser()"/>
            <a href="###" id="user_install" style="font-style: inherit" onclick="add_user()">【添加用户】</a>
        </div>
    </form>
</div>
<div region="center" class="pad-10">
    <table id="user_table" class="easyui-datagrid"
           url="${root}/user/selectUserInfoAll.asp"
           pagination="true" fit="true" style="width: 1800px;height: 100%"
           rownumbers="false" fitColumns="true" singleSelect="true" toolbar="#user_top">
        <thead>
        <tr>
            <th field="account" width="80" align="center">账号</th>
            <th field="showName" width="150" align="center">姓名</th>
            <th field="isLock" width="80" align="center" formatter="operstate">用户状态</th>
            <th field="lastLoginIP" width="150" align="center">最后登录IP</th>
            <th field="lastLoginTime" width="150" align="center">最后登录时间</th>
            <th field="operation" width="200" align="center" formatter="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
