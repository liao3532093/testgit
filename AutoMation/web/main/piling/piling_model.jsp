<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/6/5
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>渠道模块管理</title>
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
    <script type="text/javascript" src="${root}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${root}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="${root}/jquery/jquery-form.js" type="text/javascript"></script>
    <script src="${root}/jquery/jquery.json.js" type="text/javascript"></script>
    <script src="${root}/jquery/jquerySession.js" type="text/javascript"></script>
    <script src="${root}/script/utils/permissionUtils.js" type="text/javascript"></script>
    <script src="${root}/script/utils/pako.js" type="text/javascript"></script>
    <script src="${root}/script/utils/utils.js" type="text/javascript"></script>
    <script src="${root}/script/piling/piling_model.js" type="text/javascript"></script>
</head>
<body class="easyui-layout">
<div id="piling_model_top" class="pad-10">
    <form method="post">
        <div class="explain-col">
            所属平台:&nbsp;&nbsp;
            <select id="plat_name"></select>&nbsp;&nbsp;
            模块类型:&nbsp;&nbsp;
            <select id="piling_model_type"></select>&nbsp;&nbsp;
            模块名称:&nbsp;&nbsp;<input type="text" id="detail_name"/>
            &nbsp;&nbsp;<input type="button" value="搜索" onclick="searchPilModel()"/>
            <a id="chan_model_install" href="###" style="font-style: inherit"
               onclick="add_piling_model('添加模块')">【添加模块】</a>
        </div>
    </form>
</div>
<div region="center">
    <table id="piling_model_table" class="easyui-datagrid"
           url="${root}/piling/selectPilingModelAll.asp"
           pagination="true" fit="true" style="width: 1800px;height: 100%" nowrap="false"
           rownumbers="false" fitColumns="false" singleSelect="true" toolbar="#piling_model_top">
        <thead>
        <tr>
            <th field="id" width="80" align="center">ID</th>
            <th field="modelName" width="150" align="center">模块名称</th>
            <th field="platName" width="150" align="center">所属平台</th>
            <th field="modelType" width="200" align="center" formatter="opermodelType">模块类型</th>
            <th field="requestUrl" width="350" align="center" formatter="operrequestUrl">请求地址</th>
            <th field="requestType" width="100" align="center">请求方式</th>
            <th field="responseItem" width="350" align="center" formatter="operresponseItem">返回/请求参数</th>
            <th field="state" width="60" align="center" formatter="operstate">状态</th>
            <th field="operation" width="250" align="center" formatter="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
