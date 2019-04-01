package org.apis.service.member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apis.dao.member.MemberRepository;
import org.apis.dao.member.UserSummaryRepository;
import org.apis.listener.event.LoginSuccessEvent;
import org.apis.listener.event.RegisterSucessEvent;
import org.domain.member.Member;
import org.domain.member.UserSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.util.StringUtil;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private UserSummaryRepository userSummaryRepository;
	
	@Autowired
	ApplicationContext applicationContext;
	
	/**
	 * 会员注册
	 * @param member 会员信息
	 * @param memberKind 0 个人 1 企业
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Member regist(Member member,int memberKind) throws Exception{
		Member saveMember = new Member();
		saveMember.setRegistTimeFrom(new Date());//注册开始时间
		
		if(StringUtils.isEmpty(member.getNickName())){
			throw new RuntimeException("用户名不能为空");
		}
		if(StringUtils.isEmpty(member.getPhone())){
			throw new RuntimeException("注册手机号不能为空");
		}
		if(StringUtils.isEmpty(member.getPassword())){
			throw new RuntimeException("用户未设置密码");
		}
		
		//校验用户名是否重复
		if(memberRepository.findByNickName(member.getNickName()) != null){
			throw new RuntimeException("该用户名已被使用");
		}
		if(memberRepository.findByPhone(member.getPhone())!=null){
			throw new RuntimeException("该手机号已被注册");
		}
		if(memberRepository.findByEmail(member.getEmail())!=null){
			throw new RuntimeException("该邮箱已被注册");
		}
		
		if(StringUtils.isNotEmpty(member.getPromotionId())){
			Member tgMember = memberRepository.findByPhone(member.getPromotionId());
			if(tgMember == null){
				throw new RuntimeException("推荐人不存在，请检查推荐码");
			}
			saveMember.setMemberIdZ(tgMember.getId());//推荐人
		}
		
		saveMember.setNickName(member.getNickName());
		saveMember.setPhone(member.getPhone());
		saveMember.setEmail(member.getPhone());
		saveMember.setAddress(member.getAddress());
		saveMember.setRealCd(StringUtil.generateShortUuid());
		saveMember.setPassword(StringUtil.generateEncryptPassword(member.getPassword(), saveMember.getRealCd()));
		saveMember.setPromotionId(member.getPhone());
		saveMember.setAuditType(8);
		saveMember.setStatus(1);
		saveMember.setLoginCount(0);
		saveMember.setMemberKind(memberKind);
		saveMember.setRegistTimeTo(new Date());
		saveMember.setRegistTime(new Date());
		//保存会员信息
		member = memberRepository.saveAndFlush(saveMember);
		//初始化会员投资汇总信息
		userSummaryRepository.saveAndFlush(new UserSummary(member));
		
		//发送短信通知
		
		//红包
		
		//积分
		applicationContext.publishEvent(new RegisterSucessEvent(member.getMemberId()));
		return member;
	}
	
	
	/**
	 * 用户登录
	 * @param mobile
	 * @param password
	 * @param lastLoginIp
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public Member login(String mobile,String password,String lastLoginIp) throws Exception{
		List<Member> members = memberRepository.findAllByPhone(mobile);
		if(members.size() > 1){
			throw new Exception("当前输入手机号匹配多个登录账户，请联系平台系统管理员。");
		}
		if(members.size() == 0){
			throw new Exception("请输入正确的用户名和密码");
		}
		
		Member m = members.get(0);
		
		if(m.getStatus() == Member.MEMBER_STATUS_LIMIT.intValue()){
			throw new Exception("该用户已被限制登录!");
		}
		
		if(StringUtils.isEmpty(m.getRealCd())){
			throw new Exception("用户信息RealCd域为空");
		}
		
		String encryptPassword = StringUtil.generateEncryptPassword(password, m.getRealCd());
		if(!encryptPassword.equalsIgnoreCase(m.getPassword())){
			throw new Exception("请输入正确的用户名和密码");
		}
		
		//判断是否是今日首次登录
		Date lastLoginTime = m.getLastLogin();
		boolean isFirtLoginToday = false;
		if(lastLoginTime == null){
			isFirtLoginToday = true;
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			isFirtLoginToday = !sdf.format(new Date()).equals(sdf.format(lastLoginTime));
		}
		m.setLastLoginIp(lastLoginIp);
		m.setLastLogin(new Date());
		m.setLastLoginFrom(new Date());
		m.setLastLoginTo(new Date());
		//增加登录次数
		m.setLoginCount(m.getLoginCount()+1);
		memberRepository.saveAndFlush(m);
		applicationContext.publishEvent(new LoginSuccessEvent(m, isFirtLoginToday));
		return m;
	}
}
