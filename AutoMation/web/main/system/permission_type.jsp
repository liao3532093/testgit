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
    <title>权限类型管理</title>
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
    <script src="${root}/script/system/permission_type.js" type="text/javascript"></script>
    <script>
        $(function () {
            isPermission('#permission_install', null, '#permission_table', 'permission');
        });
    </script>
</head>
<body class="easyui-layout">
<div id="permission_top" class="pad-10">
    <form id="permission_form" method="post">
        <div class="explain-col">
            类别名称:&nbsp;&nbsp;<input id="permission_name" name="title" type="text"/>&nbsp;&nbsp;
            排列序号:&nbsp;&nbsp;<input id="permission_code" name="orderNo" type="text"
                                    onkeyup="value=value.replace(/[^\d]/g,'') "
                                    onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>&nbsp;&nbsp;
            是否锁定:&nbsp;&nbsp;<input type="checkbox" name="isLock"/>&nbsp;&nbsp;
            <input id="permission_install" type="button" value="添加" onclick="addPowerClass()"/>
            <input id="permission_id" name="id" type="hidden" value="-1"/>
        </div>
    </form>
</div>
<div region="center">
    <table id="permission_table" class="easyui-datagrid"
           url="${root}/permission/selectPowerClassAll.asp"
           pagination="true" fit="true"
           rownumbers="false" fitColumns="true" singleSelect="true" toolbar="#permission_top">
        <thead>
        <tr>
            <th field="title" width="150" align="center">类别</th>
            <th field="orderNo" width="80" align="center">序号</th>
            <th field="isLock" width="80" align="center" formatter="operisLock">状态</th>
            <th field="operation" width="150" align="center" formatter="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
