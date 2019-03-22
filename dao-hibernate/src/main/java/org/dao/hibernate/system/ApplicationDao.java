package org.dao.hibernate.system;

import org.domain.system.Application;

public interface ApplicationDao {
	
	public void save(Application app);
	
	public void update(Application app);
	
	public Application findByAppCd(String appCd);
	
}
