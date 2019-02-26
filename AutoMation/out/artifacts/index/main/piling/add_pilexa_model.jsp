<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/6/7
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>添加渠道模块配置</title>
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
    <script src="${root}/script/utils/utils.js" type="text/javascript"></script>
    <script src="${root}/script/utils/Log.js" type="text/javascript"></script>
    <script src="${root}/script/piling/add_pilexa_model.js" type="text/javascript"></script>
</head>
<body class="easyui-layout">
<div id="add_pilexa_model_top" class="pad-10">
    <form id="add_pilexa_model_form" method="post">
        <div class="explain-col">
            用例名称:&nbsp;&nbsp;<font id="pilexa_name" color="green"></font>
            <input type="hidden" name="id" id="pilexamodel_id" value="-1"/>
            <div>
                模块名称:&nbsp;&nbsp;<select id="pilmodel_name" name="modelId" onchange="loadModelVal()"></select>&nbsp;&nbsp;
                参数:&nbsp;&nbsp;<textarea id="pilmodel_val" name="value" rows="5" cols="70"></textarea>&nbsp;&nbsp;
                执行顺序:&nbsp;&nbsp;<input type="text" id="pilmodel_num" name="orderId" style="width: 40px;"
                                        onkeyup="value=value.replace(/[^\d]/g,'')"
                                        onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"/>&nbsp;&nbsp;
                <input type="button" id="pilexa_model_add" value="添加" onclick="insertPilExaModel()"/>&nbsp;&nbsp;
                <input type="button" id="pilexa_model_update" value="修改" onclick="updatePilExaModels()"/>
            </div>
        </div>
    </form>
</div>
<div region="center">
    <table id="add_pilexa_model_table" class="easyui-datagrid"
           url="${root}/pilexample/findPilExampleModelAll.asp"
           pagination="true" fit="true" style="width: 1800px;height: 100%" nowrap="false"
           rownumbers="false" fitColumns="true" singleSelect="true" toolbar="#add_pilexa_model_top">
        <thead>
        <tr>
            <th field="modelName" width="150" align="center">模块</th>
            <th field="value" width="350" align="center" formatter="opervalue">返回/请求参数</th>
            <th field="orderId" width="60" align="center">执行顺序</th>
            <th field="state" width="60" align="center" formatter="operstate">状态</th>
            <th field="operation" width="180" align="center" formatter="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
<div id="pilexa_result_dialog"></div>
</body>
</html>
