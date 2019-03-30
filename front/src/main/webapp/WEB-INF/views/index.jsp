<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="static/layui-v2.4.5/layui/css/layui.css"  rel="stylesheet" type="text/css">
<script type="text/javascript" src="static/layui-v2.4.5/layui/layui.js"></script>
<style>
</style>
</head>
<body>
<div class="layui-carousel" id="test10">
  <div carousel-item="">
    <div><img src="static/img/950B6151B44A4905A2C0683C3DB22A41.jpg"></div>
    <div><img src="static/img/89D941D8BDDB4DDE9E54660A75C00F17.jpg"></div>
    <div><img src="static/img/09BF4E3FB20A4B559DB8DFB31B90ADEB.jpg"></div>
  </div>
</div>

<script>
layui.use(['carousel', 'form'], function(){
  var carousel = layui.carousel
  ,form = layui.form;
  
  carousel.render({
	    elem: '#test10'
	    ,width: ''
	    ,height: '400px'
	    ,interval: 3000
	  });
});
</script>
</body>
</html>
