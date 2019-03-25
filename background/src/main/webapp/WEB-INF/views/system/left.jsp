<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="static/layui-v2.4.5/layui/css/layui.css"  rel="stylesheet" type="text/css">
<script type="text/javascript" src="static/layui-v2.4.5/layui/layui.js"></script>
<style type="text/css">
body{
	background-color:#393D49;
}
</style>
</head>
<body>
<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo">
<c:forEach items="${resources }" var="res" varStatus="s">
	 <li class="layui-nav-item">
		<a href="javascript:;">${res.resourceNm }</a>
		<c:forEach items="${res.childs }" var="cres" varStatus="cs">
			<dl class="layui-nav-child"><a href="${webRoot }${cres.resourceLink}">${cres.resourceNm }</a></dl>
		</c:forEach>
		</li>
</c:forEach>
</ul>
<script>
layui.use('element', function(){
  var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
  
});
</script>
</body>
</html>