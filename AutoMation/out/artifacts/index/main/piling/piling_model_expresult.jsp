<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/6/6
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>预期结果列表</title>
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
    <script src="${root}/script/utils/pako.js" type="text/javascript"></script>
    <script src="${root}/script/utils/utils.js" type="text/javascript"></script>
    <script src="${root}/script/piling/piling_model_expresult.js" type="text/javascript"></script>
</head>
<body class="easyui-layout">
<div id="piling_model_exp_top" class="pad-10">
    <form method="post">
        <div class="explain-col">
            所属模块:&nbsp;&nbsp;<font class="piling_model_name" color="green"></font>
            &nbsp;&nbsp;&nbsp;&nbsp;<a id="case_install" href="###" style="font-style: inherit"
                                       onclick="goToModelExpResult('新增预测结果')">【添加预期结果】</a>
        </div>
    </form>
</div>
<div region="center">
    <table id="piling_model_exp_table" class="easyui-datagrid"
           url="${root}/piling/findPilModelExpResult.asp"
           pagination="true" fit="true" style="width: 1800px;height: 100%" nowrap="false"
           rownumbers="false" fitColumns="true" singleSelect="true" toolbar="#piling_model_exp_top">
        <thead>
        <tr>
            <th field="expName" width="150" align="center">预期类型</th>
            <th field="details" width="200" align="center" formatter="operdetails">预期结果详情</th>
            <th field="sqlName" width="300" align="center" formatter="opersql">执行脚本</th>
            <th field="eValue" width="150" align="center" formatter="operexaval">预期值</th>
            <th field="num" width="60" align="center">顺序</th>
            <th field="state" width="60" align="center" formatter="operstate">状态</th>
            <th field="operation" width="150" align="center" formatter="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
