<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/4/25
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>脚本方法列表</title>
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
    <script src="${root}/script/system/script_method.js" type="text/javascript"></script>
    <script>
        $(function () {
            isPermission('#script_install', null, '#script_method_table', 'script');
        });
        $(document).ready(function () {
            //改变每页数据条数,例如改为50
            $("#script_method_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
        });
    </script>
</head>
<body class="easyui-layout">
<div id="script_top" class="pad-10">
    <form id="prediction_form" method="post">
        <div class="explain-col">
            方法名称:&nbsp;&nbsp;<input id="script_name" type="text"/>&nbsp;&nbsp;
            方法描述:&nbsp;&nbsp;<input id="script_detail" type="text"/>
            &nbsp;&nbsp;<input type="button" value="搜索" onclick="searchScriptMethod()"/>
            &nbsp;&nbsp;<a id="script_install" href="###" onclick="addScriptMethod()">【添加方法】</a>
        </div>
    </form>
</div>
<div region="center" class="pad-10">
    <table id="script_method_table" class="easyui-datagrid"
           url="${root}/script/selectScriptMethodAll.asp"
           pagination="true" fit="true" nowrap="false"
           rownumbers="false" fitColumns="true" singleSelect="true" toolbar="#script_top">
        <thead>
        <tr>
            <th field="id" width="80" align="center">编号</th>
            <th field="title" width="100" align="center">方法名称</th>
            <th field="detail" width="150" align="center" formatter="operdetail">方法描述</th>
            <th field="isEnable" width="80" align="center" formatter="operstate">状态</th>
            <th field="operation" width="150" align="center" formatter="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
