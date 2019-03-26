package org.dao.hibernate.product;

import org.domain.product.ItemType;

import pagination.PageRequest;
import pagination.PageResponse;

public interface ItemTypeDao {

	public void save(ItemType itemType);
	
	public PageResponse<ItemType> queryAll(PageRequest pageRequest,ItemType itemType);
	
}
