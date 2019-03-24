package org.background.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.business.system.PermissionService;
import org.business.system.UserService;
import org.domain.system.Application;
import org.domain.system.Menu;
import org.domain.system.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 自定义
 * @author yshin1992
 * 域，Shiro 从从 Realm 获取安全数据（如用户、角色、权限），就是说 SecurityManager 要验证用户身份，
 * 那么它需要从 Realm 获取相应的用户进行比较以确定用户身份是否合法；
 * 也需要从 Realm 得到用户相应的角色 / 权限进行验证用户是否能进行操作；可以把 Realm 看成 DataSource，即安全数据源。
 */
@Component
public class CustomerRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(CustomerRealm.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	public CustomerRealm(){
		super();
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("MD5");
		matcher.setHashIterations(2);
		matcher.setStoredCredentialsHexEncoded(true);
		setCredentialsMatcher(matcher);
		setCachingEnabled(true);
		setAuthenticationCachingEnabled(true);
		setAuthenticationCacheName("authenticationCache");
		setAuthorizationCachingEnabled(true);
		setAuthorizationCacheName("authorizationCache");
	}
	/**
	 * 获取授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		
		return new SimpleAuthorizationInfo();
	}

	/**
	 * 获取身份验证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
	
		String username = (String)token.getPrincipal();
		if(StringUtils.isEmpty(username)){
			throw new UnknownAccountException("未知的账户!");
		}
		
		User user = userService.findByCd(username);
		if(ObjectUtils.isEmpty(user)){
			throw new UnknownAccountException("未知的账户!");
		}
		
		Subject curUser = SecurityUtils.getSubject();
		System.out.println(curUser.getSession().getAttribute("curUser"));
		curUser.getSession().setAttribute("curUser", user);
		
		List<Application> apps = permissionService.findAppByUser(user.getUserCd(), user.getIsAdmin()==1);
		logger.error("用户拥有的App权限 : {}",apps);
		curUser.getSession().setAttribute("apps", apps);
		curUser.getSession().setAttribute("curApp",apps.get(0));
		List<Menu> menus = permissionService.findMenuByUser(apps.get(0).getAppCd(), user.getUserCd(), null, user.getIsAdmin()==1);
		curUser.getSession().setAttribute("menus", menus);
		logger.error("用户拥有的菜单权限: {}",menus);
		logger.error("生成认证信息...");
		return new SimpleAuthenticationInfo(username,user.getPassword(),ByteSource.Util.bytes(user.getUserCd()),getName());
	}

}
