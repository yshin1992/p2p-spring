package org.p2p.service;

import org.p2p.dao.SelectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceSupport {
	
	@Autowired
	private SelectDao selectDao;

	public SelectDao getSelectDao() {
		return selectDao;
	}
	
	
}
