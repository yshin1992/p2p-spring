package org.dao.hibernate.contract.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.dao.hibernate.AbstractDaoImpl;
import org.dao.hibernate.contract.ContractTemplateDao;
import org.domain.contract.ContractTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import pagination.PageRequest;
import pagination.PageResponse;

@Repository(value = "contractTemplateDao")
public class ContractTemplateDaoImpl extends AbstractDaoImpl<ContractTemplate> implements ContractTemplateDao {
    @Override
    public PageResponse<ContractTemplate> queryByPage(PageRequest pageRequest, Date queryStart, Date queryEnd, String keywords) throws Exception {
        StringBuilder hql = new StringBuilder("from  ContractTemplate where state='F0A' ");
        Map<String,Object> condition = new HashMap<>();
        if(StringUtils.isNotEmpty(keywords)){
            hql.append(" and templateName like :templateName");
            condition.put("templateName","%" + keywords + "%");
        }

        if(!ObjectUtils.isEmpty(queryStart)){
            hql.append(" and createTime >= :queryStart");
            String startTime = DateFormatUtils.format(queryStart, "yyyy-MM-dd").concat(" 00:00:00");
            condition.put("queryStart",DateUtils.parseDate(startTime, "yyyy-MM-dd HH:mm:ss"));
        }

        if(!ObjectUtils.isEmpty(queryEnd)){
            hql.append(" and createTime <= :queryEnd");
            String endTime = DateFormatUtils.format(queryEnd,"yyyy-MM-dd").concat(" 23:59:59");
            condition.put("queryEnd",DateUtils.parseDate(endTime, "yyyy-MM-dd HH:mm:ss"));
        }

        return this.queryPageByHQL(hql.toString(),condition,pageRequest);
    }
}
