<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/4/25
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>添加请求模块参数</title>
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
    <script src="${root}/script/platform/request_modular.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission(null, null, null, 'platRquest');
        });
        $(document).ready(function () {
            divHide('#parameter_div', '#method_div');
            updaBtnStyle('#request_modular_install', '#request_modular_update');
        });
    </script>
</head>
<body class="easyui-layout">
<div id="request_modular_top">
    <form action="" method="post">
        <div class="explain-col">
            <div>
                <input id="request_modular_install" type="button" value="添加" onclick="saveModularInfo()"
                       style="width: 80px;"/>&nbsp;&nbsp;
                <input id="request_modular_update" type="button" value="修改" onclick="saveModularInfo()"
                       style="width: 80px;"/>
                &nbsp;&nbsp;<font color="red">(值不能含有“|”线否则添加不成功)</font>
            </div>
            <div>
                参数名称：<input id="modular_name" type="text" style="width: 150px;"/>
                &nbsp;&nbsp;&nbsp;&nbsp;<input id="method_id" type="checkbox" onchange="isMethod()"/>&nbsp;方法类型
            </div>
            <div id="parameter_div">
                &nbsp;&nbsp;&nbsp;参数值：<input id="modular_value" type="text" style="width: 260px;"/>
            </div>
            <div id="method_div">
                &nbsp;&nbsp;&nbsp;参数值：<select id="modular_script" onchange="updateMethod()"></select>
                &nbsp;&nbsp;<input id="modular_script_value" type="text" style="width: 240px;"/>
            </div>
        </div>
    </form>
</div>
<div region="center">
    <table id="request_modular_table" class="easyui-datagrid"
           url="${root}/requestModular/selectModlarAll.asp"
           pagination="true" fit="true" style="width: 1000px;height: 100%" nowrap="false"
           rownumbers="false" fitColumns="false" singleSelect="true" toolbar="#request_modular_top">
        <thead>
        <tr>
            <th field="title" width="100" align="center">参数名称</th>
            <th field="value" width="200" align="center" formatter="opervalue">参数值</th>
            <th field="valueType" width="60" align="center" formatter="opermethod">方法类型</th>
            <th field="isEnable" width="60" align="center" formatter="operstate">参数状态</th>
            <th field="operation" width="150" align="center" formatter="operation">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
