<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="static/layui-v2.4.5/layui/css/layui.css"
	type="text/css">
<script type="text/javascript" src="static/layui-v2.4.5/layui/layui.js"></script>
<style>
body {
	padding: 10px;
}
</style>
</head>
<body>
	<div class="layui-form layui-form-pane">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">搜索</label>
				<div class="layui-input-inline">
					<input type="text" name="templateNm" autocomplete="off"
						class="layui-input" placeholder="模板名称">
				</div>
				<button class="layui-btn" lay-submit="" lay-filter="typelist" id="query">查询</button>
			</div>
		</div>
	</div>
	<div class="layui-inline">
		<a class="layui-btn" href="${webRoot}/itemtypeedit">新增</a>
		<button class="layui-btn" id="del">删除</button>
		<button class="layui-btn" id="edit">编辑</button>
	</div>
	<table class="layui-hide" id="content"></table>
	<script type="text/javascript">
		layui.use([ 'form', 'jquery', 'table','layer' ], function() {
			var form = layui.form;
			var $ = layui.jquery;
			form.on('submit(typelist)', function(data) {
				console.log(data.field);
				return true;
			});

			var table = layui.table;

			table.render({
				id:'dataTable',
				elem : '#content',
				url : '${webRoot}/itemTemplateList/data',
				cols : [ [ {
					type : 'checkbox'
				}, {
					field : 'templateNm',
					title : '模板名称'
				}, {
					field : 'itemTypesNm',
					title : '模板费用项'
				},  {
					field : 'createBy',
					title : '创建人'
				}, {
					field : 'createTimeStr',
					title : '创建时间',
				}, {
					field : 'defaultFlagStr',
					title : '是否默认'
				} ] ],
				page : true
			});
			
			$("#query").click(function(){
				table.reload('dataTable',{
					where:{
						"templateNm":$('input[name="templateNm"]').val()
					},page:{
						page:1
					}
				});
			})
			
			$("#edit").click(function() {
				var checkStatus = table.checkStatus('dataTable'); 
				var datas = checkStatus.data;
				if(datas.length!=1){
					layer.open({
						title: '提示',content: '只能选择一项进行编辑'
					});   
				}else{
					var itemTypeId = datas[0].itemTypeId;
					$(location).attr('href', '${webRoot}/itemtypeedit?itemTypeId='+itemTypeId);
				}
			});
			
			$("#del").click(function(){
				var datas = table.checkStatus('dataTable').data;
				if(datas.length==0){
					layer.open({
						title:"提示",content:"至少选择一项"
					});
				}else{
					var ids = new Array(datas.length);
					datas.map(function(data){
						ids.push(data.id);
					});
					$(location).attr("href","${webRoot}/itemtypedelete?ids="+ids);
				}
			});
		});
	</script>
</body>
</html>