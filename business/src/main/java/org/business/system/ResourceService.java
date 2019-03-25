package org.business.system;

import java.util.List;

import org.domain.system.Application;
import org.domain.system.Resource;

public interface ResourceService {

	public void save(List<Resource> resources,Application app);
	
	public Resource findResourceByCd(String resourceCd);
}
