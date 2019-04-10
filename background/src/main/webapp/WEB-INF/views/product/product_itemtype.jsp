<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div>
<c:forEach items="${listType}" var="p" varStatus="s">
		<input type="hidden" name="productItemType[${s.index}].biller">
		<input type="hidden" name="productItemType[${s.index}].charger">
		<input type="hidden" name="productItemType[${s.index}].node">
		
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
            <legend>${p.itemTypeNm}</legend>
        </fieldset>
        <div class="layui-inline">付款方:${p.billerName}</div>
        <div class="layui-inline">收款方:${p.chargerName}</div>
        <div class="layui-inline">费用节点:${p.nodeName}</div>
        <c:if test="${p.edited==1}">
        	<div class="layui-form-item">
			    <label class="layui-form-label">扣费方式:</label>
			    <div class="layui-input-inline">
			      <select name="productItemType[${s.index}].rateReferened" style="display:inline-block">
                    <c:forEach items="${rateReferened }" var="rm" varStatus="stat">
                        <option value="${rm.attrCd }" ${p.rateReferened==rm.attrCd?"selected":"" }>${rm.attrNm }</option>
                    </c:forEach>
                	</select>
			    </div>
			  </div>
			  
			  <div class="layui-form-item">
			    <label class="layui-form-label">X</label>
			    <div class="layui-input-inline">
			      <input type="text" name="productItemType[${s.index}].rate" value="${p.rateP}" autocomplete="off" class="layui-input">
			    </div>
			    <div class="layui-form-mid layui-word-aux">% X 天数/期数</div>
			  </div>
			  
			  <div class="layui-form-item">
			    <label class="layui-form-label">上限</label>
			    <div class="layui-input-inline">
			      <input type="text" name="productItemType[${s.index}].maxAmount" value="<fmt:formatNumber value='${p.maxAmount}' type='currency' pattern='###0.00'/>" autocomplete="off" class="layui-input">
			    </div>
			    <div class="layui-form-mid layui-word-aux">元</div>
			  </div>
        </c:if>
        <c:if test="${p.edited==0}">
			<input type="hidden" name="productItemType[${s.index}].edited">
			<input type="hidden" name="productItemType[${s.index}].rateReferened">
			<input type="hidden" name="productItemType[${s.index}].rate">
			<div class="layui-inline">扣费方式:${p.billerName}×${p.rateP} %</div>
		</c:if>
</c:forEach>
</div>