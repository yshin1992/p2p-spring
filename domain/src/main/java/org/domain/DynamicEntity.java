package org.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class DynamicEntity extends AbstractEntity {

	private static final long serialVersionUID = -873460242435969570L;

	public static final int DELETED_NO=0;
	
	public static final int DELETED_YES=1;
	
	public static final int DELETED_CANNOT=2;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime = new Date();

	@Column(nullable=false)
	private String updateBy = DEFAULTCREATEBY;
	
	@Column
	private Integer deleted = DELETED_NO;

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	
	
}
