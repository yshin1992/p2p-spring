<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="${webRoot }/static/layui-v2.4.5/layui/css/layui.css"  rel="stylesheet" type="text/css">
<script type="text/javascript" src="${webRoot }/static/layui-v2.4.5/layui/layui.js"></script>
<style type="text/css">
body{
	padding:10px;
}
.tip{
	color:gray;
}
#contentdiv{
	display:none;
}
</style>
</head>
<div class="layui-form">
	<div class="layui-form-item">
    <div class="layui-input-inline">
      <input type="checkbox" name="isUseIntegral" lay-filter="isUseIntegral" title="启用/停用积分规则" lay-skin="primary" ${item.isUseIntegral==1 ? "checked":"" }>
    </div>
  </div>
</div>
<hr/>
<div id="contentdiv">
<div class="layui-form-item">
	<h2 class="layui-input-block">积分发放条件设置 <span class="gray">不获得积分请填写0</span></h2>
</div>

 <div class="layui-form-item">
    <label class="layui-form-label">注册：</label>
    <div class="layui-input-inline">
      <input type="text" name="registerGiveIntegral" value="${item.registerGiveIntegral }" required lay-verify="required" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">积分</div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">登录：</label>
    <div class="layui-input-inline">
      <input type="text" name="loginGiveIntegral" value="${item.loginGiveIntegral }" required lay-verify="required" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">积分  注：每天只计算一次有效登录</div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">推荐好友：</label>
    <div class="layui-input-inline">
      <input type="text" name="recommendFriendsGiveIntegral" value="${item.recommendFriendsGiveIntegral }" required lay-verify="required" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">积分</div>
  </div>
  <hr/>
  <div class="layui-form-item">
	<h2 class="layui-input-block"> 好友投资获得积分：<span class="gray">不获得积分请填写0</span></h2>
</div>
  <div class="layui-form-item">
    <label class="layui-form-label">推荐人：</label>
    <div class="layui-input-inline">
      <input type="text" name="friendsInvestGiveIntegral" value="${item.friendsInvestGiveIntegral }" required lay-verify="required" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">积分/千元   注：不足1千元视为 0元</div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">投资获得积分：</label>
    <div class="layui-input-inline">
      <input type="text" name="investGiveIntegral" value="${item.investGiveIntegral }" required lay-verify="required" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">积分/千元   注：不足1千元视为 0元</div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">用户投资大于过往单次投资金额时：</label>
    <div class="layui-input-inline">
      <input type="text" name="maxInvestGiveIntegral" value="${item.maxInvestGiveIntegral }" required lay-verify="required" autocomplete="off" class="layui-input">
    </div>
    <div class="layui-form-mid layui-word-aux">积分/千元   注：不足1千元视为 0元</div>
  </div>
  <h4>计算公式：本次投资金额 - 过往投资金额 = 计算积分的金额（取整）</h4>
 </div>
   <div class="layui-form-item">
    	<div class="layui-input-inline">
    		<button class="layui-btn" id="save">保存</button>
    	</div>
    </div>
  <script>
  	layui.use(['form','jquery','layer'],function(){
  		var form = layui.form;
  		var $ = layui.jquery;
  		var layer = layui.layer;
  		
  		var checked = '${item.isUseIntegral==1 ? "checked":"" }';
  		if(checked){
  			$("#contentdiv").css("display","block");
  		}
  		
  		form.on('checkbox(isUseIntegral)',function(data){
  			if(data.elem.checked){
  				$("#contentdiv").css("display","block");
  			}else{
  				$("#contentdiv").css("display","none");
  			}
  		});
  		
  		$("#save").click(function(){
  			$.post("${webRoot}/market/integralsave",{
  				"isUseIntegral":$("input[name='isUseIntegral']").prop("checked")?1:0,
  				"registerGiveIntegral":$("input[name='registerGiveIntegral']").val(),
  				"loginGiveIntegral":$("input[name='loginGiveIntegral']").val(),
  				"recommendFriendsGiveIntegral":$("input[name='recommendFriendsGiveIntegral']").val(),
  				"friendsInvestGiveIntegral":$("input[name='friendsInvestGiveIntegral']").val(),
  				"investGiveIntegral":$("input[name='investGiveIntegral']").val(),
  				"maxInvestGiveIntegral":$("input[name='maxInvestGiveIntegral']").val()
  			},function(data){
  				if(data.code==200){
  					layer.open({
  						title:"提示",content:"保存成功"
  					});
  				}else{
  					layer.open({
  						title:"提示",content:"保存失败"
  					});
  				}
  			},"json");
  		});
  	});
  </script>
</body>
</html>