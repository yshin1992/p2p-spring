package org.domain.product;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.domain.DynamicEntity;
import org.domain.member.Member;
import org.hibernate.annotations.GenericGenerator;
import org.util.StringUtil;

@Entity
@Table(name="product")
public class Product extends DynamicEntity{
	private static final long serialVersionUID = 6068126871135298436L;

	/** 草稿 **/
	public static final Integer STATUS_DRAFT = Integer.valueOf(10);
	/** 待审核 **/
	public static final Integer STATUS_ONAUDIT = Integer.valueOf(11);
	/** 已废弃 **/
	public static final Integer STATUS_DISCARD = Integer.valueOf(12);
	/** 已撤回 **/
	public static final Integer STATUS_REVOKE = Integer.valueOf(13);
	/** 审核驳回 **/
	public static final Integer STATUS_AUDIT_REJECT = Integer.valueOf(14);
	/** 审核失败 **/
	public static final Integer STATUS_AUDIT_FAILURE = Integer.valueOf(15);
	/** 项目审核中 **/
	public static final Integer STATUS_AUDITING = Integer.valueOf(16);//项目审核中
	/** 待招标(待上线) **/
	public static final Integer STATUS_SCALE_ON = Integer.valueOf(31);
	/** 已撤标 **/
	public static final Integer STATUS_SCALE__REVOKE = Integer.valueOf(40);
	/** 已上线 **/
	public static final Integer STATUS_ONLINE = Integer.valueOf(32);
	/** 招标中 **/
	public static final Integer STATUS_SCALE_ONWAY = Integer.valueOf(30);
	/** 满标待审 **/
	public static final Integer STATUS_SCALEFULL_AUDITON = Integer.valueOf(60);
	/** 流标待处理 **/
	public static final Integer STATUS_SCALEFAILED_ONDEAL = Integer.valueOf(61);
	/** 待放款 **/
	public static final Integer STATUS_LOAN_ON = Integer.valueOf(80);
	/** 放款中 **/
	public static final Integer STATUS_LOANING = Integer.valueOf(81); //放款中
	/** 还款中 **/
	public static final Integer STATUS_REPAYMENT_ON = Integer.valueOf(90);
	/** 还款请求中 **/
	public static final Integer STATUS_REPAYING = Integer.valueOf(91);//还款请求中
	/** 已还完 **/
	public static final Integer STATUS_REPAYMENT_DONE = Integer.valueOf(100);//已还完"
	/** 废标待退款 **/
	public static final Integer STATUS_SCALEDISCARD_REFOUND_ON = Integer.valueOf(70);
	/** 废标已退款 **/
	public static final Integer STATUS_SCALEDISCARD_REFOUND_DONE = Integer.valueOf(101);
	/** 流标待退款 **/
	public static final Integer STATUS_SCALEFAILED_REFOUND_ON = Integer.valueOf(71);
	/** 撤标待退款 **/
	public static final Integer STATUS_SCALEREVOKE_REFOUND_ON = Integer.valueOf(73);
	/** 撤标已退款 **/
	public static final Integer STATUS_SCALEREVOKE_REFOUND_DONE = Integer.valueOf(103);
	/** 流标已退款 **/
	public static final Integer STATUS_SCALEFAILED_REFOUND_DONE = Integer.valueOf(102);
	/** 已流标 **/
	public static final Integer STATUS_SCALEFAILED = Integer.valueOf(50);
	
	/**
	 * 设置代偿
	 */
	public static final Integer STATUS_COMPENSATORY_INIT = 0;
	public static final Integer STATUS_COMPENSATORY_ALLAY = 1;
	public static final Integer STATUS_COMPENSATORY_SUCCESS = 2;
	public static final Integer STATUS_COMPENSATORY_FAILED = 3;
	
	
	@Id
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	@Column(length = 32)
	private String productId;

	@Column(length = 32, unique = true)
	private String productCd;

	@Column(length = 128)
	private String productNm;

	@Column(precision = 19, scale = 4)
	private BigDecimal rate = BigDecimal.ZERO;

	@Column
	private Integer period;

	@Column
	private Integer periodType;

	@Column(length = 32)
	private String directPassword;

	@Column(precision = 19, scale = 4)
	private BigDecimal unitPrice;

