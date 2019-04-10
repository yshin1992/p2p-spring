<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="tags/manager"  prefix="mgr" %>
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
<mgr:navs navigations="${navs }"/>
<div class="layui-tab" lay-filter="demo">
	<ul class="layui-tab-title">
		<li class="layui-this">待处理项目</li>
		<li>已处理项目</li>
	</ul>
	<div class="layui-tab-content">
		<div class="layui-tab-item layui-show">
			<div class="layui-form layui-form-pane">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">创建日期</label>
						<div class="layui-input-inline">
							<input type="text" id="startTime0" autocomplete="off"
								   class="layui-input" placeholder="">
						</div>

						<label class="layui-form-label">至</label>
						<div class="layui-input-inline">
							<input type="text" id="endTime0" autocomplete="off"
								   class="layui-input" placeholder="">
						</div>

						<label class="layui-form-label">搜索</label>
						<div class="layui-input-inline" style="width:400px">
							<input type="text" id="applyKeywords0" autocomplete="off"
								   class="layui-input" style="width:400px" placeholder="项目编号、标题、借款人、借款企业、担保机构">
						</div>
						<button class="layui-btn" lay-submit="" lay-filter="typelist" id="query0">查询</button>
					</div>
				</div>
			</div>
			<mgr:btn resources="${resources }"/>
			<table class="layui-hide" id="content0"></table>

		</div>
		<div class="layui-tab-item">
			<div class="layui-form layui-form-pane">
				<div class="layui-form-item">
					<div class="layui-inline">
						<label class="layui-form-label">上线日期</label>
						<div class="layui-input-inline">
							<input type="text" id="startTime1" autocomplete="off"
								   class="layui-input" placeholder="">
						</div>

						<label class="layui-form-label">至</label>
						<div class="layui-input-inline">
							<input type="text" id="endTime1" autocomplete="off"
								   class="layui-input" placeholder="">
						</div>

						<label class="layui-form-label">搜索</label>
						<div class="layui-input-inline" style="width:400px">
							<input type="text" id="applyKeywords1" autocomplete="off"
								   class="layui-input" style="width:400px" placeholder="项目编号、标题、借款人、借款企业、担保机构">
						</div>
						<button class="layui-btn" lay-submit="" lay-filter="typelist" id="query1">查询</button>
					</div>
				</div>
			</div>
			<table class="layui-hide" id="content1"></table>
		</div>
	</div>
</div>


	<script type="text/javascript">
		layui.use([ 'form', 'jquery', 'table','layer','element' ], function() {
			var form = layui.form;
			var $ = layui.jquery;
			var element = layui.element;
			form.on('submit(typelist)', function(data) {
				console.log(data.field);
				return true;
			});

			var table = layui.table;

			table.render({
				id:'dataTable',
				elem : '#content0',
				url : '${webRoot}/product-application/data?status=1',
				cols : [ [ {
					type : 'checkbox'
				}, {
					field : 'productCd',
					title : '项目编号'
				}, {
					field : 'productNm',
					title : '标题'
				},  {
					field : 'member.realNm',
					title : '借款人'
				}, {
					field : 'member.enterprise.companyNm',
					title : '借款企业',
				}, {
					field : 'safeguard.companyNm',
					title : '担保机构'
				} , {
					field : 'amount',
					title : '借款金额(元)'
				} , {
					field : 'rateP',
					title : '年利率(%)'
				} , {
					field : 'periodDesc',
					title : '借款期限'
				} , {
					field : 'repayMethodName',
					title : '还款方式'
				} , {
					field : 'tenderKindName',
					title : '标种名称'
				} , {
					field : 'createTimeStr',
					title : '创建时间'
				} , {
					field : 'statusName',
					title : '状态'
				} ] ],
				page : true
			});

			table.render({
				id:'dataTable',
				elem : '#content1',
				url : '${webRoot}/product-application/data?status=2',
				cols : [ [ {
					type : 'checkbox'
				}, {
					field : 'productCd',
					title : '项目编号'
				}, {
					field : 'productNm',
					title : '标题'
				},  {
					field : 'member.realNm',
					title : '借款人'
				}, {
					field : 'member.enterprise.companyNm',
					title : '借款企业',
				}, {
					field : 'safeguard.companyNm',
					title : '担保机构'
				} , {
					field : 'amount',
					title : '借款金额(元)'
				} , {
					field : 'rateP',
					title : '年利率(%)'
				} , {
					field : 'periodDesc',
					title : '借款期限'
				} , {
					field : 'repayMethodName',
					title : '还款方式'
				} , {
					field : 'tenderKindName',
					title : '标种名称'
				} , {
					field : 'groundTimeStr',
					title : '上线时间'
				} , {
					field : 'statusName',
					title : '状态'
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

			$("#add").click(function(){
				$(location).attr("href","${webRoot}/productCreate");
			});

			$("#edit").click(function() {
				var checkStatus = table.checkStatus('dataTable'); 
				var datas = checkStatus.data;
				if(datas.length!=1){
					layer.open({
						title: '提示',content: '只能选择一项进行编辑'
					});   
				}else{
					var templateId = datas[0].templateId;
					$(location).attr('href', '${webRoot}/itemTemplateEdit?templateId='+templateId);
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