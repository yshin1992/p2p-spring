package org.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ProductEnum {
	  CREDIT_TRANSFERMAXTIME("credit.transferMaxTime", "债权转让最大期限"), 

	  INTERESTALGORITHM("interestAlgorithm", "计息天数算法"), 

	  PERIOD_OR_DAY("periodOrDay", "期数或天数"), 

	  RATE_REFERENED("rateReferened", "费率参考"), 

	  BRROW_USE("brrowUse", "借款用途"), 

	  PERIOD_TYPE("periodType", "期限单位"), 

	  TENDER_KIND("tenderKind", "标种类型"), 

	  BUSINESS_TYPE("businessType", "业务类型"), 

	  REPAY_METHOD("repayMethod", "还款方式"), 

	  PRODUCT_STATUS("product.status", "项目所有状态"), 

	  PRODUCT_REPAY_STATUS_0("0", "计划,代偿,申请"), 

	  PRODUCT_REPAY_STATUS_1("1", "还款中-审核完成，调用接口开始还款"), 

	  PRODUCT_REPAY_STATUS_2("2", "完成-还款支付成功"), 

	  PRODUCT_REPAY_STATUS_3("3", "作废,失败"), 

	  PRODUCT_STATUS_10("10", "草稿"), 

	  PRODUCT_STATUS_11("11", "待审核"), 

	  PRODUCT_STATUS_12("12", "已废弃"), 

	  PRODUCT_STATUS_13("13", "已撤回"), 

	  PRODUCT_STATUS_14("14", "审核驳回"), 

	  PRODUCT_STATUS_15("15", "审核失败"), 
	  
	  PRODUCT_STATUS_16("16", "监管审核中"),

	  PRODUCT_STATUS_31("31", "待招标"), 

	  PRODUCT_STATUS_40("40", "已撤标"), 

	  PRODUCT_REPAY_STATUS_32("32", "已上线"), 

	  PRODUCT_REPAY_STATUS_61("61", "流标待处理"), 

	  PRODUCT_REPAY_STATUS_50("40", "已流标"), 

	  PRODUCT_REPAY_STATUS_90("90", "还款状态"), 

	  PRODUCT_REPAY_STATUS_80("80", "待放款"), 
	  
	  PRODUCT_REPAY_STATUS_81("81", "放款中"), 

	  PRODUCT_REPAY_STATUS_30("30", "招标中"), 

	  PRODUCT_REPAY_STATUS_60("60", "满标待审"), 

	  PRODUCT_REPAY_STATUS_70("70", "废标待退款"), 

	  PRODUCT_REPAY_STATUS_71("71", "流标待退款"), 

	  PRODUCT_REPAY_STATUS_73("73", "撤标待退款"), 

	  PRODUCT_REPAY_STATUS_91("91", "还款处理中"), 
	  
	  PRODUCT_REPAY_STATUS_100("100", "已还完"), 

	  PRODUCT_REPAY_STATUS_101("101", "废标已退款"), 

	  PRODUCT_REPAY_STATUS_102("102", "流标已退款"), 

	  PRODUCT_REPAY_STATUS_103("103", "撤标已退款"), 

	  PRODUCT_REPAYKIND_0("0", "自己还款"), 

	  PRODUCT_REPAYKIND_1("1", "代偿还款"), 

	  PRODUCT_COST_CODE_PRINCIPAL("100000", "本金"), 

	  PRODUCT_COST_CODE_ACCRUAL("100800", "利息"), 

	  PRODUCT_READYTENDER_STATUS("product.readytender.status", "项目待招标状态"), 

	  PRODUCT_OVERDUE_DAYS("overdueDays", "逾期天数"), 

	  PRODUCT_OVERDUE_DAYS_1("1", "逾期天数为1-30天"), 

	  PRODUCT_OVERDUE_DAYS_2("2", "逾期天数为>30天"), 

	  PRODUCT_DAYS_1("1", "1天"), 

	  PRODUCT_DAYS_30("30", "30天"), 

	  PRODUCT_CLAIM_STATUS_0("0", "追偿中"), 

	  PRODUCT_CLAIM_STATUS_1("1", "追偿完成"), 

	  PRODUCT_INTEREST_ALGORITHM_3("3", "计息天数算法"), 

	  PRODUCT_rollInout_0("0", "转入"), 

	  PRODUCT_rollInout_1("1", "转出"), 

	  PRODUCT_CREDIT_TRANSFER_STATUS_0("0", "转让中"), 

	  PRODUCT_CREDIT_TRANSFER_STATUS_1("1", "已到期"), 

	  PRODUCT_CREDIT_TRANSFER_STATUS_2("2", "已售完"), 

	  PRODUCT_CREDIT_TRANSFER_STATUS_3("3", "已撤回"), 

	  PRODUCT_CREDIT_TRANSFER_STATUS_4("4", "已违规"), 

	  PRODUCT_CREDIT_TRANSFER_STATUS_5("5", "已取消"), 

	  PRODUCT_CREDIT_TRANSFER_STATUS_6("6", "被取消"), 

	  PRODUCT_APPLICATION("1", "项目申请列表"), 

	  PRODUCT_PENDING("2", "项目待审核列表"), 

	  identity("identity", "身份认证"), 

	  credit("credit", "信用认证"), 

	  singleIncome("singleIncome", "个人收入认证"), 

	  singleJob("singleJob", "个人工作认证"), 

	  live("live", "居住认证"), 

	  phoneAuth("phoneAuth", "手机认证"), 

	  fiveCertificates("fiveCertificates", "五证认证"), 

	  bankflow("bankflow", "银行流水认证"), 

	  enterpriseLoanCard("enterpriseLoanCard", "企业贷款卡认证"), 

	  enterpriseConstitution("enterpriseConstitution", "公司章程认证"), 

	  taxRecord("taxRecord", "缴税纪录认证"), 

	  enterpriseCredit("enterpriseCredit", "企业信用认证"), 

	  shareholderResolution("shareholderResolution", "股东会议决议认证");

	  private static final Map<String, String> lookup;
	  private String code;
	  private String desc;

	  private ProductEnum(String code, String desc)
	  {
	    this.code = code;
	    this.desc = desc;
	  }
	  public Integer getCodeInt() {
	    try {
	      return Integer.valueOf(Integer.parseInt(this.code)); } catch (Exception e) {
	    }
	    return null;
	  }

	  public String getCode() {
	    return this.code;
	  }

	  public void setCode(String code) {
	    this.code = code;
	  }

	  public String getDesc() {
	    return this.desc;
	  }

	  public void setDesc(String desc) {
	    this.desc = desc;
	  }
	  public static String getDescByCode(String code) {
	    return (String)lookup.get(code);
	  }

	  static
	  {
	    lookup = new HashMap<String,String>();

	    for (ProductEnum s : EnumSet.allOf(ProductEnum.class))
	      lookup.put(s.getCode(), s.getDesc());
	  }
	}