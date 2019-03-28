package org.domain.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.domain.StaticEntity;
import org.hibernate.annotations.GenericGenerator;
import org.vo.TreeNodeDto;

@Entity
@Table(name="sys_role")
public class Role extends StaticEntity{
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(length=32)
	private String roleId;
	/**
	 * 角色内部编码
	 */
	@Column(length=64,nullable=false,unique=true)
	private String roleCd;
	/**
	 * 角色名称
	 */
	@Column(length=128)
	private String roleNm;
	/**
	 * 角色描述
	 */
	@Column(length=255)
	private String roleDesc;
	
	@Override
	public String getId() {
		return getRoleId();
	}
	public Role(){}
	
	public Role(String roleId) {
		super();
		this.roleId = roleId;
	}

	@Override
	public void setId(String id) {
		setRoleId(id);
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleNm() {
		return roleNm;
	}

	public void setRoleNm(String roleNm) {
		this.roleNm = roleNm;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getRoleCd() {
		return roleCd;
	}

	public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
	}
	
	/**返回树节点*/
	public TreeNodeDto geTreeNode(boolean checked){
		return new TreeNodeDto(getId(), "0",getRoleCd(),getRoleNm(), checked);
	}
}
