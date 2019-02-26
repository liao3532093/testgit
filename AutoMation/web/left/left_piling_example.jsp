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
<h3 class="f14"><span class="switchs cu on" title="展开与收缩"></span>渠道用例管理</h3>
<ul class="hidden">
    <li id="_MP1" class="sub_menu"><a href="javascript:_MP(1,'${root}/main/piling/piling_example.jsp','用例列表');"
                                      hidefocus="true"
                                      style="outline:none;">用例列表</a></li>
</ul>
<h3 class="f14"><span class="switchs cu on" title="展开与收缩"></span>渠道配置管理</h3>
<ul class="hidden">
    <li id="_MP2" class="sub_menu"><a
            href="javascript:_MP(2,'${root}/main/piling/piling_model.jsp','模块管理');" hidefocus="true"
            style="outline: none;">模块管理</a></li>
</ul>
<script type="text/javascript" src="http://r.vnetone.com/common/js/leftMenu.js"></script>
