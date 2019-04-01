package org.domain.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

/**
 * 访问请求数据记录
 * @author yshin1992
 *
 */
@Entity
public class VisitData extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 数据标识
	 */
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(length = 32)
	private String dataId;

	/**
	 * 访问IP
	 */
	@Column(length = 50)
	private String visitIp;

	/**
	 * 访问类型 发送短信时存储短信请求节点
	 */
	@Column(length = 128)
	private String visitType;

	/**
	 * 访问会员数据 登录或找回密码限制时存储会员memberId,发送短信时填写手机号
	 */
	@Column(length = 255)
	private String userData;

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public String getVisitIp() {
		return visitIp;
	}

	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}

	public String getVisitType() {
		return visitType;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
	}

	public String getUserData() {
		return userData;
	}

	public void setUserData(String userData) {
		this.userData = userData;
	}

	@Override
	public String getId() {
		return getDataId();
	}

	@Override
	public void setId(String id) {
		setDataId(id);
	}

}
