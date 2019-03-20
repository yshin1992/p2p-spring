package org.domain.system;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.domain.StaticEntity;
import org.hibernate.annotations.GenericGenerator;
import org.util.StringUtil;

@Entity
@Table(name="sys_user")
public class User extends StaticEntity{

	private static final long serialVersionUID = -3616609574004882188L;
	public User(){}
	
	public User(String userId) {
		super();
		this.userId = userId;
	}
	
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(length=32)
	private String userId;
	/**
	 * 登录账号
	 * 同时作为盐值
	 */
	@Column(length=32,unique=true)
	private String userCd;
	/**
	 * 用户名称
	 */
	@Column(length=128,unique=true)
	private String userNm;
	/**
	 * 登录密码,加密算法:SimpleHash("MD5", password, realCd,2)
	 */
	@Column(length=32)
	private String password;
	
	/**
	 * 最近登录时间
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	/**
	 * 最近登录Ip
	 */
	@Column(length=16)
	private String lastIp;
	
	/**
	 * 是否是管理员:0,非管理员,1:管理员
	 * 一般情况下,系统只初始化一个管理员
	 * 管理员只能访问非业务功能,在系统初始化时确定
	 * 其他用户只能访问业务功能
	 */
	@Column
	private Integer isAdmin=0;
	
	@Override
	public String getId() {
		return getUserId();
	}

	@Override
	public void setId(String id) {
		setUserId(id);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserCd() {
		return userCd;
	}

	public void setUserCd(String userCd) {
		this.userCd = userCd;
	}

	public String getUserNm() {
		return userNm;
	}

	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}

	public String getPassword() {
		return password;
	}
	/**加密密码*/
	@Transient
	public String getMD5Password() {
		return StringUtil.getMD5(StringUtil.getMD5(password)+userCd);
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	/**
	 * 用于接收前台参数.
	 * 修改密码时：第一次输入的密码
	 * 登录时          ：验证码
	 */
	@Transient
	private String password1;
	/**
	 * 用于接收前台参数.
	 * 修改密码时：第二次输入的确认密码
	 */
	@Transient
	private String password2;

	public String getPassword1() {
		return password1;
	}
	/**加密密码*/
	@Transient
	public String getMD5Password1() {
		return StringUtil.getMD5(StringUtil.getMD5(password1)+userCd);
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}
	/**加密密码*/
	@Transient
	public String getMD5Password2() {
		return StringUtil.getMD5(StringUtil.getMD5(password2)+userCd);
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userCd=" + userCd + ", userNm=" + userNm + ", password=" + password + ", lastLogin=" + lastLogin + ", lastIp=" + lastIp + ", isAdmin=" + isAdmin + ", password1=" + password1 + ", password2=" + password2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}


}
