package org.business.product;

import org.business.AbstractService;
import org.domain.product.ItemTemplate;

import pagination.PageRequest;
import pagination.PageResponse;

public interface ItemTemplateService extends AbstractService<ItemTemplate> {

	PageResponse<ItemTemplate> queryByPage(PageRequest request,ItemTemplate template);
	
}
