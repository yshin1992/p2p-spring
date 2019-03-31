package org.apis.service.member;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apis.dao.member.MemberRepository;
import org.apis.dao.member.UserSummaryRepository;
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
	
}
