package org.business.product;

import org.domain.product.ItemType;

import pagination.PageRequest;
import pagination.PageResponse;

public interface ItemTypeService {
	
	public void save(ItemType itemType);
	
	public PageResponse<ItemType> queryAll(PageRequest request,ItemType itemType);
	
}
