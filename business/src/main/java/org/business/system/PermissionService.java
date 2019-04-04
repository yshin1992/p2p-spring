package org.business.system;

import java.util.List;

import org.domain.system.Application;
import org.domain.system.Menu;
import org.domain.system.Resource;

public interface PermissionService {

	/**
	 * 根据用户cd查找用户所拥有的菜单权限
	 * @param appCd
	 * @param userCd
	 * @param resourcePcd
	 * @param isAdmin
	 * @return
	 */
	public List<Menu> findMenuByUser(String appCd,String userCd,String resourcePcd,boolean isAdmin);
	
	/**
	 * 根据用户编码查找所拥有的应用程序的权限
	 * @param userCd
	 * @param isAdmin
	 * @return
	 */
	public List<Application> findAppByUser(String userCd,boolean isAdmin);
	
	/**
	 * 根据用户编码查询用户拥有的权限编码
	 * @param userCd
	 * @return
	 */
	List<String> queryPermissionsByUser(String userCd);


	/**
	 * 根据用户编码查询用户所拥有的全部资源
	 * @param userCd
	 * @return
	 */
	List<Resource> queryResourcesByUser(String userCd);
	
}
