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
<form class="layui-form" action="${webRoot }/itemtypesave" method="post">
		<input type="hidden" name="itemTypeId" id="hidItemTypeId" value="${type.itemTypeId }"/>
    	<input type="hidden" name="itemTypeCd" id="itemTypeCd" value="${type.itemTypeCd }"/>
    	<input type="hidden" name="periodOrDay" id="periodOrDay" value="${type.periodOrDay }"/>
	<div class="layui-inline">
      <label class="layui-form-label">*费用名称</label>
      <div class="layui-input-inline">
        <input type="text" name="itemTypeNm" value="${type.itemTypeNm }" lay-verify="required" autocomplete="off" class="layui-input">
      </div>
    </div>
    
  <div class="layui-form-item">
    <label class="layui-form-label">*付款方</label>
    <div class="layui-input-block">
       <input type="radio" name="biller" value="1" title="融资人" ${type.biller=="1"?"checked":"" }/>
       <input type="radio" name="biller" value="2" title="投资人" ${type.biller=="2"?"checked":"" }/>
       <input type="radio" name="biller" value="11" title="平台" ${type.biller=="11"?"checked":"" }/>
       <input type="radio" name="biller" value="12" title="担保方" ${type.biller=="12"?"checked":"" }/>
       <input type="radio" name="biller" value="3" title="转让人" ${type.biller=="3"?"checked":"" }/>
       <input type="radio" name="biller" value="4" title="受让人" ${type.biller=="4"?"checked":"" }/>
       <input type="radio" name="biller" value="13" title="三方支付公司" ${type.biller=="13"?"checked":"" }/>
       <input type="radio" name="biller" value="15" title="渠道商" ${type.biller=="15"?"checked":"" }/>
    </div>
  </div>
  
  <div class="layui-form-item">
    <label class="layui-form-label">*收款方</label>
    <div class="layui-input-block">
       <input type="radio" name="charger" value="1" title="融资人" ${type.charger=="1"?"checked":"" }/>
       <input type="radio" name="charger" value="2" title="投资人" ${type.charger=="2"?"checked":"" }/>
       <input type="radio" name="charger" value="11" title="平台" ${type.charger=="11"?"checked":"" }/>
       <input type="radio" name="charger" value="12" title="担保方" ${type.charger=="12"?"checked":"" }/>
       <input type="radio" name="charger" value="3" title="转让人" ${type.charger=="3"?"checked":"" }/>
       <input type="radio" name="charger" value="4" title="受让人" ${type.charger=="4"?"checked":"" }/>
       <input type="radio" name="charger" value="13" title="三方支付公司" ${type.charger=="13"?"checked":"" }/>
       <input type="radio" name="charger" value="15" title="渠道商" ${type.charger=="15"?"checked":"" }/>
    </div>
  </div>
  
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">*费用分类</label>
      <div class="layui-input-inline">
        <select name="feeType">
          <c:forEach items="${feeType }" var="ft" varStatus="stat">
          	<option value="${ft.attrCd }" ${type.feeType==ft.attrCd?"selected":"" }>${ft.attrNm }</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">*费用节点</label>
      <div class="layui-input-inline">
        <select name="node">
          <c:forEach items="${feeNodes }" var="node" varStatus="stat">
          	<option value="${node.attrCd }" ${type.node==node.attrCd?"selected":"" }>${node.attrNm }</option>
          </c:forEach>
        </select>
      </div>
    </div>
   </div>
  	
  	<div class="layui-inline">
      <label class="layui-form-label">*扣费方式</label>
      <div class="layui-input-inline">
        <input type="radio" name="edited" value="0" title="固定" ${type.edited=="0"?"checked":"" }/>
       	<input type="radio" name="edited" value="1" title="按项目" ${type.edited=="1"?"checked":"" }/>
      </div>
    </div>
    <div class="layui-form-item" style="padding-left:40px">
    	选择“期数/天数”，可根据借款期数（天标按天）来扣费；“逾期还款”节点，天数代表逾期天数；上限不填则上不设限
    </div>
  
  <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">费率参考</label>
      <div class="layui-input-inline">
        <select name="rateReferened">
          <c:forEach items="${referenceNodes }" var="rn" varStatus="stat">
          	<option value="${rn.attrCd }" ${type.rateReferened==rn.attrCd?"selected":"" } >${rn.attrNm }</option>
          </c:forEach>
        </select>
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">x 费率(%)</label>
      <div class="layui-input-inline">
		<input type="text" name="rate" value="${type.rate }" class="layui-input"/>
      </div>
      <label class="layui-form-label">x </label>
      <div class="layui-input-inline">
      	<input type="checkbox" name="periodOrDayCB" id="periodOrDayCB" ${type.periodOrDay=="1"?"checked":"" } lay-skin="primary" value="${type.periodOrDay }" title="期数/天数">
      </div>
    </div>
   </div>
   
   <div class="layui-form-item">
    <div class="layui-inline">
      <label class="layui-form-label">上限(元)</label>
      <div class="layui-input-inline">
        <input type="text" name="maxAmount" value='<fmt:formatNumber type="currency" value="${type.maxAmount }" pattern="###0.00"/>' class="layui-input"/>
      </div>
    </div>
    <div class="layui-inline">
      <label class="layui-form-label">下限(元)</label>
      <div class="layui-input-inline">
		<input type="text" name="minAmount" value='<fmt:formatNumber type="currency" value="${type.minAmount }" pattern="###0.00"/>' class="layui-input"/>
      </div>
      <div class="layui-input-inline">
      <input type="checkbox" name="calOnlineFlag" ${type.calOnlineFlag=="1"?"checked":"" } lay-skin="primary" value="${type.calOnlineFlag }" title="是否参与线上运算">
      </div>
    </div>
   </div>
   
   <div class="layui-form-item">
    <div class="layui-input-block">
      <button class="layui-btn" lay-submit="" lay-filter="edit">保存</button>
      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
  </div>
</form>

<script type="text/javascript">
layui.use(['form','jquery'],function(){
	var form = layui.form;
	var $ = layui.jquery;
	form.on('submit(edit)',function(data){
		console.log(data.field);
		$("#periodOrDay").val($("#periodOrDayCB").prop("checked")? 1 : 0);
		return true;
	});
});
</script>
</body>
</html>