package org.business.product.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.business.AbstractServiceImpl;
import org.business.product.ItemTemplateService;
import org.dao.hibernate.product.ItemTypeDao;
import org.domain.product.ItemTemplate;
import org.domain.product.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vo.DropDownDto;

import pagination.PageRequest;
import pagination.PageResponse;

@Service("itemTemplateService")
public class ItemTemplateServiceImpl extends AbstractServiceImpl<ItemTemplate> implements
		ItemTemplateService {

	@Autowired
	private ItemTypeDao itemTypeDao;
	
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

	@Override
	public List<ItemType> findItemTypesById(String id) {
		return itemTypeDao.findByTemplateId(id);
	}

	@Override
	public List<DropDownDto> findAll() {
		List<ItemTemplate> list = this.queryList("from ItemTemplate where 1=1");
		List<DropDownDto> dtos = new ArrayList<DropDownDto>();
		for(ItemTemplate template : list){
			dtos.add(new DropDownDto(template.getId(), template.getTemplateNm()));
		}
		return dtos;
	}
}
