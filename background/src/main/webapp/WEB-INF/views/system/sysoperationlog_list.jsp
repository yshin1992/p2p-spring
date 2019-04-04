<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="tags/manager"  prefix="mgr" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
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
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">操作对象名</label>
				<div class="layui-input-inline">
					<input type="text" name="operateObjName" autocomplete="off"
						class="layui-input" placeholder="">
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label">操作人员</label>
				<div class="layui-input-inline">
					<input type="text" name="operatorAccount" autocomplete="off"
						class="layui-input" placeholder="">
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label">操作类型</label>
				<div class="layui-input-inline">
					<select id="operateTypeSelect">
						<option value="0">全部类型</option>
						<option value="1">新增</option>
						<option value="2">删除</option>
						<option value="3">编辑</option>
						<option value="4">导出</option>
					</select>
				</div>
			</div>
			
			<div class="layui-inline">
				<label class="layui-form-label">操作对象</label>
				<div class="layui-input-inline">
					<select id="operateObjSelect">
						<option value="0">全部对象</option>
						<option value="1">项目数据</option>
						<option value="2">会员数据</option>
						<option value="3">订单数据</option>
						<option value="4">企业客户</option>
						<option value="9">其他</option>
					</select>
				</div>
			</div>
			
		</div>
		<div class="layui-form-item">
			<div class="layui-inline">
				<input type="text" class="layui-input" placeholder="查询开始时间" id="startTime">
			</div>
			<div class="layui-inline">
			 	<input type="text" class="layui-input" placeholder="查询结束时间" id="endTime">
			</div>
			
			<button class="layui-btn" lay-submit="" lay-filter="typelist" id="query">查询</button>
		</div>
	</div>
	<table class="layui-hide" id="content"></table>
	<script type="text/javascript">
		layui.use([ 'form', 'jquery', 'table','layer','laydate' ], function() {
			var form = layui.form;
			var $ = layui.jquery;
			
			var laydate = layui.laydate;
			laydate.render({ 
				  elem: '#startTime',
				  type:'datetime'
				  ,format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
			});
			
			laydate.render({ 
				  elem: '#endTime',
				  type:'datetime'
				  ,format: 'yyyy-MM-dd HH:mm:ss' //可任意组合
			});
			var table = layui.table;
			table.render({
				id:'dataTable',
				elem : '#content',
				url : '${webRoot}/system/sysOperationLogList/data',
				cols : [ [ {
					field : 'operateObjType',
					title : '操作对象类型'
				}, {
					field : 'operateObjName',
					title : '操作对象名'
				}, {
					field : 'operateType',
					title : '操作类型'
				}, {
					field : 'operateContent',
					title : '操作内容'
				}, {
					field : 'operatorAccount',
					title : '操作人员'
				}, {
					field : 'operateTimeStr',
					title : '操作时间'
				} , {
					field : 'remark',
					title : '备注'
				} ] ],
				page : true
			});
			
			$("#query").click(function(){
				table.reload('dataTable',{
					where:{
						"sysOperationLog.operateObjName":$('input[name="operateObjName"]').val(),
						"sysOperationLog.operatorAccount":$('input[name="operatorAccount"]').val(),
						"sysOperationLog.operateType":$("#operateTypeSelect").val(),
						"sysOperationLog.operateObjType":$("#operateObjSelect").val(),
						"queryStartTime":$("#startTime").val(),
						"queryEndTime":$("#endTime").val()
					},page:{
						page:1
					}
				});
			});
			
		});
	</script>
</body>
</html>