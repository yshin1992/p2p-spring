package org.dao.hibernate.contract;

import org.dao.hibernate.AbstractDao;
import org.domain.contract.ContractTemplate;

import pagination.PageRequest;
import pagination.PageResponse;

import java.util.Date;

public interface ContractTemplateDao extends AbstractDao<ContractTemplate> {

    PageResponse<ContractTemplate> queryByPage(PageRequest pageRequest, Date queryStart,Date queryEnd,String keywords) throws Exception;
}
