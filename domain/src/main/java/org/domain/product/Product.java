package org.domain.product;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.domain.DynamicEntity;
import org.hibernate.annotations.GenericGenerator;

@Table(name="product")
public class Product extends DynamicEntity{
	private static final long serialVersionUID = 6068126871135298436L;
	
	public static final Integer STATUS_DRAFT = Integer.valueOf(10);//草稿
	public static final Integer STATUS_ONAUDIT = Integer.valueOf(11);
	public static final Integer STATUS_DISCARD = Integer.valueOf(12);
	public static final Integer STATUS_REVOKE = Integer.valueOf(13);
	public static final Integer STATUS_AUDIT_REJECT = Integer.valueOf(14);
	public static final Integer STATUS_AUDIT_FAILED = Integer.valueOf(15);
	public static final Integer STATUS_AUDITING = Integer.valueOf(16);//项目审核中
	public static final Integer STATUS_SCALE_ON = Integer.valueOf(31);
	public static final Integer STATUS_SCALE__REVOKE = Integer.valueOf(40);
	public static final Integer STATUS_ONLINE = Integer.valueOf(32);
	public static final Integer STATUS_SCALE_ONWAY = Integer.valueOf(30);
	public static final Integer STATUS_SCALEFULL_AUDITON = Integer.valueOf(60);
	public static final Integer STATUS_SCALEFAILED_ONDEAL = Integer.valueOf(61);
	public static final Integer STATUS_LOAN_ON = Integer.valueOf(80);
	public static final Integer STATUS_LOANING = Integer.valueOf(81); //放款中
	public static final Integer STATUS_REPAYMENT_ON = Integer.valueOf(90);
	public static final Integer STATUS_REPAYING = Integer.valueOf(91);//还款请求中
	public static final Integer STATUS_REPAYMENT_DONE = Integer.valueOf(100);//已还完"
	public static final Integer STATUS_SCALEDISCARD_REFOUND_ON = Integer.valueOf(70);
	public static final Integer STATUS_SCALEDISCARD_REFOUND_DONE = Integer.valueOf(101);
	public static final Integer STATUS_SCALEFAILED_REFOUND_ON = Integer.valueOf(71);
	public static final Integer STATUS_SCALEREVOKE_REFOUND_ON = Integer.valueOf(73);
	public static final Integer STATUS_SCALEREVOKE_REFOUND_DONE = Integer.valueOf(103);
	public static final Integer STATUS_SCALEFAILED_REFOUND_DONE = Integer.valueOf(102);

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
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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
	
	

}
