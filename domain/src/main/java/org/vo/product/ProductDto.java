package org.vo.product;

import java.io.Serializable;
import java.util.Date;

public class ProductDto implements Serializable {

    private static final long serialVersionUID = -1332338582282334246L;
    private String productId;
    private String productCd;
    private String productNm;

    private String status;
    // 上线时间（开始）
    private Date startTime;
    // 上线时间（结束）
    private Date endTime;
    // 项目申请搜索条件
    private String applyKeywords;
    // 产品状态
    private Integer productStatus;
    // 标种名称
    private Integer tenderKind;
    // 还款方式
    private Integer repayWay;
    // 项目查询搜索条件
    private String selectKeywords;

    // 借款记录 1 代表借款记录查询
    private int borrowingRecord = 0;
    private String memberId;

    private String joinStatus;
    // 错误信息
    private String errorMsg;
    // 保存还是提交
    private String suborsave;
    //审核意见
    private String auditOpinion;
    //审批结果
    private String checkStatus;
    //驳回到的状态
    private String pstatus;
    //当前操作人员账号
    private String loginCd;
    //当前操作员id
    private String loginId;
    //撤标原因
    private String removeReason;
    //撤标备注
    private String removeRemark;




    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getRemoveReason() {
        return removeReason;
    }

    public void setRemoveReason(String removeReason) {
        this.removeReason = removeReason;
    }

    public String getRemoveRemark() {
        return removeRemark;
    }

    public void setRemoveRemark(String removeRemark) {
        this.removeRemark = removeRemark;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    public String getLoginCd() {
        return loginCd;
    }

    public void setLoginCd(String loginCd) {
        this.loginCd = loginCd;
    }

    public String getSuborsave() {
        return suborsave;
    }

    public void setSuborsave(String suborsave) {
        this.suborsave = suborsave;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(String joinStatus) {
        this.joinStatus = joinStatus;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getTenderKind() {
        return tenderKind;
    }

    public void setTenderKind(Integer tenderKind) {
        this.tenderKind = tenderKind;
    }

    public Integer getRepayWay() {
        return repayWay;
    }

    public void setRepayWay(Integer repayWay) {
        this.repayWay = repayWay;
    }

    public String getSelectKeywords() {
        return selectKeywords;
    }

    public void setSelectKeywords(String selectKeywords) {
        this.selectKeywords = selectKeywords;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getApplyKeywords() {
        return applyKeywords;
    }

    public void setApplyKeywords(String applyKeywords) {
        this.applyKeywords = applyKeywords;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public int getBorrowingRecord() {
        return borrowingRecord;
    }

    public void setBorrowingRecord(int borrowingRecord) {
        this.borrowingRecord = borrowingRecord;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
