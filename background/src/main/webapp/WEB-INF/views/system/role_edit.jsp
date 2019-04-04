<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="tags/manager"  prefix="mgr" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="${webRoot }/static/layui-v2.4.5/layui/css/layui.css"  rel="stylesheet" type="text/css">
<link href="${webRoot }/static/zTree_v3/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${webRoot }/static/layui-v2.4.5/layui/layui.js"></script>
<script type="text/javascript" src="${webRoot }/static/zTree_v3/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${webRoot }/static/zTree_v3/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${webRoot }/static/zTree_v3/js/jquery.ztree.excheck.js"></script>
<style>
body {
	padding: 10px;
}
</style>
</head>
<body>
<mgr:navs navigations="${navs }"/>
<form class="layui-form" action="${webRoot }/system/roleSave" method="post">
<input type="hidden" name="roleId" value="${role.roleId }"/>
<input type="hidden" name="ids" />
	<div class="layui-form-item">
      <label class="layui-form-label">*角色编码</label>
      <div class="layui-input-inline">
        <input type="text" name="roleCd" value="${role.roleCd }" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    
    <div class="layui-form-item">
      <label class="layui-form-label">*角色名称</label>
      <div class="layui-input-inline">
        <input type="text" name="roleNm" value="${role.roleNm }" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    
    <div class="layui-form-item">
      <label class="layui-form-label">*角色权限</label>
      <div class="layui-input-inline">
        <ul id="treeDemo" class="ztree"></ul>
      </div>
    </div>
    
    <div class="layui-form-item">
    	<div class="layui-input-inline">
    		<button class="layui-btn" lay-submit="" lay-filter="save">保存</button>
    	</div>
    </div>
</form>
<script>
var treeObj;
layui.use([ 'form', 'jquery','layer' ], function() {
	var form = layui.form;
	var $ = layui.jquery;
	form.on('submit(save)',function(data){
		console.log(data.field);
		var selectedNodes = treeObj.getCheckedNodes();
		var ids="";
		selectedNodes.map(function(item,index){
			ids+=item.id;
			if(index<selectedNodes.length-1){
				ids+=",";
			}
		});
		console.log(ids);
		$("input[name='ids']").attr("value",ids);
		return true;
	});
});
$(document).ready(function(){
	var setting = {
			check: { enable: true },
			data: { simpleData: { enable: true } }
		};
	var roleId = $("input[name='roleId']").val();
	$.post('${webRoot}/system/role/permissions',{'roleId':roleId},function(data){
		treeObj = $.fn.zTree.init($("#treeDemo"), setting, data);
	},'json');
});
</script>
</body>
</html>