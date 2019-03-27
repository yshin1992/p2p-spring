package org.dao.hibernate.product.impl;

import org.dao.hibernate.AbstractDaoImpl;
import org.dao.hibernate.product.ItemTemplateDao;
import org.domain.product.ItemTemplate;
import org.springframework.stereotype.Repository;

@Repository("itemTemplateDao")
public class ItemTemplateDaoImpl extends AbstractDaoImpl<ItemTemplate> implements ItemTemplateDao {

}
