<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/5/8
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用户权限列表</title>
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
    <script src="${root}/script/system/user.js" type="text/javascript"></script>
    <script>
        $(function () {
            isPermission(null, null, null, 'user');
        });
    </script>
</head>
<body class="easyui-layout" style="padding: 20px;">
<div class="easyui-panel" title="" style="width:800px;height:500px;">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'west',split:true" title="权限菜单" style="width:240px;padding:10px">
            <ul class="easyui-tree" id="permission_user_ui">
                <c:forEach var="item" items="${powerClass}">
                    <li>
                        <span>${item.title}</span>
                        <c:forEach var="data" items="${item.data}">
                            <ul>
                                <li>
                                    <a href="###" onclick="openPermission(${data.id})">${data.title}</a>
                                </li>
                            </ul>
                        </c:forEach>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div data-options="region:'center'">
            <div id="ermission_user_top" class="pad-10">
                <div class="explain-col">
                    <input type="button" value="设置权限" onclick="setPermission()"/>
                </div>
            </div>
            <table id="ermission_user_table" class="easyui-datagrid" title="设置权限列表"
                   url="${root}/permission/selectPowerItemAll.asp"
                   pagination="true" fit="true" style="width: 1800px;height: 500px;"
                   rownumbers="false" fitColumns="true" singleSelect="false" toolbar="#ermission_user_top">
                <thead>
                <tr>
                    <th field="aa" checkbox="true" align="center"></th>
                    <th field="title" width="150" align="center">权限名称</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
</body>
</html>
