package org.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class StaticEntity extends AbstractEntity {
	private static final long serialVersionUID = 8925082152953500811L;

	public static final String STATE_ENABLED = "F0A";
	public static final String STATE_DISABLED = "F0X";

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date effTime;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date expTime;

	@Column(length = 3)
	private String state;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date stateTime;

	@Column(nullable=false)
	private Integer listSort = Integer.valueOf(1);

	public Date getEffTime() {
		return effTime;
	}

	public void setEffTime(Date effTime) {
		this.effTime = effTime;
	}

	public Date getExpTime() {
		return expTime;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getStateTime() {
		return stateTime;
	}

	public void setStateTime(Date stateTime) {
		this.stateTime = stateTime;
	}

	public Integer getListSort() {
		return listSort;
	}

	public void setListSort(Integer listSort) {
		this.listSort = listSort;
	}

	public boolean isEnabled() {
		Date d1 = new Date();
		return ("F0A".equals(this.state)) && (d1.after(this.effTime))
				&& (d1.before(this.expTime));
	}

	public void setEnable(boolean enable) {
		setState(enable ? "F0A" : "F0X");
	}

	@Transient
	public String getStateName() {
		if ("F0A".equals(getState())) {
			return "有效";
		}
		return "失效";
	}

	public void disable() {
		setEnable(false);
		setStateTime(new Date());
		setExpTime(new Date());
	}

	public void enable() {
		setEnable(true);
		setStateTime(new Date());
		setExpTime(DateUtils.addYears(new Date(), 100));
	}

	public void init() {
		super.init();
		if (getEffTime() == null)
			setEffTime(new Date());
		try {
			if (getExpTime() == null)
				setExpTime(DateUtils.parseDate("22001231235959",
						new String[] { "yyyyMMddHHmmss" }));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		if (getStateTime() == null)
			setStateTime(new Date());
		if (getState() == null)
			setEnable(true);
		if (getListSort() == null)
			setListSort(Integer.valueOf(1));
	}
}
