package org.background.controller.market;

import javax.servlet.http.HttpSession;

import org.annotation.FunctionEx;
import org.annotation.MenuEx;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.business.market.IntegralSetService;
import org.business.util.CacheUtil;
import org.domain.system.User;
import org.enums.CategoryAttrEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.vo.IntegralSetDto;
import org.vo.ResponseMsg;

/**
 * 积分管理
 * @author yanshuai
 *
 */
@Controller
@RequestMapping("/market")
public class MarketIntegralController {

	private static final Logger logger = LoggerFactory.getLogger(MarketIntegralController.class);
	
	@Autowired
	private IntegralSetService integralSetService;
	
	@Autowired
	private CacheUtil cacheUtil;
	
	@RequiresPermissions("p2p.market.integral.setting")
	@MenuEx(code = "p2p.market.integral.setting", name = "积分设置", parentCd = "p2p.market.integral", listSort = 40601)
	@RequestMapping("/integralset")
	public ModelAndView integralSet(IntegralSetDto dto) {
		ModelAndView mv = new ModelAndView("market/integralset");
		if (StringUtils.isEmpty(CacheUtil.getConfig(CategoryAttrEnum.INTEGRAL_ISUSEINTEGRAL.getAttrCd()))) {
			throw new RuntimeException("未初始化积分设置相关配置。");
		}
		// 获取显示
		dto.setIsUseIntegral(CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_ISUSEINTEGRAL.getAttrCd(), 0));//是否启用积分
		dto.setRegisterGiveIntegral(CacheUtil.getInt(
				CategoryAttrEnum.INTEGRAL_REGISTERGIVEINTEGRAL.getAttrCd(), 0));//注册获取积分
		dto.setLoginGiveIntegral(CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_LOGINGIVEINTEGRAL.getAttrCd(), 0));//登录获取积分
		dto.setRecommendFriendsGiveIntegral(CacheUtil.getInt(
				CategoryAttrEnum.INTEGRAL_RECOMMENDFRIENDSGIVEINTEGRAL.getAttrCd(), 0));//推荐好友获得积分
		dto.setFriendsInvestGiveIntegral(CacheUtil.getInt(
				CategoryAttrEnum.INTEGRAL_FRIENDSINVESTGIVEINTEGRAL.getAttrCd(), 0));//好友投资 推荐人获取积分
		dto.setInvestGiveIntegral(CacheUtil.getInt(CategoryAttrEnum.INTEGRAL_INVESTGIVEINTEGRAL.getAttrCd(), 0));//投资获得积分
		dto.setMaxInvestGiveIntegral(CacheUtil.getInt(
				CategoryAttrEnum.INTEGRAL_MAXINVESTGIVEINTEGRAL.getAttrCd(), 0));//用户投资大于过往单次投资金额时获得积分
		mv.addObject("item", dto);
		logger.debug("积分设置参数显示=====进入显示界面，参数值：{}", dto);
		return mv;
	}
	
	@RequiresPermissions("p2p.market.integral.setting.save")
	@FunctionEx(code = "p2p.market.integral.setting.save", name = "积分设置保存", parentCd = "p2p.market.integral.setting")
	@RequestMapping(value="/integralsave")
	@ResponseBody
	public ResponseMsg<String> integralSave(IntegralSetDto dto,HttpSession session){
		ResponseMsg<String> resMsg = new ResponseMsg<String>();
		try{
			User user = (User)session.getAttribute("curUser");
			integralSetService.save(dto, user);
			//保存成功后刷新cache
			cacheUtil.refresh();
			
		}catch(Exception e){
			resMsg.failure(e.getMessage());
			logger.error("保存积分设置出现异常,{}",e);
		}
		return resMsg;
	}
	
}
