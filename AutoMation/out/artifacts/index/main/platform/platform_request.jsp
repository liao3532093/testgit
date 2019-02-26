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
    <title>请求模块列表</title>
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
    <script src="${root}/script/utils/Log.js" type="text/javascript"></script>
    <script src="${root}/script/platform/platform_request.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission('#request_install', null, '#request_table', 'platRquest');
        });
        $(document).ready(function () {
            var url = projectName + '/PManage/selectPlatformManageByName.asp';
            init(url, 1, '#plat_name', -1);  //平台信息
            url = projectName + '/PManage/selectInterfaceByName.asp';
            init(url, 1, '#inter_name', -1);  //接口信息
            //改变每页数据条数,例如改为50
            $("#request_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
        });
    </script>
</head>
<body class="easyui-layout">
<div id="request_top">
    <form action="" method="post">
        <div class="explain-col">
            所属平台:&nbsp;&nbsp;
            <select id="plat_name" name="platId"></select>&nbsp;&nbsp;
            所属接口:&nbsp;&nbsp;
            <select id="inter_name" name="interfaceId"></select>&nbsp;&nbsp;
            模块名称:&nbsp;&nbsp;<input id="request_title" type="text" name="title"/>
            &nbsp;&nbsp;<input type="button" value="搜索" onclick="searchRequest()"/>
            <a id="request_install" href="###" style="font-style: inherit" onclick="addRequest()">【添加请求模块】</a>
        </div>
    </form>
</div>
<div region="center" class="pad-10">
    <table id="request_table" class="easyui-datagrid"
           url="${root}/PManage/selectPlatRequestAll.asp?type=0"
           pagination="true" fit="true" style="width: 1000px;height: 100%" nowrap="false"
           rownumbers="false" fitColumns="false" singleSelect="true" toolbar="#request_top">
        <thead>
        <tr>
            <th field="id" width="100" align="center">模块ID</th>
            <th field="platName" width="150" align="center">所属平台</th>
            <th field="interName" width="150" align="center">所属接口</th>
            <th field="title" width="150" align="center">模块名称</th>
            <th field="requestUrl" width="300" align="center" formatter="operUrl">请求地址</th>
            <th field="requestType" width="100" align="center" formatter="request_mode">请求方式</th>
            <th field="paramType" width="150" align="center" formatter="parameter_format">参数格式</th>
            <th field="isEnable" width="100" align="center" formatter="operstate">平台状态</th>
            <th field="operation" width="150" align="center" formatter="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
