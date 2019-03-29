package org.dao.hibernate.system;

import java.util.List;

import org.dao.hibernate.AbstractDao;
import org.domain.system.CategoryAttr;

public interface CategoryAttrDao extends AbstractDao<CategoryAttr>{
	/**
	 * 根据CategoryCd查询其下所有的categoryAttr
	 * @param categoryCd
	 * @return
	 */
	List<CategoryAttr> findByCategoryCd(String categoryCd);
	
	/**
	 * 根据attrCd查询出categoryAttr信息
	 * @param attrCd
	 * @return
	 */
	CategoryAttr findByAttrCd(String attrCd);
}
