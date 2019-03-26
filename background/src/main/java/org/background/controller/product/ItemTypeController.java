package org.background.controller.product;

import org.annotation.MenuEx;
import org.business.product.ItemTypeService;
import org.domain.product.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pagination.PageRequest;
import pagination.PageResponse;

@Controller
public class ItemTypeController {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemTypeController.class);
	
	@Autowired
	private ItemTypeService itemTypeService;
	
	/**
	 * 费用设置列表
	 * @param request
	 * @param itemType
	 * @return
	 */
	@MenuEx(code = "p2p.product.itemtype.list",name = "费用设置", parentCd = "p2p.system.profile", listSort = 10)
	@RequestMapping("/itemtypelist")
	public ModelAndView templateList(PageRequest request,ItemType itemType){
		ModelAndView mv = new ModelAndView();
		logger.info("查询费用设置列表:{}", request);
		PageResponse<ItemType> pager = itemTypeService.queryAll(request, itemType);
		mv.addObject("pager",pager);
		mv.addObject("itemType",itemType);
		mv.setViewName("product/itemtype_list");
		return mv;
	}
}
