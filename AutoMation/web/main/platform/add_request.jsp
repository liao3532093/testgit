<%--
  Created by IntelliJ IDEA.
  User: vsofo
  Date: 2017/4/24
  Time: 9:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>添加请求</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
    <META HTTP-EQUIV="Expires" CONTENT="0">
    <link type="text/css" rel="stylesheet" href="http://r.vnetone.com/common/css/system.css"/>
    <link href="http://r.vnetone.com/admin/css/table_form.css" rel="stylesheet" type="text/css"/>
    <link href="http://r.vnetone.com/common/css/tip.css" rel="stylesheet" type="text/css"/>
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
    <script src="${root}/script/utils/Log.js" type="text/javascript"></script>
    <script src="${root}/script/platform/platform_request.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function () {
            isPermission(null, null, null, 'platRquest');
        });
        $(document).ready(function () {
            $('.request_type_tr').hide();
            var id = $.session.get('id');
            if (id == undefined) {
                var url = projectName + '/PManage/selectPlatformManageByName.asp';
                init(url, 1, '#plat_name', -1);  //平台信息
                url = projectName + '/PManage/selectInterfaceByName.asp';
                init(url, 1, '#inter_name', -1);  //接口信息
            } else {
                var url = projectName + '/PManage/selectPlatformManageByName.asp';
                init(url, 1, '#plat_name', id);  //平台信息
                url = projectName + '/PManage/selectInterfaceByName.asp';
                init(url, 0, '#inter_name', -1);  //接口信息
            }
            $.session.remove('id');
        });
    </script>
</head>
<body>
<div class="pad-10">
    <form id="request_form" method="post">
        <table class="table-list01" style="width:100%">
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>所属平台：</td>
                <td><select id="plat_name" name="platId"></select></td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>接口名称：</td>
                <td><select id="inter_name" name="interfaceId"></select></td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>模块名称：</td>
                <td><input id="request_name" name="title" type="text" style="width:100%;"/>
                    <input id="request_id" name="id" type="hidden" value="-1"/>
                </td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>模块地址：</td>
                <td><textarea id="request_url" name="requestUrl" rows="5" style="width: 100%;"></textarea></td>
            </tr>
            <tr>
                <td style="width: 110px;text-align: right;"><font color="red">*</font>请求方式：</td>
                <td><select id="request_type" name="requestType" onchange="loadRequestType()">
                    <option value="-1">请选择</option>
                    <option value="1">POST</option>
                    <option value="2">GET</option>
                </select></td>
            </tr>
            <tr class="request_type_tr">
                <td style="width: 110px;text-align: right;"><font color="red">*</font>参数格式：</td>
                <td>
                    <label><input name="paramType" type="radio" value="1"/>XML</label>
                    <label><input name="paramType" type="radio" value="2" checked="checked"/>JSON</label>
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
        <input style="width: 100px;height: 30px;float: right;" type="button" value="确定" onclick="saveRequestInfo()"/>
    </div>
</div>
</body>
</html>
