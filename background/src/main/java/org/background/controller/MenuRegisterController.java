package org.background.controller;

import org.annotation.MenuEx;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统菜单在此处生成和配置
 * 
 * @author yanshuai
 *
 */
@Controller
public class MenuRegisterController {

	@RequestMapping("/test.html")
	public void test(){
		
	}
	
	@RequiresPermissions("p2p.product")
	@MenuEx(name = "项目管理", code = "p2p.product", listSort = 10000)
	public void productManage() {
	}

	@RequiresPermissions("p2p.product.manager")
	@MenuEx(name = "项目管理", code = "p2p.product.manager", parentCd = "p2p.product", listSort = 100100)
	public void productMg() {

	}

	// 项目管理--债权转让
	@RequiresPermissions("p2p.product.credit")
	@MenuEx(code = "p2p.product.credit", name = "债权转让", parentCd = "p2p.product", listSort = 10200)
	public void product_credit() {
	}

	// 项目管理--借款申请
	@RequiresPermissions("p2p.product.apply")
	@MenuEx(code = "p2p.product.apply", name = "借款申请", parentCd = "p2p.product", listSort = 10300)
	public void product_apply() {
	}

	// /////////////////资金管理/////////////////////////////
	// 资金管理
	@RequiresPermissions("p2p.bankroll")
	@MenuEx(code = "p2p.bankroll", name = "资金管理", listSort = 20000)
	public void bankroll() {
	}

	// 资金管理--资金管理
	@RequiresPermissions("p2p.bankroll.manager")
	@MenuEx(code = "p2p.bankroll.manager", name = "资金管理", parentCd = "p2p.bankroll", listSort = 20100)
	public void bankroll_manager() {
	}

	// /////////////////客户管理/////////////////////////////
	// 客户管理
	@RequiresPermissions("p2p.customer")
	@MenuEx(code = "p2p.customer", name = "客户管理", listSort = 30000)
	public void customer() {
	}

	// 客户管理--客户管理
	@RequiresPermissions("p2p.customer.manager")
	@MenuEx(code = "p2p.customer.manager", name = "客户管理", parentCd = "p2p.customer", listSort = 30100)
	public void customer_manager() {
	}

	// /////////////////营销管理/////////////////////////////
	// 营销管理
	@RequiresPermissions("p2p.market")
	@MenuEx(code = "p2p.market", name = "营销管理", listSort = 40000)
	public void marker() {
	}

	// 营销管理--广告管理
	@RequiresPermissions("p2p.market.ad")
	@MenuEx(code = "p2p.market.ad", name = "广告管理", parentCd = "p2p.market", listSort = 40100)
	public void marker_ad() {
	}

	// 营销管理--红包管理
	@RequiresPermissions("p2p.market.red")
	@MenuEx(code = "p2p.market.red", name = "红包管理", parentCd = "p2p.market", listSort = 40200)
	public void marker_red() {
	}

	// // 营销管理--经纪人管理
	// @MenuEx(code = "p2p.market.broker", name = "经纪人管理", parentCd =
	// "p2p.market", listSort = 40300)
	// public void marker_broker() {
	// }

	// 营销管理--信息营销
	@RequiresPermissions("p2p.market.sms")
	@MenuEx(code = "p2p.market.sms", name = "信息营销", parentCd = "p2p.market", listSort = 40400)
	public void marker_sms() {
	}

	// 营销管理--好友管理
	@RequiresPermissions("p2p.market.friends")
	@MenuEx(code = "p2p.market.friends", name = "好友管理", parentCd = "p2p.market", listSort = 40500)
	public void marker_friends() {
	}

	// 营销管理--积分管理
	@RequiresPermissions("p2p.market.integral")
	@MenuEx(code = "p2p.market.integral", name = "积分管理", parentCd = "p2p.market", listSort = 40600)
	public void marker_integral() {
	}

	// /////////////////内容管理/////////////////////////////
	// 内容管理
	@RequiresPermissions("p2p.cms")
	@MenuEx(code = "p2p.cms", name = "内容管理", listSort = 50000)
	public void cms() {
	}

	// 内容管理--内容管理
	@RequiresPermissions("p2p.cms.manager")
	@MenuEx(code = "p2p.cms.manager", name = "内容管理", parentCd = "p2p.cms", listSort = 50100)
	public void cms_manager() {
	}

	// /////////////////系统管理/////////////////////////////
	// 系统管理
	@RequiresPermissions("p2p.system")
	@MenuEx(code = "p2p.system", name = "系统管理", listSort = 60000)
	public void system() {
	}

	// 系统管理--系统管理
	@RequiresPermissions("p2p.system.profile")
	@MenuEx(code = "p2p.system.profile", name = "系统管理", parentCd = "p2p.system", listSort = 60100)
	public void system_profile() {
	}
}
