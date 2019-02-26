<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/5/3
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>执行详情列表</title>
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
    <link rel="stylesheet" type="text/css" href="${root}/jquery-easyui-1.3.3/demo/demo.css">
    <link href="${root}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://r.vnetone.com/common/js/jquery/jquery-1.9.1.min.js"></script>
    <script src="http://r.vnetone.com/common/js/plus/artDialog/jquery.artDialog.js?skin=default"
            type="text/javascript"></script>
    <script src="http://r.vnetone.com/common/js/plus/artDialog/plugins/iframeTools.js"
            type="text/javascript"></script>
    <script src="http://r.vnetone.com/common/js/tip.js" type="text/javascript" language="javascript"></script>
    <script src="http://r.vnetone.com/common/js/plus/vdate/vdate.js" language="javascript"
            type="text/javascript"></script>
    <script type="text/javascript" src="${root}/jquery-easyui-1.3.3/jquery.min.js"></script>
    <script type="text/javascript" src="${root}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${root}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="${root}/jquery/jquery-form.js" type="text/javascript"></script>
    <script src="${root}/jquery/jquery.json.js" type="text/javascript"></script>
    <script src="${root}/jquery/jquerySession.js" type="text/javascript"></script>
    <script src="${root}/script/utils/permissionUtils.js" type="text/javascript"></script>
    <script src="${root}/script/monitor/implement_info.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission(null, null, null, 'execdetails');
            init();
        });
    </script>
</head>
<body class="easyui-layout">
<div id="implement_top">
    <form action="" method="post">
        <div class="explain-col">
            <div>
                平台:&nbsp;&nbsp;
                <select id="plat_name" name="platId"></select>&nbsp;&nbsp;
                接口:&nbsp;&nbsp;
                <select id="inter_name" name="interId"></select>&nbsp;&nbsp;
                执行时间:&nbsp;&nbsp;
                <input type="text" class="Wdate" id="implement_old_date" name="inDate"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:$('input[mark=\'EndTime\']').val(),isShowClear:false,readOnly:true})"/>&nbsp;&nbsp;
                到&nbsp;&nbsp;
                <input type="text" class="Wdate" id="implement_new_date" name="outDate"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:$('input[mark=\'EndTime\']').val(),isShowClear:false,readOnly:true})"/>
            </div>
            <div>
                用例名称:&nbsp;&nbsp;<input type="text" id="detail_name" name="dName"/>&nbsp;&nbsp;
                状态:&nbsp;&nbsp;<select id="state" name="state"></select>&nbsp;&nbsp;
                <input type="button" value="搜索" onclick="search_implement()"/>
            </div>
        </div>
    </form>
</div>
<div region="center" class="pad-10">
    <table id="implement_table" class="easyui-datagrid"
           url="${root}/monitor/selectImplementInfoAll.asp"
           pagination="true" fit="true" style="width: 1800px;height: 100%" nowrap="false"
           rownumbers="false" fitColumns="true" singleSelect="true" toolbar="#implement_top">
        <thead>
        <tr>
            <th field="id" width="80" align="center">执行ID</th>
            <th field="pName" width="150" align="center">平台名称</th>
            <th field="iName" width="150" align="center">接口名称</th>
            <th field="dName" width="150" align="center" formatter="opertitle">用例标题</th>
            <th field="detail" width="200" align="center" formatter="operdetail">用例描述</th>
            <th field="result" width="80" align="center" formatter="operstate">最终状态</th>
            <th field="addTime" width="200" align="center">执行时间</th>
        </tr>
        </thead>
    </table>
</div>
</body>
</html>
