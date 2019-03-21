package org.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass//表示该类将不是一个完整的实体类，他将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中。
public abstract class AbstractEntity implements Serializable{

	private static final long serialVersionUID = -4181970274497470818L;

	protected static String DEFAULTCREATEBY = "system";
	
	//悲观锁：把所需要的数据全部加锁，不允许其他事务对数据做修改 update xxx where xxxx for update
	//乐观锁：对数据进行版本校验，如果版本不一致，则操作数据失败 update xxx,version+1  where xxxx and version=x
	@Version//@Version注解，可以实现乐观锁功能
	private Integer version = 0;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonIgnore//生成json 时不生成该属性
	@Column(name="createTime")
	private Date createTime=new Date();
	
	@JsonIgnore
	@Column(name="createBy",length=32)
	private String createBy = DEFAULTCREATEBY;
	
	@Transient
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonIgnore//生成json 时不生成该属性
	private Date effTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonIgnore//生成json 时不生成该属性
	@Transient
	private Date expTime;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}


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
	
	public void init(){
		if(null == createBy)
			createBy=DEFAULTCREATEBY;
		if(null == createTime)
			createTime=new Date();
	}
	
	public abstract void setId(String id);
	
	public abstract String getId();
	
	@Transient
	@JsonIgnore
	public String getCreateTimeStr(){
		if(null ==  createTime)
			return "";
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
}
