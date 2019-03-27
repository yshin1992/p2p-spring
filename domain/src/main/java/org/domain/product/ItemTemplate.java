package org.domain.product;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
public class ItemTemplate extends AbstractEntity {

	private static final long serialVersionUID = -5801604583674510115L;

	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(length = 32)
	private String templateId;
	/**
	 * 模板名称
	 */
	@Column(length = 128)
	private String templateNm;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "itemtemplate_itemtype", joinColumns = { @JoinColumn(name = "itemtemplate_id") }, inverseJoinColumns = { @JoinColumn(name = "itemtype_id") })
	private List<ItemType> itemTypes;
	/**
	 * 该模板是不是默认费用模板
	 */
	private Boolean defaultFlag = false;

	@Override
	public String getId() {
		return getTemplateId();
	}

	@Override
	public void setId(String id) {
		setTemplateId(id);
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateNm() {
		return templateNm;
	}

	public void setTemplateNm(String templateNm) {
		this.templateNm = templateNm;
	}

	public List<ItemType> getItemTypes() {
		return itemTypes;
	}

	public void setItemTypes(List<ItemType> itemTypes) {
		this.itemTypes = itemTypes;
	}

	public Boolean getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(Boolean defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public String getDefaultFlagStr(){
		return this.defaultFlag ? "是":"否";
	}
	
	public String getItemTypesNm() {
		if (itemTypes != null) {
			StringBuilder sb = new StringBuilder();
			for (ItemType type : itemTypes) {
				sb.append(",").append(type.getItemTypeNm());
			}
			if (sb.length() > 0) {
				return sb.substring(1);
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	public String getCreateTimeStr() {
		if (null == getCreateTime())
			return "";
		else
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(getCreateTime());
	}
}