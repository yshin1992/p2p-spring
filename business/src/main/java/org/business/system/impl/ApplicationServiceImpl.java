package org.business.system.impl;

import javax.transaction.Transactional;

import org.business.system.ApplicationService;
import org.dao.hibernate.system.ApplicationDao;
import org.domain.system.Application;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("applicationService")
public class ApplicationServiceImpl implements ApplicationService {

	
	@Autowired
	private ApplicationDao applicationDao;
	
	@Transactional
	@Override
	public void saveOrUpdate(Application app) {
		Application appFind = applicationDao.findByAppCd(app.getAppCd());
		if(appFind != null){
			BeanUtils.copyProperties(app, appFind,new String[] { "appId", "id","effTime","createTime"});
			applicationDao.update(appFind);
		}else{
			applicationDao.save(app);
		}
	}

}
