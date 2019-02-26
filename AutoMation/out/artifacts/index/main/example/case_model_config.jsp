<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/4/27
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用例模块参数配置</title>
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
    <script src="${root}/script/example/model_config.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission(null, null, null, 'exaConfig');
        });
        $(document).ready(function () {
            var json = $.session.get('config');
            var obj = jQuery.parseJSON(json);
            $('#config_detail_title').text(obj.caseName);
            $('#request_name').text(obj.modelName);
            loadSelectData(obj.modelId, obj.id);
            //改变每页数据条数,例如改为50
            $("#case_model_config_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
        });
    </script>
</head>
<body class="easyui-layout">
<div id="model_config_top">
    <fieldset style="margin: 10px;">
        <legend>用例模块信息</legend>
        <div>
            <font color="green">用例标题：</font><font id="config_detail_title"></font>&nbsp;&nbsp;
            <font color="green">模块名称：</font><font id="request_name"></font>&nbsp;&nbsp;
            &nbsp;&nbsp;<input type="button" value="保存" onclick="saveModelConfig()" style="width: 80px;"/>
        </div>
    </fieldset>
</div>
<div region="center">
    <table id="case_model_config_table" class="easyui-datagrid"
           url="${root}/requestModular/selectModelToModular.asp"
           pagination="true" fit="true" style="width: 1800px;height: 100%" checkOnSelect="false"
           rownumbers="false" fitColumns="true" singleSelect="false" toolbar="#model_config_top">
        <thead>
        <tr>
            <th field="cb" checkbox="true" align="center"></th>
            <th field="title" width="80" align="center">参数名称</th>
            <th field="value" width="150" align="center" formatter="opervalue">参数值</th>
            <th field="operation" width="80" align="center" formatter="operation">恢复默认值</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
