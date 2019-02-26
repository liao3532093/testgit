<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/5/8
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>权限项关键字</title>
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
            isPermission(null, null, null, 'permission');
        });
    </script>
</head>
<body class="easyui-layout">
<div id="power_item_top" class="pad-10">
    <fieldset style="margin: 15px;">
        <legend>权限项关键字</legend>
        <div style="margin: 10px;">
            <c:forEach var="pClass" items="${powerClass}" varStatus="stute">
                <a href="###" onclick="initParentId('${pClass.id}')">${pClass.title}</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            </c:forEach>
        </div>
        <div>
            <form id="power_item_form" method="post">
                <div class="explain-col">
                    <input type="hidden" id="parentId" value="-1" name="parentClassId"/>
                    关键词:&nbsp;&nbsp;<input id="power_item_key" name="keywords" type="text"/>&nbsp;&nbsp;
                    权限名称:&nbsp;&nbsp;<input id="power_item_name" name="title" type="text"/>&nbsp;&nbsp;
                    <input id="power_item_install" type="button" value="添加" onclick="savePowerItem()"/>
                    <input id="power_item_id" name="id" type="hidden" value="-1"/>
                </div>
            </form>
        </div>
    </fieldset>
</div>
<div region="center">
    <table id="power_item_table" class="easyui-datagrid"
           url="${root}/permission/selectPowerItemAll.asp"
           pagination="true" fit="true"
           rownumbers="false" fitColumns="true" singleSelect="true" toolbar="#power_item_top">
        <thead>
        <tr>
            <th field="keywords" width="150" align="center">关键词</th>
            <th field="title" width="80" align="center">权限名称</th>
            <th field="operation" width="150" align="center" formatter="operation_power_item">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
