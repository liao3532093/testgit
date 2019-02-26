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
    <title>添加预测结果</title>
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
    <script src="${root}/script/example/expected_results.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission(null, null, null, 'exaConfig');
        });
        $(document).ready(function () {
            initAdd();
        });
    </script>
</head>
<body>
<div class="pad-10">
    <form id="exp_result_form" method="post">
        <table class="table-list01" style="width:100%">
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>测试用例：</td>
                <td>
                    <font id="case_detail_name"></font>
                    <input type="hidden" id="caseModelId" name="caseModelId"/>
                    <input type="hidden" id="exp_result_id" name="id" value="-1"/>
                </td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>预期详情：</td>
                <td><textarea rows="3" style="width: 100%;" id="detail" name="detail"></textarea></td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>预测类型：</td>
                <td>
                    <select id="expectType" onchange="getScript()"></select>
                    <input type="hidden" id="expectTypeId" name="expectTypeId" value="-1"/>
                </td>
            </tr>
            <tr id="imple_script">
                <td style="width: 110px;text-align: right;"><font color="red">*</font>执行脚本：</td>
                <td>
                    <input type="hidden" id="sqlId" name="sqlId" value="-1"/>
                    常用脚本：<select id="common_script" onchange="getScriptUrl()" style="width: 200px;"></select><br/>
                    <textarea id="script_info" rows="5" style="width: 100%;margin-top: 5px;"
                              disabled="disabled"></textarea>
                </td>
            </tr>
            <tr id="imple_value">
                <td style="width: 110px;text-align: right;"><font color="red">*</font>预期值：</td>
                <td>
                    <input id="exp_value" type="text" style="width: 100%" disabled="disabled"/><br/>
                    <font color="red">注：预期值运算符<,>,<=,>=,LIKE,=</font>
                </td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>执行顺序：</td>
                <td><input type="text" onkeyup="value=value.replace(/[^\d]/g,'') "
                           onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
                           id="orderNum" name="orderNum"/>
                </td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>状态：</td>
                <td><select id="isEnable" name="isEnable">
                    <option value="-1">请选择</option>
                    <option value="1">启用</option>
                    <option value="0">禁用</option>
                </select></td>
            </tr>
        </table>
    </form>
    <div style="padding: 10px;">
        <input style="width: 100px;height: 30px;float: right;" type="button" value="确定" onclick="save_exp_result()"/>
    </div>
</div>
</body>
</html>
