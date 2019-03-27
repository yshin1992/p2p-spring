package org.background.controller.product;

import org.annotation.FunctionEx;
import org.annotation.MenuEx;
import org.business.product.ItemTemplateService;
import org.domain.product.ItemTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pagination.PageRequest;
import pagination.PageResponse;

@Controller
public class ItemTemplateController {

	@Autowired
	private ItemTemplateService itemTemplateService;
	
	@MenuEx(code="p2p.product.itemtemplate.list",name="分润模板",parentCd="p2p.system.profile",listSort = 20)
	@RequestMapping(value="/itemTemplateList",method=RequestMethod.GET)
	public String itemTemplateList(){
		return "product/itemtemplate_list";
	}
	
	@ResponseBody
	@RequestMapping("/itemTemplateList/data")
	public PageResponse<ItemTemplate> queryByPage(PageRequest request,ItemTemplate template){
		return itemTemplateService.queryByPage(request, template);
	}
	
	@FunctionEx(code="p2p.product.itemtemplate.init",name="新增分润模板",parentCd="p2p.product.itemtemplate.list")
	@RequestMapping("/itemTemplateEdit")
	public String itemTemplateEdit(ItemTemplate template){
		itemTemplateService.saveOrUpdate(template);
		return "product/itemtemplate_list";
	}
	
	
}
