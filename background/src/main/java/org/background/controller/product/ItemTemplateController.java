package org.background.controller.product;

import java.util.List;

import org.annotation.FunctionEx;
import org.annotation.MenuEx;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.business.product.ItemTemplateService;
import org.business.product.ItemTypeService;
import org.domain.product.ItemTemplate;
import org.domain.product.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pagination.PageRequest;
import pagination.PageResponse;

@Controller
public class ItemTemplateController {

	@Autowired
	private ItemTemplateService itemTemplateService;
	
	@Autowired
	private ItemTypeService itemTypeService;
	
	@RequiresPermissions(value="p2p.product.itemtemplate.list")
	@MenuEx(code="p2p.product.itemtemplate.list",name="分润模板",parentCd="p2p.system.profile",listSort = 20)
	@RequestMapping(value="/itemTemplateList",method=RequestMethod.GET)
	public String itemTemplateList(){
		return "product/itemtemplate_list";
	}
	
	@RequiresPermissions(value="p2p.product.itemtemplate.list")
	@ResponseBody
	@RequestMapping("/itemTemplateList/data")
	public PageResponse<ItemTemplate> queryByPage(PageRequest request,ItemTemplate template){
		return itemTemplateService.queryByPage(request, template);
	}
	
	@RequiresPermissions(value="p2p.product.itemtemplate.init")
	@FunctionEx(code="p2p.product.itemtemplate.init",name="新增分润模板",parentCd="p2p.product.itemtemplate.list")
	@RequestMapping("/itemTemplateEdit")
	public String itemTemplateEdit(String templateId,ModelMap model){
		if(StringUtils.isNotEmpty(templateId)){
			ItemTemplate itemTemplate = itemTemplateService.queryById(templateId);
			model.addAttribute("itemTemplate", itemTemplate);
			model.addAttribute("itemTypesId", itemTemplate.getItemTypesId());
		}
		return "product/itemtemplate_edit";
	}
	
	@RequiresPermissions(value="p2p.product.itemtemplate.save")
	@FunctionEx(code="p2p.product.itemtemplate.save",name="保存分润模板",parentCd="p2p.product.itemtemplate.list")
	@RequestMapping("/itemTemplateSave")
	public String itemTemplateSave(ItemTemplate template,String itemIds){
		if(StringUtils.isEmpty(template.getTemplateId())){
			//防止id为 ""空字符串时，保存时出现异常
			template.setTemplateId(null);
		}
		if(StringUtils.isNotEmpty(itemIds)){
			String[] ids = itemIds.indexOf(",")==-1?new String[]{itemIds}:itemIds.split(",");
			List<ItemType> itemTypes = itemTypeService.queryByIds(ids);
			template.setItemTypes(itemTypes);
		}
		
		itemTemplateService.saveOrUpdate(template);
		return "product/itemtemplate_list";
	}
	
	@RequiresPermissions(value="p2p.product.itemtemplate.delete")
	@FunctionEx(code="p2p.product.itemtemplate.delete",name="删除分润模板",parentCd="p2p.product.itemtemplate.list")
	@RequestMapping("/itemTemplateDelete")
	public String itemTemplateDelete(String[] templateIds){
		if(templateIds.length>0){
			for(String templateId:templateIds){
				itemTemplateService.delete(templateId);
			}
		}
		return "product/itemtemplate_list";
	}
	
	
}
