<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="static/layui-v2.4.5/layui/css/layui.css"  rel="stylesheet" type="text/css">
<script type="text/javascript" src="static/layui-v2.4.5/layui/layui.js"></script>
<style>
div.header{
	background-color: #eee;
	height:40px;
	margin:0px;
	width:100%;
	line-height:40px;
}
div.logo{
	height:90px;
	text-align: center;
	line-height:1.35;
	padding:23px 0 0 7px;
	font-size:16px;
}
div.nav{
	font-size:16px;
	height:90px;
	line-height:90px;
}
hr{
 margin:0px;
}
</style>
</head>
<body>
<div class="layui-container header">
	<div class="layui-col-md1">&nbsp;&nbsp;
    </div>
	<div class="layui-col-md5">
      <div class="">服务热线:400-828-1949 &nbsp;&nbsp;QQ群号：205816335&nbsp;&nbsp;服务时间：9:00 - 18:00</div>
    </div>
    <div class="layui-col-md1 layui-col-md-offset1">登录</div>
    <div class="layui-col-md1"><a href="personalRegist">个人注册</a></div>
    <div class="layui-col-md1"><a href="enterpriseRegist">企业注册</a></div>
    <div class="layui-col-md1">帮助中心</div>
</div>

<div class="layui-container">
    <div class="layui-col-md2">
   		<img src="static/img/logo_14.png"/>
   	</div>
    <div class="layui-col-md2 logo">
    国资互联网文化金融<br/>综合服务平台
    </div>
    
    <div class="layui-col-md1 layui-col-md-offset3 nav" ><a href="${webRoot }">首页</a></div>
    <div class="layui-col-md1 nav">我要投资</div>
    <div class="layui-col-md1 nav">大数据介绍</div>
    <div class="layui-col-md1 nav">信息披露</div>
    <div class="layui-col-md1 nav">我的账户</div>
</div>
<hr style="margin:0px"/>
</body>
</html>