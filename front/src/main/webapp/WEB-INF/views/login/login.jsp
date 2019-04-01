<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="static/layui-v2.4.5/layui/css/layui.css"  rel="stylesheet" type="text/css">
<script type="text/javascript" src="static/layui-v2.4.5/layui/layui.js"></script>
<style type="text/css">
div.content{
	padding:15px;
}
</style>
</head>
<body>
<div class="layui-container content">
	<div class="layui-col-md6 layui-col-md-offset4">
		<form class="layui-form" action="${webRoot }/login" method="post">
		
		<div class="layui-form-item">
			<label class="layui-form-label">手机号码</label>
			<div class="layui-input-inline">
      			<input type="text" name="phone" value="${user.phone }" required  lay-verify="required" placeholder="请输入您的手机号码" autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">密码</label>
			<div class="layui-input-inline">
      			<input type="password" name="password" value="${user.password }" required  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
			</div>
		</div>
		
	<div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="registForm">登录</button>
	      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
	    </div>
    </div>
    
    <div class="layui-form-item">
	${error }    
    </div>
		</form>
	</div>
</div>
<script>
layui.use(['jquery','form','layer'], function(){
	  var form = layui.form;
	  var $=layui.jquery;
	  var layer = layui.layer;
	  
	  form.on('submit(registForm)',function(data){
		  return true;
	  });
	});
</script>
</body>
</html>