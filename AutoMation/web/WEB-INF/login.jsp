<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/5/10
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>自动化测试</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="0">
    <link rel="stylesheet" href="http://r.vnetone.com/admin/css/login.css" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${root}/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${root}/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="http://r.vnetone.com/common/js/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script>
    <script type="text/javascript" src="${root}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${root}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script src="${root}/jquery/jquery-form.js" type="text/javascript"></script>
    <script type="text/javascript" src="${root}/script/utils/Log.js"></script>
    <%--<script type="text/javascript" src="${root}/script/utils/goeasyutils.js"></script>--%>
    <script type="text/javascript" src="${root}/script/gVerify.js"></script>
    <script type="text/javascript" src="${root}/script/login.js"></script>
</head>
<body>
<div id="loginBox2" class="Automate_loginbox">
    <form id="login_form" method="post">
        <div class="username">
            <h5>用户名：</h5>
            <input class="text" name="account" id="account" autocomplete="off" type="text" value=""
                   style="width:330px;">
        </div>
        <div class="password">
            <h5>密　码：</h5>
            <input class="text" name="password" id="password" autocomplete="off" type="password" style="width:330px;"
                   value="">
        </div>
        <div class="code">
            <h5>验证码：</h5>
            <input type="text" id="txtcode" name="code" autocomplete="off" class="text"
                   style="width: 140px; margin-right:6px;" maxlength="4"/>
            <span id="ver_code"></span>
            <span>看不清，<a title="看不清,点击更换验证码" href="javascript:;" onclick="readyCode()">换一张</a></span>
        </div>
        <div class="button">
            <input type="button" class="btnEnter" id="submitButton" value=" " onclick="login()"/>
        </div>
    </form>
</div>
</body>
</html>
