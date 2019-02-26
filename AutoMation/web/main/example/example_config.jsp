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
    <title>用例配置管理</title>
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
    <link rel='stylesheet' type='text/css' media='all' href='${root}/layui/css/layui.css'>
    <link rel='stylesheet' type='text/css' media='all' href='${root}/layui/css/update.css'>
    <script type='text/javascript' charset='utf-8' src='${root}/layui/layui.js'></script>
    <script src="${root}/layui/layutils.js" type="text/javascript"></script>
    <script src="${root}/script/utils/permissionUtils.js" type="text/javascript"></script>
    <script src="${root}/script/example/example_config.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission('#case_install', null, '#case_detail_table', 'exaConfig');
        });
        $(document).ready(function () {
            //改变每页数据条数,例如改为50
            $("#case_detail_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
            init_exa_config();
        });
    </script>
</head>
<body class="easyui-layout">
<div id="exa_config_top">
    <form action="" method="post">
        <div class="explain-col">
            所属平台:&nbsp;&nbsp;
            <select id="plat_name"></select>&nbsp;&nbsp;
            接口名称:&nbsp;&nbsp;
            <select id="inter_name"></select>&nbsp;&nbsp;
            状态:&nbsp;&nbsp;
            <select id="exa_state"></select>&nbsp;&nbsp;
            用例标题:&nbsp;&nbsp;<input type="text" id="detail_name"/>
            &nbsp;&nbsp;
            用例描述:&nbsp;&nbsp;<input type="text" id="detail_dec"/>
            &nbsp;&nbsp;<input type="button" value="搜索" onclick="searchCaseDetail(-1)"/>
            <a id="case_install" href="###" style="font-style: inherit" onclick="addCaseDetail()">【添加用例】</a>
        </div>
    </form>
</div>
<div region="center" class="pad-10">
    <table id="case_detail_table" class="easyui-datagrid"
           url="${root}/example/selectCaseDetailAll.asp"
           pagination="true" fit="true" style="width: 1800px;height: 100%" nowrap="false"
           rownumbers="false" fitColumns="false" singleSelect="true" toolbar="#exa_config_top">
        <thead>
        <tr>
            <th field="id" width="80" align="center">用例ID</th>
            <th field="platName" width="150" align="center">所属平台</th>
            <th field="interName" width="150" align="center">接口名称</th>
            <th field="title" width="200" align="center" formatter="oper_exa_title">用例标题</th>
            <th field="detail" width="350" align="center" formatter="oper_exa_sex">用例描述</th>
            <th field="isEnable" width="60" align="center" formatter="operstate">状态</th>
            <th field="operation" width="250" align="center" formatter="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
