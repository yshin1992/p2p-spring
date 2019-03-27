package org.domain.system;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.domain.StaticEntity;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="category")
public class Category extends StaticEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6763938258886448154L;

	@Id
	@GenericGenerator(name="SystemUUID",strategy="uuid")
	@GeneratedValue(generator="SystemUUID")
	@Column(length=32)
	private String categoryId;
	
	@Column(length=128)
	private String categoryCd;
	
	@Column(length=128)
	private String categoryNm;
	
	@Column(length=255)
	private String categoryDesc;
	
	@Column
	private Integer edited=0;
	
	@Column
	private Integer configed;
	
	@Column
	private String remark;
	
	@OneToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,mappedBy="category")
	private Set<CategoryAttr> attrs;
	
	@Override
	public void setId(String id) {
		this.categoryId = id;
	}

	@Override
	public String getId() {
		return this.categoryId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryCd() {
		return categoryCd;
	}

	public void setCategoryCd(String categoryCd) {
		this.categoryCd = categoryCd;
	}

	public String getCategoryNm() {
		return categoryNm;
	}

	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public Integer getEdited() {
		return edited;
	}

	public void setEdited(Integer edited) {
		this.edited = edited;
	}

	public Integer getConfiged() {
		return configed;
	}

	public void setConfiged(Integer configed) {
		this.configed = configed;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Set<CategoryAttr> getAttrs() {
		return attrs;
	}

	public void setAttrs(Set<CategoryAttr> attrs) {
		this.attrs = attrs;
	}
	
}
