package org.background.controller.contract;

import org.annotation.FunctionEx;
import org.annotation.MenuEx;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.StringUtils;
import org.background.support.CustomDateEditor;
import org.background.support.PDFUtil;
import org.background.support.Plupload;
import org.background.support.PluploadUtil;
import org.business.contract.ContractTemplateService;
import org.business.util.CacheUtil;
import org.domain.contract.ContractTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.vo.ResponseMsg;
import pagination.PageRequest;
import pagination.PageResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 合同Controller
 */
@Controller
public class ContractController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final String PATH_TEMPLATE_UPLOAD = "/Users/yanshuai/Documents/data/others/templates/";

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Date.class,new CustomDateEditor());
    }


    @Autowired
    private ContractTemplateService contractTemplateService;

    @RequiresPermissions("p2p.product.contract.manager")
    @MenuEx(code = "p2p.product.contract.manager",name="合同管理",parentCd="p2p.product.contract",listSort = 200)
    @RequestMapping(value = "/contractList",method = RequestMethod.GET)
    public String contractList(){
        return "contract/contract_list";
    }

    @ResponseBody
    @RequiresPermissions("p2p.product.contract.manager")
    @RequestMapping(value="/contractList/data")
    public PageResponse<ContractTemplate> queryByPage(PageRequest request, Date queryStart, Date queryEnd,String keywords){
        return  contractTemplateService.queryByPage(request,queryStart,queryEnd,keywords);
    }

    @RequiresPermissions("p2p.product.contract.preview")
    @FunctionEx(code="p2p.product.contract.preview",name = "预览",parentCd = "p2p.product.contract.manager")
    @ResponseBody
    @RequestMapping(value="/contractTemplate/path",method = RequestMethod.POST)
    public ResponseMsg<String> getContractTemplatePath(String templateId){
        logger.error("模板ID --> {}",templateId);
        ResponseMsg<String> msg = new ResponseMsg<>();
        if(StringUtils.hasText(templateId)){
            ContractTemplate template = contractTemplateService.queryById(templateId);
            if(null != template){
                msg.setData(template.getTemplatePdfPath());
                return msg;
            }
        }
        msg.failure("未找到模板文件");
        return msg;

    }


    @RequiresPermissions("p2p.product.contract.delete")
    @FunctionEx(code="p2p.product.contract.delete",name="删除",parentCd = "p2p.product.contract.manager")
    @RequestMapping(value="/contractTemplate/delete")
    @ResponseBody
    public ResponseMsg<String> deleteTemplate(String ids[]){
        ResponseMsg<String> msg = new ResponseMsg<>();
        try{
            contractTemplateService.deleteTemplates(ids);
        }catch (Exception e){
            msg.failure("删除模板异常");
            logger.error("删除模板异常",e);
        }
        return msg;

    }

    @RequiresPermissions("p2p.product.contract.upload")
    @FunctionEx(code="p2p.product.contract.upload",name="模板上传",parentCd = "p2p.product.contract.manager")
    @RequestMapping("/contractTemplate/upload")
    @ResponseBody
    public ResponseMsg<String> uploadTemplate(Plupload plupload, HttpServletRequest request){
        ResponseMsg<String> msg = new ResponseMsg<>();
        plupload.setRequest(request);
        //文件存储路径
        File dir = new File(PATH_TEMPLATE_UPLOAD);

        try{
            String fileName = PluploadUtil.upload(plupload,dir);

            ContractTemplate template = new ContractTemplate();
            template.init();
            //合同名称
            template.setTemplateName(plupload.getName().substring(0,plupload.getName().indexOf(".")));
            //合同路径
            template.setTemplatePath(PATH_TEMPLATE_UPLOAD + fileName);

            //生成PDF文档
            String rootPath = PluploadUtil.PDF_ROOT_PATH;

            File tmpFile = new File(rootPath);
            if(!tmpFile.exists()){
                tmpFile.mkdirs();
            }

            Map<String,Object> param = new HashMap<String,Object>();

            param.put("agreementCd","");
            param.put("lastRepayDateStr","");
            param.put("repayTotalAmt", "");

            String pdfName = fileName.substring(0,fileName.lastIndexOf(".")) + ".pdf";
            PDFUtil.getInstance().templateOfficeToPdf(template.getTemplatePath(), rootPath + pdfName , param);

            template.setTemplatePdfPath(CacheUtil.getProperty("agreement.root.url") + "tmp" + File.separator + pdfName);
            
            contractTemplateService.save(template);
        }catch (Exception  e){
            logger.error("生成合同异常{}",e);
            msg.failure("上传合同出现异常");

        }
        return msg;

    }

}
