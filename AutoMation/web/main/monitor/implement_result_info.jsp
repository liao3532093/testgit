<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/5/4
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>用例预测最终结果表</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="0">
    <link type="text/css" rel="stylesheet" href="http://r.vnetone.com/common/css/system.css"/>
    <link href="http://r.vnetone.com/admin/css/table_form.css" rel="stylesheet" type="text/css"/>
    <link href="http://r.vnetone.com/common/css/tip.css" rel="stylesheet" type="text/css"/>
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
    <script>
        $(function () {
            isPermission(null, null, null, 'execdetails');
        });
    </script>
</head>
<body style="font-size: 15px;">
<div class="pad-10">
    <p><font color="green">用例描述:</font>${cName}</p>
    <br/>
    <c:forEach var="params" items="${params}">
        <fieldset style="width:740px;">
            <legend style="font-size: 12px;">${params.tName}</legend>
            <div>
                <table style="font-size: 13px;">
                    <tr>
                        <td style="color: green;width: 80px;">请求地址：</td>
                        <td>
                            <p style="word-wrap: break-word;word-break:normal;width: 400px;">${params.requestURL}</p>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td style="color: green;width: 80px;">请求参数：</td>
                        <td>
                            <p style="word-wrap: break-word;word-break:normal;width: 400px;">${params.requestParam}</p>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td style="color: green;width: 80px;">同步返回：</td>
                        <td>
                            <p style="word-wrap: break-word;word-break:normal;width: 400px;">${params.returnInfo}</p>
                        </td>
                    </tr>
                </table>
            </div>
        </fieldset>
        <br/>
    </c:forEach>
    <div class="table-list01">
        <table width="760" cellspacing="0" style="font-size: 13px;">
            <thead>
            <tr align="center">
                <th>所属模块</th>
                <th>预测结果</th>
                <th>测试结果详情</th>
                <th>测试最终结果</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="info" items="${infos}">
                <tr align="center">
                    <td>
                        <p style="word-wrap: break-word;word-break:normal;">${info.tName}</p>
                    </td>
                    <td>
                        <p style="word-wrap: break-word;word-break:normal;">${info.tResult}</p>
                    </td>
                    <td>
                        <p style="word-wrap: break-word;word-break:normal;">${info.detail}</p>
                    </td>
                    <c:choose>
                        <c:when test="${info.result==0}">
                            <td><font color="#bc8f8f">等待</font></td>
                        </c:when>
                        <c:when test="${info.result==1}">
                            <td><font color="red">失败</font></td>
                        </c:when>
                        <c:when test="${info.result==2}">
                            <td><font color="green">成功</font></td>
                        </c:when>
                        <c:when test="${info.result==3}">
                            <td><font color="red">脚本异常</font></td>
                        </c:when>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
