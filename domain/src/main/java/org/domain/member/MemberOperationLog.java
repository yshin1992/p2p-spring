package org.domain.member;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;

import org.domain.AbstractEntity;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.BrowserType;
import eu.bitwalker.useragentutils.DeviceType;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.RenderingEngine;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * 会员操作记录实体类
 * @author yanshuai
 *
 */
@Entity
@Table(name="member_operation_log")
public class MemberOperationLog extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6609081606577028472L;

	public MemberOperationLog(){
		
	}
	
	public MemberOperationLog(HttpServletRequest request){
		this.setAcceptLanguage(request.getHeader("accept-language"));
		this.setUserAgent(request.getHeader("user-agent"));
	}

	public MemberOperationLog(HttpServletRequest request,String targetObject,Member member,short operateResult){
		this(request);
		this.init();
		this.setTargetObject(targetObject);
		this.setOperateDate(new Date());
		this.setOperateResult(operateResult);
		this.setSessionId(request.getSession().getId());
		this.setIp(request.getRemoteAddr());
		this.setUrl(request.getRequestURL().toString());
		this.setMember(member);
	}

	@Id
	@GenericGenerator(name="SystemUUID",strategy="uuid")
	@GeneratedValue(generator="SystemUUID")
	@Column
	private String logId;

	@Column
	private String url;

	@Column(length=256)
	private String targetObject;

	@Column()
	private Short operateResult;

	@Column
	private String sessionId;

	@Column
	private String ip;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date operateDate;

	@ManyToOne(optional=false,fetch=FetchType.EAGER)
	@JoinColumn(name="memberId")
	private Member member;

	/**
	 * 代理信息
	 */
	@Column(length=512)
	private String userAgent;

	/**
	 * 语言
	 */
	@Column(length=256)
	private String acceptLanguage;

	/**
	 * 浏览器分类
	 */
	@Column
	@Enumerated(EnumType.STRING)
	private Browser browser;

	/**
	 * 浏览器名称
	 */
	@Column
	private String browserName;

	/**
	 * 浏览器版本
	 */
	@Column
	private String browserVersion;

	/**
	 * 浏览器类型：WEB_BROWSER MOBILE_BROWSER
	 */
	@Column
	@Enumerated(EnumType.STRING)
	private BrowserType browserType;

	/**
	 * 浏览器内核
	 */
	@Column
	@Enumerated(EnumType.STRING)
	private RenderingEngine renderingEngine;

	/**
	 * 操作系统分类
	 */
	@Column
	@Enumerated(EnumType.STRING)
	private OperatingSystem operatingSystem;

	/**
	 * 操作系统名称
	 */
	@Column
	private String systemName;

	/**
	 * 设备类型：COMPUTER MOBILE TABLET
	 */
	@Column
	@Enumerated(EnumType.STRING)
	private DeviceType deviceType;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTargetObject() {
		return targetObject;
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
		if (userAgent != null) {
			UserAgent agent = new UserAgent(userAgent);
			if (agent != null) {
				Browser browser = agent.getBrowser();
				this.browser = browser.getGroup();
				this.browserVersion = agent.getBrowserVersion().getVersion();
				this.browserType = browser.getBrowserType();
				this.browserName = browser.getName();
				this.renderingEngine = browser.getRenderingEngine();
			}
			OperatingSystem operatingSystem = agent.getOperatingSystem();
			if (operatingSystem != null) {
				this.operatingSystem = operatingSystem.getGroup();
				this.systemName = operatingSystem.getName();
				this.deviceType = operatingSystem.getDeviceType();
			}
		}
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public String getAcceptLanguage() {
		return acceptLanguage;
	}

	public void setAcceptLanguage(String acceptLanguage) {
		this.acceptLanguage = acceptLanguage;
	}

	public Browser getBrowser() {
		return browser;
	}

	public String getBrowserName() {
		return browserName;
	}

	public BrowserType getBrowserType() {
		return browserType;
	}

	public RenderingEngine getRenderingEngine() {
		return renderingEngine;
	}

	public OperatingSystem getOperatingSystem() {
		return operatingSystem;
	}

	public String getSystemName() {
		return systemName;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public Short getOperateResult() {
		return operateResult;
	}

	public void setOperateResult(Short operateResult) {
		this.operateResult = operateResult;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setBrowser(Browser browser) {
		this.browser = browser;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public void setBrowserType(BrowserType browserType) {
		this.browserType = browserType;
	}

	public void setRenderingEngine(RenderingEngine renderingEngine) {
		this.renderingEngine = renderingEngine;
	}

	public void setOperatingSystem(OperatingSystem operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	@Override
	public String toString() {
		return "SystemOperateLog [logId=" + logId + ", url=" + url
				+ ", targetObject=" + targetObject + ", operateResult="
				+ operateResult + ", sessionId=" + sessionId + ", ip=" + ip
				+ ", operateDate=" + operateDate + ", member=" + member
				+ ", userAgent=" + userAgent + ", acceptLanguage="
				+ acceptLanguage + ", browser=" + browser + ", browserName="
				+ browserName + ", browserVersion=" + browserVersion
				+ ", browserType=" + browserType + ", renderingEngine="
				+ renderingEngine + ", operatingSystem=" + operatingSystem
				+ ", systemName=" + systemName + ", deviceType=" + deviceType
				+ "]";
	}

	@Override
	public void setId(String id) {
		this.logId = id;
	}

	@Override
	public String getId() {
		return this.logId;
	}

}
