package org.dao.hibernate.product;

import org.domain.product.ItemType;

import pagination.PageRequest;
import pagination.PageResponse;

public interface ItemTypeDao {

	public void saveOrUpdate(ItemType itemType);
	
	public PageResponse<ItemType> queryAll(PageRequest pageRequest,ItemType itemType);
	
	public ItemType queryById(String id);
	
	public String getCategoryMaxCd(String feeType);
	
	public void deleteById(String id);
}
