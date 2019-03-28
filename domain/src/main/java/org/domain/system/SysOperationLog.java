package org.domain.system;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="sysoperationlog")
public class SysOperationLog extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 操作类型：1：增加/新建
	 */
	public static final int OPERATE_TYPE_ADD = 1;
	/**
	 * 操作类型：2：删除/废弃
	 */
	public static final int OPERATE_TYPE_DEL = 2;
	/**
	 * 操作类型：3：修改/编辑/状态迁移
	 */
	public static final int OPERATE_TYPE_UPDATE = 3;
	/**
	 * 操作类型：4：查询/导出
	 */
	public static final int OPERATE_TYPE_QUERY = 4;

	/**
	 * 操作对象类型：1：项目
	 */
	public static final String OBJ_TYPE_PRODUCT = "1";
	/**
	 * 操作对象类型：2：会员
	 */
	public static final String OBJ_TYPE_MEMBER = "2";
	/**
	 * 操作对象类型：3：订单
	 */
	public static final String OBJ_TYPE_ORDER = "3";
	/**
	 * 操作对象类型：4：企业客户
	 */
	public static final String OBJ_TYPE_COMPANY_MEMBER = "4";
	/**
	 * 操作对象类型：2.1：身份证
	 */
	public static final String OBJ_TYPE_IDCARD = "2.1";
	/**
	 * 操作对象类型：2.2：手机
	 */
	public static final String OBJ_TYPE_PHONE = "2.2";
	/**
	 * 操作对象类型：2.3：银行卡号
	 */
	public static final String OBJ_TYPE_BANKCARD = "2.3";
	/**
	 * 操作对象类型：9：其他
	 */
	public static final String OBJ_TYPE_OTHER = "9";

	/**
	 * 日志主键
	 */
	@Id
	@Column(name = "logId", length = 32)
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	private String logId;

	/**
	 * 操作类型。1：增加/新建、2：删除/废弃、3：修改/编辑/状态迁移、4：查询/导出
	 */
	@Column
	private Integer operateType;

	@Transient
	public String getOperateTypeNm() {
		if (null==operateType) {
			return "";
		}
		switch (operateType) {
		case 1:
			return "新增";
		case 2:
			return "删除";
		case 3:
			return "编辑";
		case 4:
			return "导出";
		default:
			return "";
		}
	}

	/**
	 * 操作对象
	 */
	@Column(length = 32)
	private String operateObj;

	/**
	 * 操作对象类型。1：项目、2：会员、3：订单、4:企业客户 2.1：身份证、2.2：手机、2.3：银行卡号 9：其他
	 */
	@Column(length = 32)
	private String operateObjType;

	public String getOperateObjTypeNm() {
		if (StringUtils.isEmpty(operateObjType)) {
			return "";
		}
		if (operateObjType.equals("1")) {
			return "项目数据";
		} else if (operateObjType.equals("2")) {
			return "会员数据";
		} else if (operateObjType.equals("3")) {
			return "订单数据";
		} else if (operateObjType.equals("2.1")) {
			return "会员-->身份证";
		} else if (operateObjType.equals("2.2")) {
			return "会员-->手机";
		} else if (operateObjType.equals("2.3")) {
			return "会员-->银行卡号";
		} else if (operateObjType.equals("4")) {
			return "企业客户";
		} else if (operateObjType.equals("9")) {
			return "其他";
		} else {
			return "";
		}
	}

	/**
	 * 操作对象名
	 */
	@Column(length = 200)
	private String operateObjName;

	/**
	 * 操作内容
	 */
	@Column(length = 200)
	private String operateContent;

	/**
	 * 操作人ID
	 */
	@Column(length = 32)
	private String operatorId;

	/**
	 * 操作人账号
	 */
	@Column(length = 32)
	private String operatorAccount;

	/**
	 * 操作人用户类型
	 */
	@Column
	private String operatorType;

	/**
	 * 操作时间
	 */
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date operateTime;

	@Transient
	public String getOperateTimeStr() {
		if (null == getOperateTime())
			return "";
		else
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(getOperateTime());
	}

	/**
	 * 备注
	 */
	@Column(length = 200)
	private String remark;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public String getOperateObj() {
		return operateObj;
	}

	public void setOperateObj(String operateObj) {
		this.operateObj = operateObj;
	}

	public String getOperateObjName() {
		return operateObjName;
	}

	public void setOperateObjName(String operateObjName) {
		this.operateObjName = operateObjName;
	}

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperatorAccount() {
		return operatorAccount;
	}

	public void setOperatorAccount(String operatorAccount) {
		this.operatorAccount = operatorAccount;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String getId() {
		return getLogId();
	}

	@Override
	public void setId(String id) {
		setLogId(id);
	}

	public String getOperateObjType() {
		return operateObjType;
	}

	public void setOperateObjType(String operateObjType) {
		this.operateObjType = operateObjType;
	}
	
	public SysOperationLog(String objId, String objNm, String operateContent,
			Integer operateType, String operateObjType, String remark, User user) {
		this.operateObj = objId;
		this.operateObjName = objNm;
		this.operateType = operateType;
		this.operatorId = user.getUserId();
		this.operatorAccount = user.getUserCd();
		this.operatorType = "20";// 操作人类型 20:表示后台操作人员
		this.operateContent = operateContent;
		this.operateObjType = operateObjType;
		this.operateTime = new Date();
		this.remark = remark;
	}

	public SysOperationLog() {
	}
}
