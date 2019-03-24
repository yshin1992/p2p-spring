package org.dao.hibernate.system;

import java.util.List;

import org.domain.system.Application;

public interface ApplicationDao {
	
	public void save(Application app);
	
	public void update(Application app);
	
	public Application findByAppCd(String appCd);
	
	public List<Application> findByUser(String userCd);
	
	public List<Application> findAll();
}
