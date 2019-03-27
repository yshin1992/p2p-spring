package org.business.product;

import org.domain.product.ItemType;

import pagination.PageRequest;
import pagination.PageResponse;

public interface ItemTypeService {
	
	public void saveOrUpdate(ItemType itemType);
	
	public PageResponse<ItemType> queryAll(PageRequest request,ItemType itemType);
	
	public ItemType queryById(String itemTypeId);
	
	public void deleteItemTypes(String... ids);
}
