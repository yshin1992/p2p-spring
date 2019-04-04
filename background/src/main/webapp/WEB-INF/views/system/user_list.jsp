<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="tags/manager"  prefix="mgr" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${webRoot }/static/layui-v2.4.5/layui/css/layui.css"  rel="stylesheet" type="text/css">
<script type="text/javascript" src="${webRoot }/static/layui-v2.4.5/layui/layui.js"></script>
<style>
	body{
		padding:10px;
	}
</style>
</head>
<body>
<mgr:navs navigations="${navs }"/>
<div class="layui-form layui-form-pane">
<div class="layui-inline">
		<a class="layui-btn" href="${webRoot}/system/useredit">新增</a>
		<button class="layui-btn" id="edit">编辑</button>
		<button class="layui-btn layui-btn-primary" id="enable">启用</button>
		<button class="layui-btn layui-btn-primary" id="disable">禁用</button>
	</div>
</div>
	<table class="layui-hide" id="content"></table>
	<script type="text/javascript">
		layui.use([ 'form', 'jquery', 'table','layer' ], function() {
			var form = layui.form;
			var $ = layui.jquery;
			var table = layui.table;
			var layer = layui.layer;
			table.render({
				id:'dataTable',
				elem : '#content',
				url : '${webRoot}/system/userlist/data',
				cols : [ [ {
					type : 'checkbox'
				}, {
					field : 'userCd',
					title : '用户账号'
				}, {
					field : 'userNm',
					title : '用户名称'
				}, {
					field : 'stateName',
					title : '状态'
				} ] ],
				page : true
			});
			
			$("#query").click(function(){
				table.reload('dataTable',{
					where:{
					},page:{
						page:1
					}
				});
			})
			
			$("#edit").click(function(){
				var checkStatus = table.checkStatus('dataTable'); 
				var datas = checkStatus.data;
				if(datas.length!=1){
					layer.open({
						title: '提示',content: '只能选择一项进行编辑'
					});   
				}else{
					var id = datas[0].id;
					$(location).attr("href","${webRoot}/system/useredit?userId="+id)
				}
			});
			$("#enable").click(function() {
				var checkStatus = table.checkStatus('dataTable'); 
				var datas = checkStatus.data;
				if(datas.length==0){
					layer.open({
						title: '提示',content: '至少选择一项数据进行启用'
					});   
				}else{
					layer.open({
						title: '提示',content: '确定要启用所选角色么?',
						yes:function(index,layero){
							var ids = "";
							datas.map(function(data,index){
								ids+=data.id;
								if(index<datas.length-1)
									ids+=",";
							});
							$.post("${webRoot}/system/userenable",{"ids":ids},function(data){
								if(data.code == 200){
									layer.open({
										title:"提示",content:"启用角色成功"
									});
									table.reload('dataTable',{
										where:{
										},page:{
											page:1
										}
									});
								}
							},"json");
							layer.close(index);
						}
					});
					
				}
			});
			
			$("#disable").click(function(){
				var datas = table.checkStatus('dataTable').data;
				if(datas.length==0){
					layer.open({
						title:"提示",content:"至少选择一项数据"
					});
				}else{
					layer.open({
						title: '提示',content: '确定要禁用所选角色么?',
						yes:function(index,layero){
							var ids = "";
							datas.map(function(data,index){
								ids+=data.id;
								if(index<datas.length-1)
									ids+=",";
							});
							$.post("${webRoot}/system/userdisable",{"ids":ids},function(data){
								if(data.code == 200){
									layer.open({
										title:"提示",content:"禁用角色成功"
									});
									table.reload('dataTable',{
										where:{},page:{page:1}
									});
								}
							},"json");
							layer.close(index);
						}
					});
				}
			});
		});
	</script>
</body>
</html>