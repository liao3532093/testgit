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
    <title>添加校验模块</title>
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
    <script src="${root}/script/platform/platform_check.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission(null, null, null, 'platCheck');
        });
        $(document).ready(function () {
            init(1, '#plat_name', '${root}/PManage/selectPlatformManageByName.asp');
            init(1, '#pread_name', '${root}/prediction/selectPredIsEnableByName.asp');
            loadInfo();
        });
    </script>
</head>
<body>
<div class="pad-10">
    <form id="plat_check_form" method="post">
        <table class="table-list01" style="width:100%">
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>所属平台：</td>
                <td><select id="plat_name" name="platID"></select></td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>预测类型：</td>
                <td>
                    <select id="pread_name" name="expectTypeID"></select>
                    <input id="plat_check_id" name="id" type="hidden" value="-1"/>
                </td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>脚本说明：</td>
                <td><textarea id="script_txt" name="title" rows="2" style="width: 100%;"></textarea>
                </td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>脚本：</td>
                <td><textarea id="script" name="executeSql" rows="5" style="width: 100%;"></textarea><br/>
                    <font color="red">
                        注：若脚本存在Where 条件时，参数值必须和请求时的参数名称一致，<br/>
                        例如：SELECT ID FROM ORDER_INFO WHERE SPID=sp <br/>
                        AND SPOrder=orderid
                    </font>
                </td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font> 预期值：</td>
                <td>
                    <input id="expect_value" name="expectValue" type="text" value="" style="width: 100%;"/><br/>
                    <font color="red">注：预期值运算符<,>,<=,>=,LIKE,=</font>
                </td>
            </tr>
        </table>
    </form>
    <div style="padding: 10px;">
        <input style="width: 100px;height: 30px;float: right;" type="button" value="确定" onclick="savePlatCheck()"/>
    </div>
</div>
</body>
</html>
