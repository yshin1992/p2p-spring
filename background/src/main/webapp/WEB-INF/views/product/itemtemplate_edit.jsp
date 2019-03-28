<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="static/layui-v2.4.5/layui/layui.js"></script>
<link rel="stylesheet" href="static/layui-v2.4.5/layui/css/layui.css">
<style>
body{
	padding:10px;
}
</style>
</head>
<body>
<form class="layui-form" action="${webRoot }/itemTemplateSave" method="post">
	<input type="hidden" name="templateId" value="${itemTemplate.templateId }"/>
	<input type="hidden" name="itemIds" value="${itemTemplate.itemTypesId }">
	<div class="layui-form-item">
	    <label class="layui-form-label">模板名称</label>
	    <div class="layui-input-block">
	      <input type="text" name="templateNm" required value="${itemTemplate.templateNm }"  lay-verify="required" placeholder="模板名称" autocomplete="off" class="layui-input">
	    </div>
	</div>
	<div class="layui-form-item">
	    <label class="layui-form-label">费用项</label>
	    <div class="layui-input-block">
	      <input type="text" id="typeNms" required  lay-verify="required" value="${itemTemplate.itemTypesNm }" placeholder="" autocomplete="off" class="layui-input">
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
			<div class="layui-inline">
				<button class="layui-btn" id="select">添加选中</button>
			</div>
		</div>
		<table class="layui-hide" id="content"></table>
</div>
	
<script>
	layui.use(['form','jquery','layer','table'],function(){
		var form = layui.form;
		var $ = layui.jquery;
		var layer = layui.layer;
		var table = layui.table;
		$("#typeNms").click(function(){
			var tab=table.render({
				id:'dataTable',
				elem : '#content',
				url : '${webRoot}/itemtypelist/data',
				cols : [ [ {
					type : 'checkbox'
				}, {
					field : 'itemTypeNm',
					title : '费用项目名称'
				}, {
					field : 'billerName',
					title : '付款方'
				}, {
					field : 'chargerName',
					title : '收款方'
				} ] ],
				page : true
			});
			layer.open({
				  type: 1,
				  area:['800px', '600px'],
				  content: $('#search') //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
				});
		});
		
		$("#query").click(function(){
			table.reload('dataTable',{
				where:{
					"itemTypeNm":$('input[name="itemTypeNm"]').val()
				},page:{
					page:1
				}
			});
		});
		
		$("#select").click(function(){
			var checkStatus = table.checkStatus('dataTable'); 
			var datas = checkStatus.data;
			if(datas.length<1){
				layer.open({
					title: '提示',content: '至少选择一项'
				});   
			}else{
				var ids = "";
				var names="";
				for(var i=0;i<datas.length;i++){
					ids+=datas[i].id;
					names+=datas[i].itemTypeNm;
					if(i<datas.length-1){
						ids+=",";
						names+=",";
					}
				}
				
				$("input[name='itemIds']").attr("value",ids);
				$("#typeNms").attr("value",names);
				
				layer.closeAll();
			}
		});
		
		form.on('submit(edit)',function(data){
			return true;
		})
	});
</script>

</body>
</html>