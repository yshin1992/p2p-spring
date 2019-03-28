package org.domain.system;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.domain.StaticEntity;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sys_app")
public class Application extends StaticEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8239300424024027959L;
	/**
	 * 应用Id
	 */
	@Id
	@GenericGenerator(name="systemUUID",strategy="uuid")
	@GeneratedValue(generator="systemUUID")
	@Column(length=32,name="appId")
	private String appId;
	/**
	 * 应用唯一编码
	 */
	@Column(length=64,nullable=true,unique=true)
	private String appCd;
	/**
	 * 应用名称
	 */
	@Column(length=128)
	private String appName;
	/**
	 * 应用备注
	 */
	@Column(length=255)
	private String appDesc;
	/**
	 * 应用所在主机:可以是Ip地址,也可以是主机名、也可以是域名
	 */
	@Column(length=64)
	private String host;
	/**
	 * 应用所在端口
	 */
	@Column(length=8)
	private String port;
	/**
	 * 应用所对应的上下文
	 */
	@Column(length=64)
	private String context;
	
	/**
	 * 是否是业务系统
	 */
	@Column
	private Integer isBusiness=1;
	
	@Override
	public void setId(String id) {
		this.appId=id;

	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.appId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppCd() {
		return appCd;
	}

	public void setAppCd(String appCd) {
		this.appCd = appCd;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public Integer getIsBusiness() {
		return isBusiness;
	}

	public void setIsBusiness(Integer isBusiness) {
		this.isBusiness = isBusiness;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appCd == null) ? 0 : appCd.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Application other = (Application) obj;
		if (appCd == null) {
			if (other.appCd != null)
				return false;
		} else if (!appCd.equals(other.appCd))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Application [appId=" + appId + ", appCd=" + appCd
				+ ", appName=" + appName + ", appDesc=" + appDesc + ", host="
				+ host + ", port=" + port + ", context=" + context
				+ ", isBusiness=" + isBusiness + "]";
	}

	
}
