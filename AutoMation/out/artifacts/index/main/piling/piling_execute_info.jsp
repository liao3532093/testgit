<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/6/8
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>立即执行和批量执行结果</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="0">
    <link type="text/css" rel="stylesheet" href="http://r.vnetone.com/common/css/system.css"/>
    <link href="http://r.vnetone.com/admin/css/table_form.css" rel="stylesheet" type="text/css"/>
    <link href="http://r.vnetone.com/common/css/tip.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="http://r.vnetone.com/common/css/jquery.dataTables.css"/>
    <script type="text/javascript" src="http://r.vnetone.com/common/js/jquery/jquery-1.9.1.min.js"></script>
    <script src="http://r.vnetone.com/common/js/plus/artDialog/jquery.artDialog.js?skin=default"
            type="text/javascript"></script>
    <script src="http://r.vnetone.com/common/js/plus/artDialog/plugins/iframeTools.js"
            type="text/javascript"></script>
    <script src="http://r.vnetone.com/common/js/tip.js" type="text/javascript" language="javascript"></script>
    <script src="http://r.vnetone.com/common/js/plus/vdate/vdate.js" language="javascript"
            type="text/javascript"></script>
    <script src="${root}/jquery/jquery-form.js" type="text/javascript"></script>
    <script src="${root}/jquery/jquery.json.js" type="text/javascript"></script>
    <script src="${root}/jquery/jquerySession.js" type="text/javascript"></script>
    <script src="${root}/script/utils/permissionUtils.js" type="text/javascript"></script>
    <script src="${root}/script/utils/utils.js" type="text/javascript"></script>
    <script>
        $(function () {
            isListPermission([], null, 'chanExecList');
        });
    </script>
</head>
<body style="font-size: 15px;">
<div class="pad-10">
    <c:forEach var="params" items="${PILEXECUTE}">
        <fieldset style="width:740px;">
            <legend style="font-size: 12px;">执行ID:${params.id}</legend>
            <div>
                <table style="font-size: 13px;">
                    <tr>
                        <td style=" color: green;width: 80px;">返回信息：
                        </td>
                        <td>${params.message}</td>
                    </tr>
                </table>
            </div>
        </fieldset>
        <br/>
    </c:forEach>
</div>
</body>
</html>
