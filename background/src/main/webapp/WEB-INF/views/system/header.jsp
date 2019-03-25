<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="static/layui-v2.4.5/layui/css/layui.css"  rel="stylesheet" type="text/css">
</head>
<body>
<ul class="layui-nav">
  <c:forEach items="${menus }" var="menu" varStatus="s">
  	<c:if test="${menu.resourceLevel==1 }">
  	<li class="layui-nav-item"><a href="${webRoot }/left?resourceCd=${menu.resourceCd}" target="left">${menu.resourceNm }</a></li>
  	</c:if>
  </c:forEach>
  	<li class="layui-nav-item" style="float:right"><a id="logout">退出</a></li>
  	<li class="layui-nav-item" style="float:right">欢迎您：${curUser.userCd }</li>
  	
 </ul>
 
 <script src="static/layui-v2.4.5/layui/layui.js"></script>
 <script>
layui.use(['element','jquery'], function(){
  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
  var $ = layui.jquery;
  $("#logout").click(function(){
	  top.document.location.href="${webRoot}/logout"; 
  });
});
</script>
</body>
</html>