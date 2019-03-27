package org.background.controller.product;

import java.util.List;

import org.annotation.FunctionEx;
import org.annotation.MenuEx;
import org.apache.commons.lang3.StringUtils;
import org.business.product.ItemTypeService;
import org.business.util.CacheUtil;
import org.domain.product.ItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.vo.DropDownDto;

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
	public String templateList(PageRequest request,ItemType itemType){
		return "product/itemtype_list";
	}
	
	/**
	 * 费用设置列表数据
	 * @param request
	 * @param itemType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/itemtypelist/data")
	public PageResponse<ItemType> queryItemTypeList(PageRequest request,ItemType itemType){
		logger.error("数据请求：{},{}",request,itemType);
		return itemTypeService.queryAll(request, itemType);
	}
	
	@FunctionEx(code = "p2p.product.itemtype.init", name = "新增费用设置", parentCd = "p2p.product.itemtype.list")
	@RequestMapping(value="/itemtypeedit",method=RequestMethod.GET)
	public ModelAndView itemTypeEdit(String itemTypeId){
		ModelAndView mv = new ModelAndView("product/itemtype_edit");
		if(!StringUtils.isEmpty(itemTypeId)){
			ItemType itemType = itemTypeService.queryById(itemTypeId);
			mv.addObject("type",itemType);
		}
		List<DropDownDto> feeNodes = CacheUtil.getConfigList("feesetting.feenode");
		List<DropDownDto> referenceNodes = CacheUtil.getConfigList("feesetting.feereference");
		List<DropDownDto> feeType = CacheUtil.getConfigList("feeTypeType");
		mv.addObject("feeNodes",feeNodes);
		mv.addObject("referenceNodes",referenceNodes);
		mv.addObject("feeType",feeType);
		return mv;
	}
	
	@FunctionEx(code = "p2p.product.itemtype.save", name = "保存费用设置", parentCd = "p2p.product.itemtype.list")
	@RequestMapping(value="/itemtypesave")
	public String itemTypeSave(ItemType itemType){
		itemTypeService.saveOrUpdate(itemType);
		return "product/itemtype_list";
	}
	
	@FunctionEx(code = "p2p.product.itemtype.delete", name = "删除费用设置", parentCd = "p2p.product.itemtype.list")
	@RequestMapping(value="/itemtypedelete")
	public String itemTypeDelete(String[] ids){
		itemTypeService.deleteItemTypes(ids);
		return "product/itemtype_list";
	}
}
