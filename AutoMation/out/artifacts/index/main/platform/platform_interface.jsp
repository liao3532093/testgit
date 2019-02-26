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
    <title>接口配置列表</title>
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
    <script src="${root}/script/platform/platform_interface.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission('#inter_install', null, '#interface_table', 'platInterface');
        });
        $(document).ready(function () {
            initData(1, '#search_platid', -1);
            //改变每页数据条数,例如改为50
            $("#interface_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
        });
    </script>
</head>
<body class="easyui-layout">
<div region="center" class="pad-10">
    <table id="interface_table" class="easyui-datagrid"
           url="${root}/PManage/selectPlatInterfaceAll.asp"
           pagination="true" fit="true" nowrap="false"
           rownumbers="false" fitColumns="true" singleSelect="true" toolbar="#interface_top">
        <thead>
        <tr>
            <th field="id" width="100" align="center">接口ID</th>
            <th field="platName" width="200" align="center">所属平台</th>
            <th field="title" width="250" align="center">接口名称</th>
            <th field="isEnable" width="200" align="center" formatter="operstate">状态</th>
            <th field="operation" width="250" align="center" formatter="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
<div id="interface_top">
    <form id="interface_form" method="post">
        <div class="explain-col">
            所属平台:&nbsp;&nbsp;
            <select id="search_platid" name="platId">
            </select>&nbsp;&nbsp;
            接口名称:&nbsp;&nbsp;<input type="text" id="search_name" name="title"/>
            &nbsp;&nbsp;<input type="button" value="搜索" onclick="searchInterface()"/>
            <a id="inter_install" href="###" style="font-style: inherit" onclick="addPlatInterface()">【添加接口】</a>
        </div>
    </form>
</div>
</body>
</html>
