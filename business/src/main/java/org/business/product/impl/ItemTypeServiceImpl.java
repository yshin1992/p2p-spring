package org.business.product.impl;

import org.business.product.ItemTypeService;
import org.dao.hibernate.product.ItemTypeDao;
import org.domain.product.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pagination.PageRequest;
import pagination.PageResponse;

@Service("itemTypeService")
public class ItemTypeServiceImpl implements ItemTypeService {
	
	@Autowired
	private ItemTypeDao itemTypeDao;
	
	@Override
	public void save(ItemType itemType) {
		itemTypeDao.save(itemType);
	}

	@Override
	public PageResponse<ItemType> queryAll(PageRequest request,
			ItemType itemType) {
		return itemTypeDao.queryAll(request, itemType);
	}

}
