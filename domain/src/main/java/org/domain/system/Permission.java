package org.domain.system;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.domain.StaticEntity;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sys_permission")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "permissionType", discriminatorType = DiscriminatorType.STRING)
public abstract class Permission extends StaticEntity {

	private static final long serialVersionUID = -5198803289048885459L;

	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(length=32)
	private String permissionId;
	
	@ManyToOne(optional=false)//optional 属性实际上指定关联类与被关联类的join 查询关系，如optional=false 时join 查询关系为inner join, optional=true 时join 查询关系为left join
	@JoinColumn(name="resourceId")
	private Resource resource;
	
	@Override
	public void setId(String id) {
		this.permissionId = id;
	}

	@Override
	public String getId() {
		return this.permissionId;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	@Override
	public String toString() {
		return "Permission [permissionId=" + permissionId + ", resource="
				+ resource + "]";
	}

}
