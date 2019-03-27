package org.domain.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.domain.StaticEntity;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class CategoryAttr extends StaticEntity {
	private static final long serialVersionUID = -5080615425989870251L;
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(length = 32)
	private String attrId;
	@Column(length = 128)
	@JsonProperty
	private String attrCd;
	@Column(length = 128)
	@JsonProperty
	private String attrNm;
	@Column(length = 1000)
	@JsonProperty
	private String actualval;
	@Column(length = 1000)
	@JsonProperty
	private String defaultVal;
	@ManyToOne(cascade = { javax.persistence.CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "categoryId")
	@JsonIgnore
	private Category category;
	@Column
	private Integer attrRequired;
	@Column
	private String remark;
	@Transient
	private String pId;
	@Transient
	private String code;
	@Transient
	private String name;

	public String getId() {
		return getAttrId();
	}

	public void setId(String id) {
		setAttrId(id);
	}

	public String getAttrId() {
		return this.attrId;
	}

	public void setAttrId(String attrId) {
		this.attrId = attrId;
	}

	public String getAttrCd() {
		return this.attrCd;
	}

	public void setAttrCd(String attrCd) {
		this.attrCd = attrCd;
	}

	public String getAttrNm() {
		return this.attrNm;
	}

	public void setAttrNm(String attrNm) {
		this.attrNm = attrNm;
	}

	public String getDefaultVal() {
		return this.defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getAttrRequired() {
		return this.attrRequired;
	}

	public void setAttrRequired(Integer attrRequired) {
		this.attrRequired = attrRequired;
	}

	public String getActualval() {
		return this.actualval;
	}

	public void setActualval(String actualval) {
		this.actualval = actualval;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CategoryAttr [attrId=");
		builder.append(this.attrId);
		builder.append(", attrCd=");
		builder.append(this.attrCd);
		builder.append(", attrNm=");
		builder.append(this.attrNm);
		builder.append(", actualval=");
		builder.append(this.actualval);
		builder.append(", defaultVal=");
		builder.append(this.defaultVal);
		builder.append(", category=");
		builder.append(this.category);
		builder.append(", attrRequired=");
		builder.append(this.attrRequired);
		builder.append(", remark=");
		builder.append(this.remark);
		builder.append("]");
		return builder.toString();
	}

	public String getpId() {
		return getCategory() != null ? getCategory().getCategoryId() : null;
	}

	public String getCode() {
		return getAttrCd();
	}

	public String getName() {
		return getAttrNm();
	}
}
