<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/5/10
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>修改用户信息</title>
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
    <script src="${root}/script/system/update_user_info.js" type="text/javascript"></script>
    <script>
        $(function () {
            isPermission(null, null, null, 'user');
        });
    </script>
</head>
<body>
<div class="pad-10">
    <form id="update_user_form" method="post">
        <table class="table-list01" style="width:100%">
            <tr>
                <td class="tdbg" style="width: 110px;text-align: right;"><font color="red">*</font>账号：</td>
                <td><input type="text" id="account" name="account" style="width:100%;" disabled="disabled"/></td>
            </tr>
            <tr id="account_tr">
                <td class="tdbg" style="width: 110px;text-align: right;"><font color="red">*</font>显示名称：</td>
                <td><input id="showName" name="showName" type="text" style="width:100%;"/>
                    <input id="user_id" name="id" type="hidden" value="-1"/>
                </td>
            </tr>
            <tr id="pass_tr">
                <td class="tdbg" style="width: 110px;text-align: right;"><font color="red">*</font>当前密码：</td>
                <td><input type="password" id="password" name="password" style="width:100%;"/></td>
            </tr>
            <tr id="old_tr">
                <td class="tdbg" style="width: 110px;text-align: right;"><font color="red">*</font>新密码：</td>
                <td><input type="password" id="oldPass" name="oldPass" style="width:100%;"/></td>
            </tr>
            <tr id="new_tr">
                <td class="tdbg" style="width: 110px;text-align: right;"><font color="red">*</font>确认密码：</td>
                <td><input type="password" id="newPass" name="newPass" style="width:100%;"/></td>
            </tr>
        </table>
    </form>
    <div style="padding: 10px;">
        <input style="width: 100px;height: 30px;float: right;" type="button" value="确定" onclick="saveUser()"/>
    </div>
</div>
</body>
</html>
