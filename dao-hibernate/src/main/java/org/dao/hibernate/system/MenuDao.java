package org.dao.hibernate.system;

import java.util.List;

import org.domain.system.Menu;

public interface MenuDao {

	/**
	 * 查询出应用程序下所有的菜单
	 * @param appCd 应用程序编码
	 * @param resourcePcd 资源父编码
	 * @return
	 */
	public List<Menu> queryAll(String appCd,String resourcePcd);
	
}
