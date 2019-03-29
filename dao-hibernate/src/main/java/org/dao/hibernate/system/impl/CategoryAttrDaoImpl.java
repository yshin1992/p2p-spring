package org.dao.hibernate.system.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dao.hibernate.AbstractDaoImpl;
import org.dao.hibernate.system.CategoryAttrDao;
import org.domain.system.CategoryAttr;
import org.springframework.stereotype.Repository;

@Repository("categoryAttrDao")
public class CategoryAttrDaoImpl extends AbstractDaoImpl<CategoryAttr> implements CategoryAttrDao {

	@Override
	public List<CategoryAttr> findByCategoryCd(String categoryCd) {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("categoryCd", categoryCd);
		String HQL = "from CategoryAttr attr where attr.category.categoryCd=:categoryCd";
		return queryList(HQL, condition);
	}

	@Override
	public CategoryAttr findByAttrCd(String attrCd) {
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("attrCd", attrCd);
		String HQL = "from CategoryAttr where attrCd=:attrCd";
		return findSingleResultByHQL(HQL, condition);
	}

}
