<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link  rel="stylesheet" href="static/layui-v2.4.5/layui/css/layui.css" type="text/css">
<script type="text/javascript"  src="static/layui-v2.4.5/layui/layui.js"></script>
<style>
	body{
		padding:10px;
	}
</style>
</head>
<body>
<form class="layui-form layui-form-pane" action="${webRoot }/itemtypelist" method="post">
<div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">搜索</label>
      <div class="layui-input-inline">
        <input type="text" name="itemTypeNm" autocomplete="off" class="layui-input" placeholder="费用名称">
      </div>
      <div class="layui-input-inline">
	       <button class="layui-btn" lay-submit="" lay-filter="typelist">查询</button>
	       <button type="reset" class="layui-btn layui-btn-primary">重置</button>
       </div>
       <div class="layui-input-inline">
       		<button class="layui-btn" >新增</button>
       		<button class="layui-btn" >删除</button>
       </div>
      
    </div>
  </div>
  
  <table class="layui-hide" id="content"></table>
</form>
<script type="text/javascript">
layui.use(['form','jquery','table'],function(){
	var form = layui.form;
	var $ = layui.jquery;
	form.on('submit(typelist)',function(data){
		console.log(data.field);
		return true;
	});
	
	var table = layui.table;
	  
	  table.render({
	    elem: '#content'
	    ,url:'/demo/table/user/'
	    ,cols: [[
	      {type:'checkbox'}
	      ,{field:'id', width:80, title: 'ID', sort: true}
	      ,{field:'username', width:80, title: '用户名'}
	      ,{field:'sex', width:80, title: '性别', sort: true}
	      ,{field:'city', width:80, title: '城市'}
	      ,{field:'sign', title: '签名', minWidth: 100}
	      ,{field:'experience', width:80, title: '积分', sort: true}
	      ,{field:'score', width:80, title: '评分', sort: true}
	      ,{field:'classify', width:80, title: '职业'}
	      ,{field:'wealth', width:135, title: '财富', sort: true}
	    ]]
	    ,page: true
	  });
});
</script>
</body>
</html>