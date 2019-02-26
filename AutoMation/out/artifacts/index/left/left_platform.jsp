<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/4/18
  Time: 17:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<h3 class="f14"><span class="switchs cu on" title="展开与收缩"></span>平台管理</h3>
<ul class="hidden">
    <li id="_MP1" class="sub_menu"><a href="javascript:_MP(1,'${root}/main/platform/platform_manage.jsp','平台管理列表');"
                                      hidefocus="true"
                                      style="outline:none;">平台管理列表</a></li>
    <li id="_MP2" class="sub_menu"><a href="javascript:_MP(2,'${root}/main/platform/platform_interface.jsp','接口配置列表');"
                                      hidefocus="true" style="outline:none;">接口配置列表</a></li>
    <li id="_MP3" class="sub_menu"><a href="javascript:_MP(3,'${root}/main/platform/platform_request.jsp','请求模块列表');"
                                      hidefocus="true" style="outline:none;">请求模块列表</a></li>
    <li id="_MP4" class="sub_menu"><a href="javascript:_MP(4,'${root}/main/platform/platform_check.jsp','校验模块列表');"
                                      hidefocus="true" style="outline:none;">校验模块列表</a></li>
    <li id="_MP5" class="sub_menu"><a href="javascript:_MP(5,'${root}/main/platform/platform_request_db.jsp','DB配置模块');"
                                      hidefocus="true" style="outline:none;">DB配置模块</a></li>
</ul>
<script type="text/javascript" src="http://r.vnetone.com/common/js/leftMenu.js"></script>
