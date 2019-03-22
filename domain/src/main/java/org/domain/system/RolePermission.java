package org.domain.system;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue(value="Role")
public class RolePermission extends Permission{

	private static final long serialVersionUID = -9201145947557068519L;

	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role;
	
	public RolePermission(Role role,Resource resource) {
		super();
		this.role = role;
	    setResource(resource);
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
