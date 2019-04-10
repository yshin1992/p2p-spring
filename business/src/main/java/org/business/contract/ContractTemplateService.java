package org.business.contract;

import org.business.AbstractService;
import org.domain.contract.ContractTemplate;
import org.vo.DropDownDto;

import pagination.PageRequest;
import pagination.PageResponse;

import java.util.Date;
import java.util.List;

public interface ContractTemplateService extends AbstractService<ContractTemplate> {
    PageResponse<ContractTemplate> queryByPage(PageRequest pageRequest, Date queryStart, Date queryEnd, String keywords);

    void deleteTemplates(String... ids);
    
    List<DropDownDto> queryAll();
}
