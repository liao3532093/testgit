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
    <title>添加渠道用例模块</title>
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
    <script src="${root}/script/utils/pako.js" type="text/javascript"></script>
    <script src="${root}/script/utils/utils.js" type="text/javascript"></script>
    <script src="${root}/script/utils/Log.js" type="text/javascript"></script>
    <script src="${root}/script/piling/add_piling_model.js" type="text/javascript"></script>
</head>
<body>
<div class="pad-10">
    <form id="piling_model_form" method="post">
        <table class="table-list01" style="width:100%">
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>所属平台：</td>
                <td><select id="plat_name" class="add_model_pid" name="platId"></select>
                    <input id="piling_model_id" name="id" type="hidden" value="-1"/></td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>模块类型：</td>
                <td><select id="piling_model_type" name="modelType"></select>
                </td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>模块名称：</td>
                <td><input id="piling_model_name" name="modelName" type="text" style="width: 150px;"/></td>
            </tr>
            <%--<tr class="piling_order">--%>
            <%--<td style="width: 110px;text-align: right;"><font color="red">*</font>返回参数：</td>--%>
            <%--<td><input id="piling_result_item" name="responseItem" type="text" style="width: 100%"/></td>--%>
            <%--</tr>--%>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>请求方式：</td>
                <td><select id="piling_request_type" name="qType"></select></td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>请求地址：</td>
                <td><textarea id="piling_request_url" name="requestUrl" style="width: 100%;font-size: 14px;"
                              rows="3"></textarea></td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>请求/返回参数：</td>
                <td><textarea id="piling_result_item" name="requestItem" style="width: 100%;font-size: 14px;"
                              rows="5"></textarea></td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>状态：</td>
                <td><select id="piling_model_state" name="state"></select></td>
            </tr>
        </table>
    </form>
    <div style="padding: 10px;">
        <table border="1">
            <tr>
                <td style="width: 90%">
                    <span style="color: red">注：请求参数中若存在方法类型的参数，需在参数后加^,如sp^=30001</span>
                </td>
                <td style="width: 10%">
                    <input style="width: 100px;height: 30px;" type="button" value="确定"
                           onclick="savePilingModel()"/>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
