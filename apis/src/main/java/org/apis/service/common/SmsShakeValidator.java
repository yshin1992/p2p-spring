package org.apis.service.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apis.dao.member.MemberRepository;
import org.apis.service.CacheUtil;
import org.domain.member.Member;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vo.sms.SmsMemberVo;
import org.vo.sms.TemporaryStore;
import org.vo.sms.VerifyCode;

@Service
public class SmsShakeValidator {

	private int codeLength = 6;

	private int storeTime = 300;

	private String[] codeSequence = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
	
	private static final Logger logger = LoggerFactory.getLogger(SmsShakeValidator.class);
	
	@Autowired
	private MemberRepository memberRepository;
	/**
	 * 生成短信验证码的接口
	 * @param userId
	 * @param mobile
	 * @param module
	 * @return
	 */
	public VerifyCode genSmsValidateCode(String userId, String mobile, String module) {
		String randomCode = genRandomCode(null);
		VerifyCode orginal = TemporaryStore.getInstance().getVerifyCode(userId + mobile);
		String seqCode="";
		if (orginal != null) {
			seqCode = genSequenceCode(orginal.getSeqCode());
			while (randomCode.equals(orginal.getVerifyCode())) {
				randomCode = genRandomCode(null);
			}
		} else {
			seqCode = genSequenceCode(null);
		}
		VerifyCode code = new VerifyCode();
		code.setSeqCode(seqCode);
		code.setVerifyCode(randomCode);
		
		
		Member member = null;
		if (StringUtils.isNotEmpty(userId)) {
			member = memberRepository.findOne(userId);
			if (member != null && StringUtils.isEmpty(mobile)) {
				mobile = member.getPhone();
			}
		}
		
		try{
			Map<String, String> vars = new HashMap<String, String>();
			vars.put("mCheckCode", code.getVerifyCode());
			vars.put("mCheckCodeLifeTime", String.valueOf(storeTime / 60)); //设置的时间其实就是5分钟
			vars.put("siteTitle", CacheUtil.getConfig("siteTitle"));
			if (member != null) {
				vars.put("realCd", member.getPhone());
			}
			else{
				vars.put("realCd",mobile);
			}
			
			List<SmsMemberVo> members=new ArrayList<SmsMemberVo>();
			if(member!=null){
				members.add(new SmsMemberVo(member.getRealCd(), member.getRealNm(), member.getPhone(), member.getEmail()));
			}else {
				members.add(new SmsMemberVo("", "", mobile, ""));	
			}
			
			//TODO 调用接口发送短信验证码
			
			String storeKey = getStoreKey(userId, mobile);//获取缓存在内存中的KEY
			TemporaryStore.getInstance().storeVerifyCode(storeKey, storeTime, TimeUnit.SECONDS, code);
			logger.debug("发送验证码成功:store code:{},seqCode:{}",seqCode,randomCode);
		}catch(Exception e){
			logger.error("发送验证码失败{}", e);
		}
		
		return code;
	}
	//生成序列号
	public String genSequenceCode(String orginal) {
		StringBuilder seqCode = new StringBuilder(3);
		Random random = new Random();

		int charIdx = random.nextInt(25);
		char seqChar = (char) (charIdx + 65);

		String seqNumber = genRandomCode(2);

		if (orginal != null && orginal.length() == 3) {
			String orginalNumber = orginal.substring(1);
			while (seqChar == orginal.charAt(0)) {
				charIdx = random.nextInt(25);
				seqChar = (char) (charIdx + 65);
			}
			while (seqNumber.equals(orginalNumber)) {
				seqNumber = genRandomCode(2);
			}
		}
		seqCode.append(seqChar);
		seqCode.append(seqNumber);
		return seqCode.toString();
	}
	/**
	 * 生成指定长度的随机码
	 * @param length
	 * @return
	 */
	public String genRandomCode(Integer length) {
		int len = length != null ? length : codeLength;
		StringBuilder buf = new StringBuilder(len);
		Random random = new Random();
		for (int i = 0; i < len; i++) {
			int idx = random.nextInt(9);
			buf.append(codeSequence[idx]);
			if (i > 0) {
				boolean repeatMark = false;
				do {
					for (int j = i - 1; j >= 0; j--) {
						if (buf.charAt(i) == buf.charAt(j)) {
							repeatMark = true;
							idx = random.nextInt(9);
							buf.setCharAt(i, codeSequence[idx].charAt(0));
							break;
						}
						repeatMark = false;
					}
				} while (repeatMark);
			}
		}
		String randomCode = buf.toString();
		return randomCode;
	}
	
	/**
	 * 生成指定格式的Key 形如: userId:xxx|mobile:xxxxxxxx
	 * @param userId
	 * @param mobile
	 * @return
	 */
	private String getStoreKey(String userId, String mobile) {
		return new StringBuilder("userId:").append(userId).append("|").append("mobile:").append(mobile).toString();
	}
	
	/**
	 * 校验用户的短信验证码
	 * @param userId
	 * @param mobile
	 * @param code
	 * @return
	 */
	public boolean checkSmsValidateCode(String userId, String mobile, String code) {
		String storeKey = getStoreKey(userId, mobile);
		VerifyCode verifyCode = TemporaryStore.getInstance().getVerifyCode(storeKey);
		if (verifyCode != null && code.equals(verifyCode.getVerifyCode())) {
			return true;
		} else {
			return false;
		}
	}
}
