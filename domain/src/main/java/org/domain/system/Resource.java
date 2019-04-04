package org.domain.system;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.domain.StaticEntity;
import org.hibernate.annotations.GenericGenerator;
import org.vo.TreeNodeDto;

@Entity
@Table(name="sys_resource")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)//使用@Inheritance注释，标识该类的子类继承映射的方式，SINGLE_TABLE表示继承关系的实体保存在一个表
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING,name="resourceType")//标识该继承层次上所区别每个实体的类型字段，我们的Resource使用resourceType字段来区分
public abstract class Resource extends StaticEntity{

	private static final long serialVersionUID = -7770321122884855112L;

	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(length=32)
	private String resourceId;
	
	/**
	 * 资源类型:Menu,Function
	 */
	@Column(length=32,insertable=false,updatable=false)
	private String resourceType;
	/**
	 * 资源编码
	 */
	@Column(length=128,unique=true)
	private String resourceCd;
	/**
	 * 资源名称
	 */
	@Column(length=128)
	private String resourceNm;
	/**
	 * 资源所在资源树的层级, 根为第一级,后面以此类推
	 * 主要用在同级节点的排序(自动生成)
	 */
	@Column
	private Integer resourceLevel;
	/**
	 * 资源所属应用系统
	 */
	@ManyToOne
	@JoinColumn(name="appId")
	private Application app;
	
	@ManyToOne(fetch=FetchType.EAGER)//ManyToOne指定了多对一的关系，fetch=FetchType.LAZY属性表示在多的那一方通过延迟加载的方式加载对象(默认不是延迟加载)
	@JoinColumn(name="resourcePid")//通过 JoinColumn 的name属性指定了外键的名称 rid
	private Resource parent;
	
	@OneToMany(mappedBy = "parent")
	@OrderBy("listsort asc")//排序
	private List<Resource> childs;
	
	@OneToMany(mappedBy = "resource")
	private List<Permission> permissions=new ArrayList<Permission>();
	
	@Column
	private String appCd;
	
	@Override
	public String getId() {
		return getResourceId();
	}

	@Override
	public void setId(String id) {
		setResourceId(id);
	}
	
	/**上级菜单编码*/
	@Transient
	private String parentCd;


	public String getResourceId() {
		return resourceId;
	}


	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}


	public String getResourceType() {
		return resourceType;
	}


	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}


	public String getResourceCd() {
		return resourceCd;
	}


	public void setResourceCd(String resourceCd) {
		this.resourceCd = resourceCd;
	}


	public String getResourceNm() {
		return resourceNm;
	}


	public void setResourceNm(String resourceNm) {
		this.resourceNm = resourceNm;
	}


	public Integer getResourceLevel() {
		return resourceLevel;
	}


	public void setResourceLevel(Integer resourceLevel) {
		this.resourceLevel = resourceLevel;
	}


	public Application getApp() {
		return app;
	}


	public void setApp(Application app) {
		this.app = app;
	}


	public Resource getParent() {
		return parent;
	}


	public void setParent(Resource parent) {
		this.parent = parent;
	}


	public List<Resource> getChilds() {
		return childs;
	}


	public void setChilds(List<Resource> childs) {
		this.childs = childs;
	}


	public List<Permission> getPermissions() {
		return permissions;
	}


	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}


	public String getParentCd() {
		return parentCd;
	}


	public void setParentCd(String parentCd) {
		this.parentCd = parentCd;
	}
	
	/**获取节点的上级Id*/
	@Transient
	public String getPid(){
		return parent!=null?parent.getId():null;
	}

	public String getAppCd() {
		return appCd;
	}

	public void setAppCd(String appCd) {
		this.appCd = appCd;
	}
	
	/**
	 *把资源节点转换为树节点,状态是否选中取决于isEnabled属性
	 **/
	public TreeNodeDto geTreeNode(){
		return new TreeNodeDto(getId(), getPid(), getResourceCd(), getResourceNm(), isEnabled());
	}
	
	/**
	 *把资源节点转换为树节点,状态是否选中取决于传入的checked参数
	 **/
	public TreeNodeDto geTreeNode(boolean checked){
		return new TreeNodeDto(getId(), getPid(), getResourceCd(), getResourceNm(), checked);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Resource r = (Resource) obj;
		return (this.getResourceCd().equals(r.getResourceCd()))
				&& (this.getApp().equals(r.getApp()));
	}

	@Override
	public int hashCode() {
		return (this.getApp() + this.getResourceCd()).hashCode();
	}
}
