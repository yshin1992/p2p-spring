package org.ysh.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.ysh.domain.Users;
import org.ysh.service.UsersService;

public class CostomRealm extends AuthenticatingRealm {

	private UsersService usersService;

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	/**
	 * 返回认证信息（数据库中的信息，即正确的信息）
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = (String)token.getPrincipal();
		if(StringUtils.isEmpty(username)){
			throw new UnknownAccountException("未知的账户!");
		}
		
		Users user = usersService.findUserByName(username);
		if(ObjectUtils.isEmpty(user)){
			throw new UnknownAccountException("未知的账户!");
		}
		
		Subject curUser = SecurityUtils.getSubject();
		System.out.println(curUser.getSession().getAttribute("curUser"));
		curUser.getSession().setAttribute("curUser", user);
		
		return new SimpleAuthenticationInfo(username,user.getPassword(),ByteSource.Util.bytes(user.getPasswordSalt()),getName());
	}


}
