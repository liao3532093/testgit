<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/18
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>自动化测试</title>
    <link type="text/css" rel="stylesheet" href="http://r.vnetone.com/common/css/system.css"/>
    <link href="http://r.vnetone.com/admin/css/table_form.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${root}/jquery-easyui-1.3.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${root}/jquery-easyui-1.3.3/themes/icon.css">
    <script type="text/javascript" src="http://r.vnetone.com/common/js/jquery/jquery-1.9.1.min.js"></script>
    <script src="http://r.vnetone.com/common/js/plus/artDialog/jquery.artDialog.js?skin=default"
            type="text/javascript"></script>
    <script src="http://r.vnetone.com/common/js/plus/artDialog/plugins/iframeTools.js"
            type="text/javascript"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script>
    <script type="text/javascript" src="${root}/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${root}/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${root}/script/utils/Log.js"></script>
    <%--<script type="text/javascript" src="${root}/script/utils/goeasyutils.js"></script>--%>
    <script type="text/javascript" src="${root}/script/main.js"></script>
</head>
<body>
<div class="header">
    <div class="admin_logo Automate_logo">
    </div>
    <div class="white" style="float: right; padding: 18px 10px 0 0;">
        <%--待定--%>
    </div>
    <div class="col-auto" style="overflow: visible">
        <div class="log white cut_line">
            您好！${user.showName}&nbsp;<span>|</span>
            &nbsp; <a href="javascript:update_user_pwd()">修改资料</a> &nbsp;<span>|</span> &nbsp;
            <a href="###" onclick="exit()">退出</a>
        </div>
        <ul class="nav white" id="top_menu">
            <li id="_M1" class="on top_menu" title="用户管理"><a
                    href="javascript:_M(1,'${root}/left/left_system.jsp','${root}/main/system/system_user.jsp')"
                    hidefocus="true" style="outline: none;">系统管理</a></li>
            <li id="_M2" class="top_menu" title="平台管理列表"><a
                    href="javascript:_M(2,'${root}/left/left_platform.jsp','${root}/main/platform/platform_manage.jsp')"
                    hidefocus="true" style="outline: none;">平台管理</a></li>
            <li id="_M3" class="top_menu" title="用例配置管理"><a
                    href="javascript:_M(3,'${root}/left/left_example.jsp','${root}/main/example/example_config.jsp')"
                    hidefocus="true" style="outline: none;">用例管理</a></li>
            <li id="_M4" class="top_menu" title="渠道用例管理"><a
                    href="javascript:_M(4,'${root}/left/left_piling_example.jsp','${root}/main/piling/piling_example.jsp')"
                    hidefocus="true" style="outline: none;">渠道测试管理</a></li>
            <li id="_M5" class="top_menu" title="执行详情列表"><a
                    href="javascript:_M(5,'${root}/left/left_implement.jsp','${root}/main/monitor/implement_info.jsp')"
                    hidefocus="true" style="outline: none;">监控管理</a></li>
            <li id="_M6" class="top_menu" title="日志管理"><a
                    href="javascript:_M(6,'${root}/left/left_log.jsp','${root}/main/log/system_log.jsp')"
                    hidefocus="true" style="outline: none;">日志管理</a></li>
        </ul>
    </div>
</div>
<div id="content">
    <div class="col-left left_menu">
        <div id="leftMain">
        </div>
        <a href="javascript:;" id="openClose" style="outline-style: none; outline-color: invert;
                outline-width: medium;" hidefocus="hidefocus" class="open" title="展开与关闭"><span class="hidden">
                    展开</span></a>
    </div>
    <div class="col-1 lf cat-menu" id="display_center_id" style="display: none" height="100%">
        <div class="content">
            <iframe name="center_frame" id="center_frame" src="" frameborder="false" scrolling="auto"
                    style="border: none" width="100%" height="auto" allowtransparency="true"></iframe>
        </div>
    </div>
    <div class="col-auto mr8">
        <div class="crumbs">
            <div class="shortcut cu-span">
            </div>
            <div id="now_position">
                当前位置：<span id="current_pos"></span></div>
            <div id="topic">
                <div class="wrap">
                    <ul id="ullist">
                    </ul>
                    <ul id="scroll">
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-1">
            <div class="content" style="position: relative; overflow: hidden">
                <iframe name="right" id="rightMain" src="${root}/main/system/system_user.jsp" frameborder="false"
                        scrolling="auto" style="border: none; margin-bottom: 30px" width="100%" height="auto"
                        allowtransparency="true"></iframe>
                <div class="fav-nav">
                </div>
            </div>
        </div>
    </div>
</div>
<script src="http://r.vnetone.com/common/js/default.js" type="text/javascript"></script>
<script type="text/javascript">
    function getURLParameter(name) {
        return decodeURI(
            (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search) || [, null])[1] || ''
        );
    }
    var ulid = getURLParameter('ulid');
    var liid = getURLParameter('liid');
    var mid = getURLParameter('mid');
    var left_url = getURLParameter('left_url');
    var right_url = getURLParameter('right_url');
    if (mid) {
        _M(mid, left_url, right_url);
    } else {
        $(function () {
            //默认载入左侧菜单
            $("#leftMain").load("${root}/left/left_system.jsp");
            $("#current_pos").html("用户管理");
        });
    }

    window.setInterval(GetGatewayAlter, 300000);

    var $tab = $("#topic");
    var $tab1 = $("#ullist");
    var $tab2 = $("#scroll");

    //设置序号
    function GetGatewayAlter() {
        $.get("/ajax/Ajax_Gateway.aspx", {r: Math.random(), t: 'getawayalter'}, function (json) {
            $tab1.html(json);
            $tab2.html($tab1.html());
        });
    }
    function Marquee() {
        if ($tab2[0].offsetWidth - $tab[0].scrollLeft <= 0)
            $tab[0].scrollLeft -= $tab1[0].offsetWidth;
        else {
            $tab[0].scrollLeft++;
        }
    }
    var MyMar = setInterval(Marquee, 30);
    $tab.hover(function () {
        clearInterval(MyMar);
    }, function () {
        MyMar = setInterval(Marquee, 30);
    });

    window.onload = function () {
        if (ulid) {
            $("#leftMain").find('ul:eq(' + (ulid - 1) + ')').removeClass("hidden");
            $("#leftMain").find('#_MP' + liid).addClass("on fb blue");
        }
        GetGatewayAlter();
    }
</script>
</body>
</html>
