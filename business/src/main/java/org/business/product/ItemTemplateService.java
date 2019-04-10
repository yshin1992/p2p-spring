package org.business.product;

import java.util.List;

import org.business.AbstractService;
import org.domain.product.ItemTemplate;
import org.domain.product.ItemType;
import org.vo.DropDownDto;

import pagination.PageRequest;
import pagination.PageResponse;

public interface ItemTemplateService extends AbstractService<ItemTemplate> {

	/**
	 * 分页查询
	 * @param request
	 * @param template
	 * @return
	 */
	PageResponse<ItemTemplate> queryByPage(PageRequest request,ItemTemplate template);
	
	/**
	 * 模板下对应的费用(根据模板id)
	 * @param id
	 * @return
	 */
	List<ItemType> findItemTypesById(String id);
	
	/**
	 * 查询所有的分润模板并封装到DropDownDto中
	 * @return
	 */
	List<DropDownDto> findAll();
}
