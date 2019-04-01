package org.apis.controller.member;

import org.apache.commons.lang3.StringUtils;
import org.apis.service.CacheUtil;
import org.apis.service.common.SmsShakeValidator;
import org.apis.service.common.VisitDataService;
import org.enums.CategoryAttrEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vo.ResponseMsg;
import org.vo.sms.VerifyCode;

/**
 * 短信验证码管理
 * @author yshin1992
 *
 */
@Controller
public class SmsManagerController {

	private static final Logger logger = LoggerFactory.getLogger(SmsManagerController.class);
	
	@Autowired
	private VisitDataService visitDataService;
	
	@Autowired
	private SmsShakeValidator smsShakeValidator;
	
	/**
	 * 注册发送手机验证码
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findSMSCode", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMsg<String> sendSms(String mobile, String module, String visitIp, @RequestParam(required = false) String memberId){
		logger.info("发送验证码,接收参数:module={},mobile={},memberId={},visitIp={}", module, mobile, memberId, visitIp);
		ResponseMsg<String> msg = new ResponseMsg<String>();
		if (StringUtils.isEmpty(visitIp)) {
			msg.failure("请求发送手机验证码时请求IP不能为空。");
		} else if (StringUtils.isEmpty(mobile) && StringUtils.isEmpty(memberId)) {
			msg.failure("请求发送手机验证码时请求手机号或会员不能为空。");
		} else if (StringUtils.isEmpty(module)) {
			msg.failure("请求发送手机验证码时请求节点不能为空。");
		} else {
			try{
				// 验证请求是否有效
				// 验证两部分，一个是手机号发送的限制，另外一个是IP发送的限制，防止有些用户非法进行尝试不同的IP或手机来进行手机验证码的发送
				String limitIpFrequency = CacheUtil.getProperty(CategoryAttrEnum.PLARTFORMVISITLIMITIPMESSAGE.getAttrCd());
				String limitVisitDataFrequency = CacheUtil.getProperty(CategoryAttrEnum.PLARTFORMVISITLIMITPHONEMESSAGE.getAttrCd());
				if (StringUtils.isEmpty(limitIpFrequency)) {
					throw new RuntimeException( "'每个IP在1小时内允许发送短信条数'配置项获取值为空。");
				} else if (StringUtils.isEmpty(limitVisitDataFrequency)) {
					throw new RuntimeException( "'每个手机号在1小时内允许发送短信条数'配置项获取值为空。");
				}
				
				Long limitIpFrequencyInt = Long.valueOf(limitIpFrequency);
				Long limitVisitDataFrequencyInt = Long.valueOf(limitVisitDataFrequency);
				
				Integer ipCount = visitDataService.findCountInOneHourByIp(visitIp);
				if(ipCount >= limitIpFrequencyInt){
					throw new RuntimeException( "当前IP短信请求达到'每个IP在1小时内允许发送短信条数'的最大限制，请稍后再试。");
				}
				
				String userData = StringUtils.isEmpty(mobile)?memberId:mobile;
				Integer userDataCount = visitDataService.findCountInOneHourByUserData(userData);
				if(userDataCount >= limitVisitDataFrequencyInt){
					throw new RuntimeException( "当前会员或手机号短信发送达到'每个手机号在1小时内允许发送短信条数'的最大限制，请稍后再试。");
				}
				
				// 发送验证码
				VerifyCode code = smsShakeValidator.genSmsValidateCode(memberId, mobile, module);
				
				//保存发送验证码的请求信息
				visitDataService.saveVisitData(visitIp, module,
						StringUtils.isNotEmpty(mobile) ? mobile : StringUtils.isNotEmpty(memberId) ? memberId : "手机和会员编号均为空。");
				logger.error("发送验证码成功:mobile={},code={}", mobile, code.getVerifyCode());
			}catch(Exception e){
				msg.failure(e.getMessage());
				logger.error("发送验证码失败，{}",e);
			}
		}
		return msg;
	}
	
	
	/**
	 * 验证短信验证码
	 * @param mobile
	 * @param mCheckCode
	 * @param module
	 * @param memberId
	 * @return
	 */
	@RequestMapping(value = "/validateSMSCode")
	@ResponseBody
	public ResponseMsg<String> checkMobileCode(@RequestParam String mobile, @RequestParam String mCheckCode,
			 @RequestParam(required = false) String memberId){
		ResponseMsg<String> msg = new ResponseMsg<String>();
		boolean valid = smsShakeValidator.checkSmsValidateCode(memberId, mobile, mCheckCode);
		logger.debug("check mobile:" + mobile + "code:" + mCheckCode);
		if (valid) {
			msg.success("验证码正确");
		} else {
			msg.failure("验证码错误");
		}
		return msg;
	}
}
