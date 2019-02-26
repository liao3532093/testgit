<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/5/10
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>404页面</title>
    <%--<style type="text/css">--%>
    <%--body {--%>
    <%--margin: 0;--%>
    <%--padding: 0;--%>
    <%--background: #efefef;--%>
    <%--font-family: Georgia, Times, Verdana, Geneva, Arial, Helvetica, sans-serif;--%>
    <%--}--%>

    <%--div#mother {--%>
    <%--margin: 0 auto;--%>
    <%--width: 943px;--%>
    <%--height: 572px;--%>
    <%--position: relative;--%>
    <%--}--%>

    <%--div#errorBox {--%>
    <%--background: url(${root}/images/404_bg.png) no-repeat top left;--%>
    <%--width: 943px;--%>
    <%--height: 572px;--%>
    <%--margin: auto;--%>
    <%--}--%>

    <%--div#errorText {--%>
    <%--color: #39351e;--%>
    <%--padding: 146px 0 0 446px--%>
    <%--}--%>

    <%--div#errorText p {--%>
    <%--width: 303px;--%>
    <%--font-size: 14px;--%>
    <%--line-height: 26px;--%>
    <%--}--%>

    <%--div.link { /*background:#f90;*/--%>
    <%--height: 50px;--%>
    <%--width: 145px;--%>
    <%--float: left;--%>
    <%--}--%>

    <%--div#home {--%>
    <%--margin: 20px 0 0 444px;--%>
    <%--}--%>

    <%--div#contact {--%>
    <%--margin: 20px 0 0 25px;--%>
    <%--}--%>

    <%--h1 {--%>
    <%--font-size: 40px;--%>
    <%--margin-bottom: 35px;--%>
    <%--}--%>
    <%--</style>--%>
</head>
<body>
<%--<div id="mother">--%>
<%--<div id="errorBox">--%>
<%--<div id="errorText">--%>
<%--<h1>Sorry..你没有这个权限查看！</h1>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<div style="margin: 100px auto;width: 600px;">
    <p style="font-size: 16px;color: #4A8AAF;">提示信息</p>
    <p style="height: 1px;background-color: #4A8AAF;"></p>
    <div>
        <img src="${root}/images/cancel.png" rel="错误图片" style="width: 80px;height: 80px;float: left;">
        <span style="color: #ff0000;float: left;margin-left: 20px;">无权限操作本项</span>
    </div>
</div>
</body>
</html>
