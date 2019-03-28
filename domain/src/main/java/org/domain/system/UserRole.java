package org.domain.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.domain.StaticEntity;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sys_user_role")
public class UserRole extends StaticEntity {

	private static final long serialVersionUID = -3014374999017968536L;
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(length=32)
	private String userRoleId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role;
	
	

	@Override
	public String getId() {
		return getUserRoleId();
	}

	@Override
	public void setId(String id) {
		setUserRoleId(id);
	}

	public String getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public UserRole(){}
	
	public UserRole(User u, Role r) {
		setUser(u);
		setRole(r);
	}
}
