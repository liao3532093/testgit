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
    <title>校验模块列表</title>
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
    <script src="${root}/script/platform/platform_check.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission('#check_install', null, '#check_table', 'platCheck');
        });
        $(document).ready(function () {
            init(1, '#plat_name', '${root}/PManage/selectPlatformManageByName.asp');
            init(1, '#pred_name', '${root}/prediction/selectPredIsEnableByName.asp');
            //改变每页数据条数,例如改为50
            $("#check_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
        });
    </script>
</head>
<body class="easyui-layout">
<div id="check_top">
    <form action="" method="post">
        <div class="explain-col">
            所属平台:&nbsp;&nbsp;
            <select id="plat_name" name="platID"></select>&nbsp;&nbsp;
            预测类型:&nbsp;&nbsp;
            <select id="pred_name" name="expectTypeID"></select>&nbsp;&nbsp;
            脚本说明:&nbsp;&nbsp;
            <input id="sc_name" name="executeSql"/>&nbsp;&nbsp;
            &nbsp;&nbsp;<input type="button" value="搜索" onclick="searchCheck()"/>
            <a id="check_install" href="###" style="font-style: inherit" onclick="addPlatCheck()">【添加校验模块】</a>
        </div>
    </form>
</div>
<div region="center" class="pad-10">
    <table id="check_table" class="easyui-datagrid"
           url="${root}/check/selectPlatCheckAll.asp"
           pagination="true" fit="true" style="width: 1800px;height: 100%" nowrap="false"
           rownumbers="false" fitColumns="false" singleSelect="true" toolbar="#check_top">
        <thead>
        <tr>
            <th field="id" width="80" align="center">编号</th>
            <th field="platName" width="150" align="center">平台名称</th>
            <th field="expectTypeName" width="150" align="center">预测类型</th>
            <th field="title" width="200" align="center">脚本说明</th>
            <th field="executeSql" width="300" align="center" formatter="operscript">脚本</th>
            <th field="expectValue" width="150" align="center">预期值</th>
            <th field="isEnable" width="60" align="center" formatter="operstate">状态</th>
            <th field="operation" width="150" align="center" formatter="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
