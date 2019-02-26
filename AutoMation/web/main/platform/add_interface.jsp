<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/4/20
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>添加平台接口</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="0">
    <link type="text/css" rel="stylesheet" href="http://r.vnetone.com/common/css/system.css"/>
    <link href="http://r.vnetone.com/admin/css/table_form.css" rel="stylesheet" type="text/css"/>
    <link href="http://r.vnetone.com/common/css/tip.css" rel="stylesheet" type="text/css"/>
    <link href="${root}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://r.vnetone.com/common/js/jquery/jquery-1.9.1.min.js"></script>
    <script src="http://r.vnetone.com/common/js/plus/artDialog/jquery.artDialog.js?skin=default"
            type="text/javascript"></script>
    <script src="http://r.vnetone.com/common/js/plus/artDialog/plugins/iframeTools.js"
            type="text/javascript"></script>
    <script src="http://r.vnetone.com/common/js/tip.js" type="text/javascript" language="javascript"></script>
    <script src="${root}/jquery/jquery-form.js" type="text/javascript"></script>
    <script src="${root}/jquery/jquery.json.js" type="text/javascript"></script>
    <script src="${root}/jquery/jquerySession.js" type="text/javascript"></script>
    <script src="${root}/script/utils/permissionUtils.js" type="text/javascript"></script>
    <script src="${root}/script/platform/platform_interface.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission(null, null, null, 'platInterface');
        });
        $(document).ready(function () {
            var id = $.session.get('id');
            if (id == undefined) {
                initData(1, '#plat_name', id);
            } else {
                initData(1, '#plat_name', id);
            }
        });
    </script>
</head>
<body>
<div class="pad-10">
    <form id="interface_form" method="post">
        <table class="table-list01" style="width:100%">
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>所属平台：</td>
                <td><select id="plat_name" name="platId"></select></td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>接口名称：</td>
                <td><input id="interface_name" name="title" type="text" style="width:100%;"/>
                    <input id="interface_id" name="id" type="hidden" value="-1"/>
                </td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>状态：</td>
                <td><select id="interface_type" name="isEnable">
                    <option value="-1">请选择</option>
                    <option value="1">启用</option>
                    <option value="0">禁用</option>
                </select></td>
            </tr>
        </table>
    </form>
    <div style="padding: 10px;">
        <input style="width: 100px;height: 30px;float: right;" type="button" value="确定" onclick="savePlatInterface()"/>
    </div>
</div>
</body>
</html>
