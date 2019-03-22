package org.domain.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public abstract class URLLink extends Resource {

	private static final long serialVersionUID = 6627716137529068314L;
	
	/**
	 * 资源连接路径
	 */
	@Column
	private String resourceLink;
	
	/**
	 * 设置链接对应的target属性<a href='' target=''>
	 * 可取值为: _blank、 _parent、 _self、 _top、自定义的framename
	 */
	@Column(length=64)
	private String target;

	@Transient
	public String getUrl(){
		return String.format("%s%s", getApp().getContext(),resourceLink);
	}
	
	public String getResourceLink() {
		return resourceLink;
	}

	public void setResourceLink(String resourceLink) {
		this.resourceLink = resourceLink;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	

}
