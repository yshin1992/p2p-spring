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
	<script src="${webRoot}/static/upload/plupload.full.min.js"></script>
<style>
body {
	padding: 10px;
}
</style>
</head>
<body>
<mgr:navs navigations="${navs }"/>
	<div class="layui-form">
		<div class="layui-form-item">
			<div class="layui-inline">
				<label class="layui-form-label">创建时间</label>
				<div class="layui-input-inline">
					<input type="text" name="queryStart" id="queryStart" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
				</div>
				<div class="layui-form-mid">-</div>
				<div class="layui-input-inline">
					<input type="text" name="queryEnd" id="queryEnd" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
				</div>

				<label class="layui-form-label">搜索</label>
				<div class="layui-input-inline">
					<input type="text" name="keywords" placeholder="合同名称" autocomplete="off" class="layui-input">
				</div>
				<button class="layui-btn" id="query">查询</button>
				<input type="reset" class="layui-btn layui-btn-primary" value="重置" />
			</div>
		</div>
		<div class="layui-form">
			<mgr:btn resources="${resources }"/>
		</div>
	</div>

	<table class="layui-hide" id="content"></table>
	<script type="text/javascript">
		layui.use([ 'form', 'jquery', 'table','layer','laydate' ], function() {
			var form = layui.form;
			var $ = layui.jquery;
			var laydate = layui.laydate;
			//常规用法
			laydate.render({
			    elem: '#queryStart'
			});
			laydate.render({
				elem: '#queryEnd'
			});
			var table = layui.table;

			table.render({
				id:'dataTable',
				elem : '#content',
				url : '${webRoot}/contractList/data',
				cols : [ [ {
					type : 'checkbox'
				}, {
					field : 'templateName',
					title : '模板名称'
				},  {
					field : 'createBy',
					title : '创建人'
				}, {
					field : 'createTimeStr',
					title : '创建时间'
				} ] ],
				page : true
			});
			
			$("#query").click(function(){
				table.reload('dataTable',{
					where:{
						"keywords":$('input[name="keywords"]').val(),
						"queryStart":$('input[name="queryStart"]').val(),
						"queryEnd":$('input[name="queryEnd"]').val()
					},page:{
						page:1
					}
				});
			})
			
			$("#preview").click(function() {
				var checkStatus = table.checkStatus('dataTable'); 
				var datas = checkStatus.data;
				if(datas.length!=1){
					layer.open({
						title: '提示',content: '只能选择一项进行编辑'
					});   
				}else{
					var templateId = datas[0].templateId;
					$.post('${webRoot}/contractTemplate/path',{'templateId':templateId},function(data){
						console.log(data);
						if(data.code==200){
							layer.open({
								type: 2,
								title: '预览',
								shadeClose: true,
								shade: 0.8,
								area: ['800px', '90%'],
								content: data.data
							});
						}else{
							layer.open(
									{title:'提示',content:data.msg}
							);
						}

					},'json');
				}
			});
			
			$("#delete").click(function(){
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
					$.get('${webRoot}/contractTemplate/delete?ids='+ids,function(data){
						if(data.code==200){
							layer.open({title:'提示',content:'删除成功'});
							table.reload('dataTable',{
								where:{
									"keywords":$('input[name="keywords"]').val(),
									"queryStart":$('input[name="queryStart"]').val(),
									"queryEnd":$('input[name="queryEnd"]').val()
								},page:{
									page:1
								}
							});
						}else{
							layer.open({title:'提示',content:data.msg});
						}

					},'json');
				}
			});



			var $input_file = $('#upload');

			/**
			 * @description 上传模板
			 */
					// 实例化一个plupload上传对象
			var uploader = new plupload.Uploader({
						runtimes: 'html5,flash,silverlight,html4',
						max_file_size: '90000K',
						browse_button: 'upload',
						url: '${webRoot}/contractTemplate/upload',
						flash_swf_url: '${webRoot}/static/upload/Moxie.swf',
						silverlight_xap_url: '${webRoot}/static/upload/Moxie.xap',
						multi_selection: false,
						filters: {
							mime_types: [
								{title: "Doc files", extensions: "doc,docx"}
							],
							prevent_duplicates: false
						}
					});
			uploader.init(); //初始化

			uploader.bind('FilesAdded', function (uploader, files) {
				uploader.start(); //开始上传
				$input_file.val('上传中');
				$input_file[0].disabled = true;
			});

			uploader.bind('FileUploaded', function (up, file, info) {
				var data = JSON.parse(info.response);
				if (data.code==200) {
					location.href = '${webRoot}/contractList';
				} else {
					$input_file.val('上传文档');
					$input_file[0].disabled = false;
					layer.open({title:"提示",content:"上传失败"});
				}
			});
		});
	</script>
</body>
</html>