	@Column
	private Long quantity = Long.valueOf(0L);

	@Column
	private Long castQuantity = Long.valueOf(0L);

	@Column
	private Integer status;

	@Column(length = 64)
	private String statusName;

	@Column
	private Long minTenderQuantity = Long.valueOf(0L);

	@Column
	private Long maxTenderQuantity = Long.valueOf(0L);

	@Column
	private Long minFullQuantity = Long.valueOf(0L);

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date groundTime;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date tenderStart;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date tenderEnd;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date contractEffTime;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date fullScaleTime;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date interestStartTime;

	@Column
	private Integer brrowUse;

	@Column(length = 64)
	private String brrowUseName;

	@Column
	private Integer repayMethod;

	@Column
	private Integer tenderKind;

	@Column(length = 64)
	private String tenderKindName;

	@Column
	private Integer businessType;

	@Column(length = 64)
	private String businessTypeNm;

	@ManyToOne
	@JoinColumn(name="memberId")
	private Member member;

	@OneToMany(mappedBy = "Product")
	private List<ProductItemType> itemTypes;


	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.ALL })
	private List<ProductAuthInfo> productAuthInfos = new ArrayList<ProductAuthInfo>();

	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = { javax.persistence.CascadeType.ALL })
	private List<ProductPledge> productPledges = new ArrayList<ProductPledge>();


	@OneToMany(cascade = { javax.persistence.CascadeType.ALL })
	@JoinTable(joinColumns = { @JoinColumn(name = "productId") }, inverseJoinColumns = { @JoinColumn(name = "attachmentId") })
	private List<Attachment> attachments;

	@OneToOne
	@JoinColumn(name="safeguardId")
	private Safeguard safeguard;

	/**
	 * 借款人
	 * @param id
	 */
	@Override
	public void setId(String id) {
		// TODO Auto-generated method stub
		this.productId = id;
	}



	@Override
	public String getId() {
		return this.productId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductCd() {
		return productCd;
	}

	public void setProductCd(String productCd) {
		this.productCd = productCd;
	}

	public String getProductNm() {
		return productNm;
	}

	public void setProductNm(String productNm) {
		this.productNm = productNm;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getPeriodType() {
		return periodType;
	}

	public void setPeriodType(Integer periodType) {
		this.periodType = periodType;
	}

	public String getDirectPassword() {
		return directPassword;
	}

	public void setDirectPassword(String directPassword) {
		this.directPassword = directPassword;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getCastQuantity() {
		return castQuantity;
	}

	public void setCastQuantity(Long castQuantity) {
		this.castQuantity = castQuantity;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		if (StringUtil.isNotEmpty(this.status)) {
			switch (this.status.intValue()) {
				case 10:
					return "草稿";
				case 11:
					return "待审核";
				case 12:
					return "已废弃";
				case 13:
					return "已撤回";
				case 14:
					return "审核驳回";
				case 15:
					return "审核失败";
				case 31:
					return "待招标(待上线)";
				case 40:
					return "已撤标";
				case 32:
					return "已上线";
				case 30:
					return "招标中";
				case 60:
					return "满标待审";
				case 61:
					return "流标待处理";
				case 80:
					return "待放款";
				case 90:
					return "还款中";
				case 91:
					return "还款处理中";
				case 100:
					return "已还完";
				case 70:
					return "废标待退款";
				case 101:
					return "废标已退款";
				case 71:
					return "流标待退款";
				case 73:
					return "撤标待退款";
				case 102:
					return "流标已退款";
				case 50:
					return "已流标";
				case 103:
					return "撤标已退款";
				case 16:
				case 17:
				case 18:
				case 19:
				case 20:
				case 21:
				case 22:
				case 23:
				case 24:
				case 25:
				case 26:
				case 27:
				case 28:
				case 29:
				case 33:
				case 34:
				case 35:
				case 36:
				case 37:
				case 38:
				case 39:
				case 41:
				case 42:
				case 43:
				case 44:
				case 45:
				case 46:
				case 47:
				case 48:
				case 49:
				case 51:
				case 52:
				case 53:
				case 54:
				case 55:
				case 56:
				case 57:
				case 58:
				case 59:
				case 62:
				case 63:
				case 64:
				case 65:
				case 66:
				case 67:
				case 68:
				case 69:
				case 72:
				case 74:
				case 75:
				case 76:
				case 77:
				case 78:
				case 79:
				case 81:
				case 82:
				case 83:
				case 84:
				case 85:
				case 86:
				case 87:
				case 88:
				case 89:
				case 92:
				case 93:
				case 94:
				case 95:
				case 96:
				case 97:
				case 98:
				case 99:
			}
			return "";
		}

		return "";
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getPeriodDesc() {
		switch (this.periodType.intValue()) {
			case 1:
				return this.period + "天";
			case 2:
				return this.period + "个月";
			case 4:
				return this.period + "年";
			case 3:
		}
		return "";
	}

	public Long getMinTenderQuantity() {
		return minTenderQuantity;
	}

	public void setMinTenderQuantity(Long minTenderQuantity) {
		this.minTenderQuantity = minTenderQuantity;
	}

	public Long getMaxTenderQuantity() {
		return maxTenderQuantity;
	}

	public void setMaxTenderQuantity(Long maxTenderQuantity) {
		this.maxTenderQuantity = maxTenderQuantity;
	}

	public Long getMinFullQuantity() {
		return minFullQuantity;
	}

	public void setMinFullQuantity(Long minFullQuantity) {
		this.minFullQuantity = minFullQuantity;
	}

	public Date getGroundTime() {
		return groundTime;
	}

	public void setGroundTime(Date groundTime) {
		this.groundTime = groundTime;
	}

	public Date getTenderStart() {
		return tenderStart;
	}

	public void setTenderStart(Date tenderStart) {
		this.tenderStart = tenderStart;
	}

	public Date getTenderEnd() {
		return tenderEnd;
	}

	public void setTenderEnd(Date tenderEnd) {
		this.tenderEnd = tenderEnd;
	}

	public Date getContractEffTime() {
		return contractEffTime;
	}

	public void setContractEffTime(Date contractEffTime) {
		this.contractEffTime = contractEffTime;
	}

	public Date getFullScaleTime() {
		return fullScaleTime;
	}

	public void setFullScaleTime(Date fullScaleTime) {
		this.fullScaleTime = fullScaleTime;
	}

	public Date getInterestStartTime() {
		return interestStartTime;
	}

	public void setInterestStartTime(Date interestStartTime) {
		this.interestStartTime = interestStartTime;
	}

	public Integer getBrrowUse() {
		return brrowUse;
	}

	public void setBrrowUse(Integer brrowUse) {
		this.brrowUse = brrowUse;
	}

	public String getBrrowUseName() {
		return brrowUseName;
	}

	public void setBrrowUseName(String brrowUseName) {
		this.brrowUseName = brrowUseName;
	}

	public Integer getRepayMethod() {
		return repayMethod;
	}

	public void setRepayMethod(Integer repayMethod) {
		this.repayMethod = repayMethod;
	}

	public Integer getTenderKind() {
		return tenderKind;
	}

	public void setTenderKind(Integer tenderKind) {
		this.tenderKind = tenderKind;
	}

	public String getTenderKindName() {
		return tenderKindName;
	}

	public void setTenderKindName(String tenderKindName) {
		this.tenderKindName = tenderKindName;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public String getBusinessTypeNm() {
		return businessTypeNm;
	}

	public void setBusinessTypeNm(String businessTypeNm) {
		this.businessTypeNm = businessTypeNm;
	}

	public String getGroundTimeStr() {
		if (null == getGroundTime()) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(getGroundTime());
	}

	public String getTenderEndStr() {
		if (null == getTenderEnd()) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(getTenderEnd());
	}

	public String getCreateTimeStr() {
		if (null == getCreateTime()) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd  HH:mm").format(getCreateTime());
	}

	public String getTenderStartSrc() {
		if (null == getTenderStart()) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd  HH:mm").format(getTenderStart());
	}

	public String getGroundTimeStr1() {
		if (null == getGroundTime()) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getGroundTime());
	}

	public String getTenderEndStr1() {
		if (null == getTenderEnd()) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getTenderEnd());
	}

	public String getCreateTimeStr1() {
		if (null == getCreateTime()) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getCreateTime());
	}

	public String getTenderStartSrc1() {
		if (null == getTenderStart()) {
			return "";
		}
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getTenderStart());
	}

}
