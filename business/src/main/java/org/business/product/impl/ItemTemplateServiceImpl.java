package org.business.product.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.business.AbstractServiceImpl;
import org.business.product.ItemTemplateService;
import org.domain.product.ItemTemplate;
import org.springframework.stereotype.Service;

import pagination.PageRequest;
import pagination.PageResponse;

@Service("itemTemplateService")
public class ItemTemplateServiceImpl extends AbstractServiceImpl<ItemTemplate> implements
		ItemTemplateService {

	@Override
	public PageResponse<ItemTemplate> queryByPage(PageRequest request,
			ItemTemplate template) {
		StringBuilder HQL=new StringBuilder("from ItemTemplate where 1=1 ");
		Map<String,Object> condition = new HashMap<String,Object>();
		if(null != template){
			if(StringUtils.isNotEmpty(template.getTemplateNm())){
				HQL.append(" and templateNm=:templateNm");
				condition.put("templateNm", template.getTemplateNm());
			}
		}
		return this.queryPageByHQL(HQL.toString(), condition, request);
	}
}
