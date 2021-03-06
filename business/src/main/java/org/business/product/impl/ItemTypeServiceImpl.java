package org.business.product.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
	
	@Transactional
	@Override
	public void saveOrUpdate(ItemType itemType) {
		itemTypeDao.saveOrUpdate(itemType);
	}

	@Override
	public PageResponse<ItemType> queryAll(PageRequest request,
			ItemType itemType) {
		return itemTypeDao.queryAll(request, itemType);
	}

	@Override
	public ItemType queryById(String itemTypeId) {
		return itemTypeDao.queryById(itemTypeId);
	}

	@Transactional
	@Override
	public void deleteItemTypes(String... ids) {
		if(ids != null && ids.length>0){
			for(String id : ids){
				itemTypeDao.deleteById(id);
			}
		}
	}

	@Override
	public List<ItemType> queryByIds(String[] ids) {
		if(null ==ids || ids.length ==0)
			return null;
		List<ItemType> items = new ArrayList<ItemType>();
		for(String id:ids){
			ItemType itemType = this.queryById(id);
			if(null == itemType){
				throw new RuntimeException("非法的ItemType [id:"+id+"]");
			}
			items.add(itemType);
		}
		return items;
	}

}
