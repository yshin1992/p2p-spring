package org.business.system;

import org.business.AbstractService;
import org.domain.system.User;

public interface SysUserService extends AbstractService<User> {

	public void save(User user, String ids);
	
	public void disable(String[] ids);
	
	public void enable(String[] ids);
}
