package front;

import java.util.Date;

import org.domain.member.Member;
import org.domain.member.MemberOperationLog;
import org.p2p.constants.Const;
import org.p2p.service.member.MemberOperationLogService;
import org.p2p.service.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SysOperationLogServiceTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		MemberOperationLogService service = ctx.getBean(MemberOperationLogService.class);
		MemberOperationLog log = new MemberOperationLog();
		log.init();
		log.setTargetObject(Const.TARGET_LOGIN);
		log.setOperateDate(new Date());
		log.setOperateResult(Const.OPERATION_SUCCESS);
		log.setSessionId("sssss");
		log.setIp("127.0.0.1");
		log.setUrl("127.0.0.1");
		
		MemberService memberService = ctx.getBean(MemberService.class);
		Member member = memberService.queryMemberByPhone("15852087981");
		log.setMember(member);
		service.save(log);
	}

}
