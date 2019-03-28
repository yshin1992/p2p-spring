<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
<link rel="stylesheet" href="static/layui-v2.4.5/layui/css/layui.css">
<style>
img{
	height:30px;
}
.center{
	width:400px;
	margin:0 auto;
	padding:auto;
}
.center h1{
	text-align:center;
	color:blue;
}
</style>
</head>
<body>

<div class="center">
<h1>后台管理系统</h1>
<form class="layui-form" action="${webRoot }/login" method="post">
  <div class="layui-form-item">
    <label class="layui-form-label">用户名</label>
    <div class="layui-input-inline">
      <input type="text" name="username" required lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item">
    <label class="layui-form-label">密码</label>
    <div class="layui-input-inline">
      <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">验证码</label>
    <div class="layui-input-inline">
      <input type="password" name="captcha" required lay-verify="required" placeholder="请输入验证码" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux"><img id="captchaImg" src="login/captcha.png"/></div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <input type="checkbox" name="rememberMe" lay-skin="primary" title="记住密码">
    </div>
  </div>
  <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" type="submit">立即提交</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
 </form>
 <div>${error }</div>
 </div>
<script src="static/layui-v2.4.5/layui/layui.js"></script>
<script>
layui.use(['jquery','form'], function(){
	  var form = layui.form;
	  var $=layui.$;
	  var href = $("#captchaImg").attr("src");
	  $("#captchaImg").click(function(e){
		  $("#captchaImg").attr('src',href+"?timestamp="+(new Date()).getTime()); 
	  });
	});
</script>
</body>
</html>