package org.background.controller.product;

import java.util.List;

import org.annotation.FunctionEx;
import org.annotation.MenuEx;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.business.contract.ContractTemplateService;
import org.business.product.ItemTemplateService;
import org.business.product.ProductService;
import org.business.util.CacheUtil;
import org.domain.product.ItemType;
import org.domain.product.Product;
import org.enums.ProductEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.util.StringUtil;
import org.vo.DropDownDto;
import org.vo.product.ProductDto;

import pagination.PageRequest;
import pagination.PageResponse;

@Controller
public class ProductController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductService productService;

    @Autowired
    private ContractTemplateService contractTemplateService;
    
    @Autowired
    private ItemTemplateService itemTemplateService;
    
    /**
     * 项目申请
     */
    @RequiresPermissions("p2p.product.list")
    @MenuEx(code = "p2p.product.list", name = "项目申请", parentCd = "p2p.product.manager", listSort = 100)
    @RequestMapping("/product-application")
    public String productList(){
        logger.error("查询产品列表..");

        return "product/product-application";
    }

    @RequiresPermissions("p2p.product.list")
    @RequestMapping("/product-application/data")
    @ResponseBody
    public PageResponse<Product> productList(ProductDto dto, PageRequest request){
        logger.error("查询产品列表:{}",dto);
        try{
            return productService.queryByPage(dto,request,null);
        }catch (Exception e){
            logger.error("查询产品列表异常,{}",e);
        }
        return new PageResponse<>(request);
    }
    
    @RequiresPermissions("p2p.product.itemtemplate.add")
    @FunctionEx(code="p2p.product.itemtemplate.add",name="新建项目",parentCd="p2p.product.list")
    @RequestMapping(value="/productCreate")
    public String productCreate(String status,ModelMap model){
    	//还款方式
    	List<DropDownDto> repayMethods = CacheUtil.getConfigList(ProductEnum.REPAY_METHOD.getCode());
    	model.addAttribute("repayMethods",repayMethods);
    	//期限类型
    	List<DropDownDto> periodTypes = CacheUtil.getConfigList(ProductEnum.PERIOD_TYPE.getCode());
    	model.addAttribute("periodTypes",periodTypes);
    	//借款用途
    	List<DropDownDto> borrowUses = CacheUtil.getConfigList(ProductEnum.BRROW_USE.getCode());
    	model.addAttribute("borrowUses",borrowUses);
    	//业务类型
    	List<DropDownDto> businessTypes = CacheUtil.getConfigList(ProductEnum.BUSINESS_TYPE.getCode());
    	model.addAttribute("businessTypes",businessTypes);
    	//标种类型
    	List<DropDownDto> tendKinds = CacheUtil.getConfigList(ProductEnum.TENDER_KIND.getCode());
    	model.addAttribute("tendKinds",tendKinds);
    	
    	//合同模板
    	List<DropDownDto> templates = contractTemplateService.queryAll();
    	model.addAttribute("templates",templates);
    	
    	// 获取分润模板
    	List<DropDownDto> itemTemplates = itemTemplateService.findAll();
    	model.addAttribute("itemTemplates",itemTemplates);
    	
    	return "product/product_edit";
    }
    
    @RequiresPermissions("p2p.product.itemtemplate.add")
    @RequestMapping("/getItemType")
    public ModelAndView getItemType(String productId,String id){
    	ModelMap model = new ModelMap();
    	
    	List<DropDownDto> rateReferened = CacheUtil.getConfigList(ProductEnum.RATE_REFERENED.getCode());
		model.put("rateReferened", rateReferened);
		
		List<DropDownDto> periodOrDays = CacheUtil.getConfigList(ProductEnum.PERIOD_OR_DAY.getCode());
		model.put("periodOrDay", periodOrDays);
    	// 新建时默认加载的模板
		if(StringUtil.isNotEmpty(id)){
			List<ItemType> list = itemTemplateService.findItemTypesById(id);
			model.put("listType", list);
		}
		// 修改时加载该项目对应的模板
		//TODO
		return new ModelAndView("product/product_itemtype",model);
    }
    

}
