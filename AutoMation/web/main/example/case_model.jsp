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
    <title>用例模块配置</title>
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
    <script src="${root}/script/example/example_config.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission(null, null, null, 'exaConfig');
        });
        $(document).ready(function () {
            var json = $.session.get('json');
            json = decodeURIComponent(unzip(json));
            var obj = jQuery.parseJSON(json);
            $('#plat_info').text(obj.platName);
            $('#inter_info').text(obj.interName);
            $('#case_detail_title').text(obj.title);
            $('#case_detail_des').text(obj.detail);
            //改变每页数据条数,例如改为50
            $("#case_model_table").datagrid('getPager').pagination('refresh', {pageSize: 50});
        });
    </script>
</head>
<body class="easyui-layout">
<div id="case_model_top">
    <fieldset style="margin: 10px;">
        <legend>用例配置信息</legend>
        <div>
            <font color="green">所属平台：</font><font id="plat_info"></font>&nbsp;&nbsp;
            <font color="green">所属接口：</font><font id="inter_info"></font>&nbsp;&nbsp;
            <font color="green">用例标题：</font><font id="case_detail_title"></font>&nbsp;&nbsp;
            <font color="green">用例描述：</font><font id="case_detail_des"></font>&nbsp;&nbsp;
        </div>
    </fieldset>
    <form action="" method="post">
        <div class="explain-col">
            <input style="width: 120px;height: 30px;" type="button" value="添加用例模块" onclick="addCaseModel()"/>
        </div>
    </form>
</div>
<div region="center" class="pad-10">
    <table id="case_model_table" class="easyui-datagrid"
           url="${root}/example/selectCaseModelAll.asp"
           pagination="true" fit="true" style="width: 1800px;height: 100%" nowrap="false"
           rownumbers="false" fitColumns="true" singleSelect="true" toolbar="#case_model_top">
        <thead>
        <tr>
            <th field="id" width="80" align="center">模块ID</th>
            <th field="modelName" width="150" align="center">模块名称</th>
            <th field="modelUrl" width="250" align="center" formatter="operUrl">请求地址|SQL地址</th>
            <th field="orderNum" width="80" align="center">执行顺序</th>
            <th field="isEnable" width="80" align="center" formatter="operstate">状态</th>
            <th field="operation" width="250" align="center" formatter="opermodelation">操作</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
