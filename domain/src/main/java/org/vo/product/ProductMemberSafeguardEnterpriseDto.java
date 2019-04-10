package org.vo.product;

import java.util.ArrayList;
import java.util.List;

import org.domain.member.Enterprise;
import org.domain.member.Member;
import org.domain.product.Attachment;
import org.domain.product.Product;
import org.domain.product.ProductItemType;
import org.domain.product.Safeguard;

public class ProductMemberSafeguardEnterpriseDto {
	private String borrowId;
	private String applyCd;
	private Product product;
	private Member member;
	private Safeguard safeguard;
	private Enterprise enterprise;
	private List<Attachment> attachmentList = new ArrayList<Attachment>();

	private List<ProductItemType> productItemType = new ArrayList<ProductItemType>();
	private String[] attachmentId;
	private String suborsave;
	private String frmuban;
	private String homePage;
	private String danbao;
	private String loginCd;
	private String[] authinfos;
	private String hasPledge;
	private List<ProductPledgeDto> productPledgeDtos = new ArrayList<ProductPledgeDto>();
	private String deletePledgeIds;

	public String[] getAuthinfos() {
		return this.authinfos;
	}

	public void setAuthinfos(String[] authinfos) {
		this.authinfos = authinfos;
	}

	public String getLoginCd() {
		return this.loginCd;
	}

	public void setLoginCd(String loginCd) {
		this.loginCd = loginCd;
	}

	public String[] getAttachmentId() {
		return this.attachmentId;
	}

	public void setAttachmentId(String[] attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getSuborsave() {
		return this.suborsave;
	}

	public void setSuborsave(String suborsave) {
		this.suborsave = suborsave;
	}

	public String getFrmuban() {
		return this.frmuban;
	}

	public void setFrmuban(String frmuban) {
		this.frmuban = frmuban;
	}

	public String getHomePage() {
		return this.homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	public String getDanbao() {
		return this.danbao;
	}

	public void setDanbao(String danbao) {
		this.danbao = danbao;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Safeguard getSafeguard() {
		return this.safeguard;
	}

	public void setSafeguard(Safeguard safeguard) {
		this.safeguard = safeguard;
	}

	public Enterprise getEnterprise() {
		return this.enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public List<Attachment> getAttachmentList() {
		return this.attachmentList;
	}

	public void setAttachmentList(List<Attachment> attachmentList) {
		this.attachmentList = attachmentList;
	}

	public List<ProductItemType> getProductItemType() {
		return this.productItemType;
	}

	public void setProductItemType(List<ProductItemType> productItemType) {
		this.productItemType = productItemType;
	}

	public String getApplyCd() {
		return this.applyCd;
	}

	public void setApplyCd(String applyCd) {
		this.applyCd = applyCd;
	}

	public String getBorrowId() {
		return this.borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public String getHasPledge() {
		return this.hasPledge;
	}

	public void setHasPledge(String hasPledge) {
		this.hasPledge = hasPledge;
	}

	public List<ProductPledgeDto> getProductPledgeDtos() {
		return this.productPledgeDtos;
	}

	public void setProductPledgeDtos(List<ProductPledgeDto> productPledgeDtos) {
		this.productPledgeDtos = productPledgeDtos;
	}

	public String getDeletePledgeIds() {
		return this.deletePledgeIds;
	}

	public void setDeletePledgeIds(String deletePledgeIds) {
		this.deletePledgeIds = deletePledgeIds;
	}
}
