<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="static/layui-v2.4.5/layui/layui.js"></script>
<link rel="text/css" href="static/layui-v2.4.5/layui/css/layui.css">
<style>
body{
	padding:10px;
}
</style>
</head>
<body>
<form class="layui-form" action="${webRoot }/itemTemplateSave" method="post">
	<input type="hidden" name="templateId" value="${itemTemplate.templateId }"/>
	<div class="layui-form-item">
	    <label class="layui-form-label">模板名称</label>
	    <div class="layui-input-block">
	      <input type="text" name="templateNm" required  lay-verify="required" placeholder="模板名称" autocomplete="off" class="layui-input">
	    </div>
	</div>
	<div class="layui-form-item">
	    <label class="layui-form-label">费用项</label>
	    <div class="layui-input-block">
	      <input type="text" name="typeNms" id="typeNms" required  lay-verify="required" placeholder="" autocomplete="off" class="layui-input">
	    </div>
	</div>
	
	<div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="edit">保存</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>

<div class="layui-form layui-form-pane" style="display: none" id="search">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">搜索</label>
				<div class="layui-input-inline">
					<input type="text" name="itemTypeNm" autocomplete="off"
						class="layui-input" placeholder="费用名称">
				</div>
				<button class="layui-btn" lay-submit="" lay-filter="typelist" id="query">查询</button>
			</div>
		</div>
	</div>

<script>
	layui.use(['form','jquery','layer'],function(){
		var form = layui.form;
		var $ = layui.jquery;
		var layer = layui.layer;
		$("#typeNms").click(function(){
			layer.open({
				  type: 1,
				  content: $('#search') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
				});
		});
	});
</script>

</body>
</html>