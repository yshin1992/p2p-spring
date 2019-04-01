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
		<form class="layui-form" action="${webRoot }/personalRegist" method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">用户名</label>
			<div class="layui-input-inline">
      			<input type="text" name="nickName" value="${user.nickName }" required  lay-verify="required" placeholder="请输入您的用户名" autocomplete="off" class="layui-input">
			</div>
		</div>
		
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
			<label class="layui-form-label">确认密码</label>
			<div class="layui-input-inline">
      			<input type="password" name="confirm" value="${user.confirm }" required  lay-verify="required" placeholder="确认密码" autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">地址</label>
			<div class="layui-input-inline">
      			<input type="text" name="address" value="${user.address }" required  lay-verify="required" placeholder="请输入您的地址" autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">邮箱</label>
			<div class="layui-input-inline">
      			<input type="text" name="email" value="${user.email }" required  lay-verify="required" placeholder="请输入您的邮箱" autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">验证码</label>
			<div class="layui-input-inline">
      			<input type="text" name="captcha" required  lay-verify="required" placeholder="请输入图形验证码" autocomplete="off" class="layui-input">
			</div>
			<div class="layui-form-mid layui-word-aux"><img id="captchaImg" src="${webRoot }/login/captcha" style="width:55px;height:35px;"/></div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">手机验证码</label>
			<div class="layui-input-inline">
      			<input type="text" name="vfCode" required  lay-verify="required" placeholder="请输入手机验证码" autocomplete="off" class="layui-input">
				<div class="layui-form-mid layui-word-aux"><input type="button" id="getVfBtn" class="layui-btn layui-btn-primary" value="获取验证码"/></div>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">推荐人</label>
			<div class="layui-input-inline">
      			<input type="text" name="promotionId"  placeholder="选填" autocomplete="off" class="layui-input">
			</div>
		</div>
	<div class="layui-form-item">
	    <div class="layui-input-block">
	    	<input type="checkbox" name="read" lay-skin="primary" checked="checked"/>我同意并愿意遵行<a href="${webRoot }/registerNotice" target="_blank">《莫愁信融注册协议》</a> 
	    </div>
    </div>
	<div class="layui-form-item">
	    <div class="layui-input-block">
	      <button class="layui-btn" lay-submit lay-filter="registForm">注册</button>
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
	  var href = $("#captchaImg").attr("src");
	  $("#captchaImg").click(function(e){
		  $("#captchaImg").attr('src',href+"?timestamp="+(new Date()).getTime()); 
	  });
	  
	  function countDown(){
		  var text = $("#getVfBtn").val();
		  var remain = parseInt(text.substring(0,text.indexOf('秒')))-1;
		  $("#getVfBtn").attr("value",remain+ "秒后可再获取");
		  if(remain>0){
			  setTimeout(countDown, 1000);
		  }else{
			  $("#getVfBtn").attr("value","获取验证码");
			  $("#getVfBtn").removeAttr("disabled");
		  }
	  }
	  
	  $("#getVfBtn").click(function(){
		  $("#getVfBtn").attr("value", "60秒后可再获取");
		  $("#getVfBtn").attr("disabled","true");
		  $.post("${webRoot}/sendCode",$("form").serialize(),function(data){
			  if(data.code != 200){
				  alert(data.msg);
			  }else{
				  alert("短信发送成功!");
			  }
		  },"json");
		  
		  countDown();
	  });
	  
	  form.on('submit(registForm)',function(data){
		  return true;
	  });
	});
</script>
</body>
</html>