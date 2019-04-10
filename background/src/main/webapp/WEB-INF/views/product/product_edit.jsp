<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="tags/manager"  prefix="mgr" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link href="static/layui-v2.4.5/layui/css/layui.css"  rel="stylesheet" type="text/css">
<script type="text/javascript" src="static/layui-v2.4.5/layui/layui.js"></script>
<style type="text/css">
body{
	padding:10px;
}
</style>
</head>
<body>
<mgr:navs navigations="${navs }"/>
<form class="layui-form" action="${webRoot }/productSave" method="post">
<div class="layui-tab">
  <ul class="layui-tab-title">
    <li class="layui-this">基本信息</li>
    <li>费用信息</li>
    <li>借款方信息</li>
    <li>保障措施</li>
    <li>质押物信息</li>
    <li>认证信息</li>
    <li>图片资料</li>
  </ul>
  <div class="layui-tab-content">
    <div class="layui-tab-item layui-show">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
            <legend>基础信息</legend>
        </fieldset>
        <div class="layui-inline">
            <label class="layui-form-label">*产品单价</label>
            <div class="layui-input-inline">
                <input type="text" name="unitPrice" value="${product.unitPrice }" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">*产品数量</label>
            <div class="layui-input-inline">
                <input type="text" name="product.quantity" value="${product.quantity }" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">*借款金额</label>
            <div class="layui-input-inline">
                <input type="text" name="product.amount" value="${product.amount }" readonly="readonly" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">*还款方式</label>
            <div class="layui-input-inline">
                <select name="product.repayMethods">
                    <c:forEach items="${repayMethods }" var="rm" varStatus="stat">
                        <option value="${rm.attrCd }" ${product.repayMethod==rm.attrCd?"selected":"" }>${rm.attrNm }</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">*借款期限</label>
            <div class="layui-input-inline">
                <input type="text" name="product.period" value="${product.period }" lay-verify="required" autocomplete="off" class="layui-input">
                <select name="product.periodType">
                    <c:forEach items="${periodTypes }" var="node" varStatus="stat">
                        <option value="${node.attrCd }" ${product.periodType==node.attrCd?"selected":"" }>${node.attrNm }</option>
                    </c:forEach>
                </select>
            </div>

        </div>

        <div class="layui-inline">
            <label class="layui-form-label">*年利率</label>
            <div class="layui-input-inline">
                <input type="text" name="product.rate" value="${product.rate }" lay-verify="required" autocomplete="off" class="layui-input">
            </div><div class="layui-form-mid layui-word-aux">%</div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">*最小投资数量</label>
            <div class="layui-input-inline">
                <input type="text" name="product.minTenderQuantity" value="${product.minTenderQuantity }" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">最大投资数量</label>
            <div class="layui-input-inline">
                <input type="text" name="product.maxTenderQuantity" value="${product.maxTenderQuantity }" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">最小满标数量</label>
            <div class="layui-input-inline">
                <input type="text" name="product.minFullQuantity" value="${product.minFullQuantity }" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">*合同编号</label>
            <div class="layui-input-inline">
                <input type="text" name="agreementCd" value="${agreementCd }" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">*合同模板</label>
            <div class="layui-input-inline">
                <select name="contractTemplateId">
                    <c:forEach items="${templates }" var="rm" varStatus="stat">
                        <option value="${rm.attrCd }" ${product.repayMethod==rm.attrCd?"selected":"" }>${rm.attrNm }</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">*借款用途</label>
            <div class="layui-input-inline">
                <select name="product.brrowUse">
                    <c:forEach items="${borrowUses }" var="rm" varStatus="stat">
                        <option value="${rm.attrCd }" ${product.brrowUse==rm.attrCd?"selected":"" }>${rm.attrNm }</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">*业务类型</label>
            <div class="layui-input-inline">
                <select name="product.businessType">
                    <c:forEach items="${businessTypes }" var="rm" varStatus="stat">
                        <option value="${rm.attrCd }" ${product.businessType==rm.attrCd?"selected":"" }>${rm.attrNm }</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">*标种类型</label>
            <div class="layui-input-inline">
                <select name="product.tenderKind">
                    <c:forEach items="${tendKinds }" var="rm" varStatus="stat">
                        <option value="${rm.attrCd }" ${product.tenderKind==rm.attrCd?"selected":"" }>${rm.attrNm }</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
            <legend>项目介绍</legend>
        </fieldset>

        <div class="layui-form-item">
            <label class="layui-form-label">*标题</label>
            <div class="layui-input-block">
                <input type="text" name="product.productNm" lay-verify="title" autocomplete="off" placeholder="请输入标题" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">*项目简介</label>
            <div class="layui-input-block">
                <textarea name="product.productContent" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">*资金用途</label>
            <div class="layui-input-block">
                <textarea name="product.fundUse" placeholder="请输入内容" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">还款来源</label>
            <div class="layui-input-block">
                <textarea name="product.repaySource" class="layui-textarea"></textarea>
            </div>
        </div>
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
            <legend>其他信息</legend>
        </fieldset>
        <div class="layui-inline">
            <label class="layui-form-label">*申请上线时间</label>
            <div class="layui-input-inline">
                <input type="text" name="product.groundTime" id="groundTime" lay-verify="date" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">*申请投标开始</label>
            <div class="layui-input-inline">
                <input type="text" name="product.tenderStart" id="tenderStart" lay-verify="date" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-inline">
            <label class="layui-form-label">*申请投标结束</label>
            <div class="layui-input-inline">
                <input type="text" name="product.tenderEnd" id="tenderEnd" lay-verify="date" placeholder="yyyy-MM-dd HH:mm:ss" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">首页推荐</label>
            <div class="layui-input-inline">
                <input type="radio" name="homePager" value="0" title="否" />
                <input type="radio" name="homePager" value="1" title="是" />
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">奖励比例</label>
            <div class="layui-input-inline">
                <input type="text" name="product.awardRate" value="${product.awardRate }" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">%</div>
        </div>

    </div>
    <div class="layui-tab-item">
		<div class="layui-inline">
            <label class="layui-form-label">*分润模板</label>
            <div class="layui-input-inline">
                <select name="templateId" lay-filter="frmb">
                    <c:forEach items="${itemTemplates }" var="rm" varStatus="stat">
                        <option value="${rm.attrCd }" >${rm.attrNm }</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div id="itemTypes"></div>
	</div>
    <div class="layui-tab-item">内容3</div>
    <div class="layui-tab-item">内容4</div>
    <div class="layui-tab-item">内容5</div>
    <div class="layui-tab-item">内容6</div>
    <div class="layui-tab-item">内容7</div>
  </div>
</div>
</form>
<script type="text/javascript">
layui.use(['form','jquery','laydate','element'],function(){
	var form = layui.form;
	var $ = layui.jquery;
	var laydate = layui.laydate;
    var element = layui.element;
	laydate.render({ 
		  elem: '#groundTime'
		  ,type: 'datetime'
		});
	laydate.render({ 
		  elem: '#tenderStart'
		  ,type: 'datetime'
		});
	
	laydate.render({ 
		  elem: '#tenderEnd'
		  ,type: 'datetime'
		});
	form.on('submit(edit)',function(data){
		console.log(data.field);
		$("#periodOrDay").val($("#periodOrDayCB").prop("checked")? 1 : 0);
		return true;
	});
	
	form.on('select(frmb)',function(data){
		console.log(data.value);
		$.ajax({
			async:true,type:'get',url:'${webRoot}/getItemType?id='+data.value,dataType:'html',success:function(data){
				document.getElementById("itemTypes").innerHTML = data;
			},error:function(data){alert("错误消息:"+data);}
		});
	});
});
</script>
</body>
</html>