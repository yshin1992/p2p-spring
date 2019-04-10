package org.business.contract.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.business.AbstractServiceImpl;
import org.business.contract.ContractTemplateService;
import org.dao.hibernate.contract.ContractTemplateDao;
import org.domain.contract.ContractTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.vo.DropDownDto;

import pagination.PageRequest;
import pagination.PageResponse;

@Service(value = "contractTemplateService")
public class ContractTemplateServiceImpl extends AbstractServiceImpl<ContractTemplate> implements ContractTemplateService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private ContractTemplateDao contractTemplateDao;

    @Override
    public PageResponse<ContractTemplate> queryByPage(PageRequest pageRequest, Date queryStart, Date queryEnd, String keywords) {
        try {
			return contractTemplateDao.queryByPage(pageRequest, queryStart, queryEnd, keywords);
		} catch (Exception e) {
			logger.error("查询合同模板出现异常!{}",e);
		}
        return new PageResponse<ContractTemplate>(pageRequest);
    }

    @Transactional
    @Override
    public void deleteTemplates(String... ids) {
        if(!ObjectUtils.isEmpty(ids)){
            for(String id:ids){
            	if(StringUtils.hasText(id)){
            		ContractTemplate template = contractTemplateDao.queryById(id);
                    template.disable();
                    contractTemplateDao.saveOrUpdate(template);
            	}
            }
        }
    }

	@Override
	public List<DropDownDto> queryAll() {
		List<ContractTemplate> list = contractTemplateDao.queryList("from ContractTemplate where state='F0A'");
		List<DropDownDto> resList = new ArrayList<DropDownDto>();
		if(list!=null && !list.isEmpty()){
			for(ContractTemplate template : list){
				DropDownDto dto = new DropDownDto(template.getId(),template.getTemplateName());
				resList.add(dto);
			}
		}
		return resList;
	}
}
