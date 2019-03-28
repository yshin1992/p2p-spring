package org.background.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.annotation.FunctionEx;
import org.annotation.MenuEx;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.business.system.ResourceService;
import org.business.system.RoleService;
import org.business.system.UserService;
import org.domain.system.Resource;
import org.domain.system.Role;
import org.domain.system.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.vo.TreeNodeDto;

import pagination.PageRequest;
import pagination.PageResponse;

@Controller
public class UserController {

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "system/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(WebRequest request, ModelMap model) {
		Object object = request.getAttribute("shiroLoginFailure",
				WebRequest.SCOPE_REQUEST);
		if (object != null) {
			model.put("error", "登录提交失败:账号或密码错误");
			return new ModelAndView("system/login", model);
		} else {
			return new ModelAndView("hello");
		}
	}

	@RequestMapping(value = "/index")
	public String index() {
		return "system/index";
	}

	@RequestMapping(value = "/left")
	public ModelAndView left(String resourceCd) {
		ModelAndView mv = new ModelAndView("system/left");
		if (null != resourceCd) {
			Resource resource = resourceService.findResourceByCd(resourceCd);
			if (null != resource) {
				List<Resource> childs = resource.getChilds();
				mv.addObject("resources", childs);
			}
		}
		return mv;
	}

	@RequestMapping("/header")
	public String header() {
		return "system/header";
	}

	@RequestMapping("/main")
	public String main() {
		return "system/main";
	}

	@RequiresPermissions("p2p.permission.user.list")
	@MenuEx(code = "p2p.permission.user.list", name = "用户管理", parentCd = "p2p.system.profile", listSort = 60)
	@RequestMapping(value = "/system/userlist")
	public String userList() {
		return "system/user_list";
	}

	@RequiresPermissions("p2p.permission.user.list")
	@RequestMapping(value = "/system/userlist/data")
	@ResponseBody
	public PageResponse<User> queryUsersByPage(PageRequest request) {
		return userService.queryByPage(request);
	}

	@RequiresPermissions("p2p.permission.user.edit")
	@FunctionEx(code = "p2p.permission.user.edit", name = "进入编辑或新增用户", parentCd = "p2p.permission.user.list")
	@RequestMapping("/system/useredit")
	public ModelAndView userEdit(String userId) {
		ModelAndView mv = new ModelAndView("system/user_edit");
		if (StringUtils.isNotEmpty(userId)) {
			User user = userService.queryById(userId);
			mv.addObject("user", user);
		}
		return mv;
	}

	@RequiresPermissions("p2p.permission.user.edit")
	@RequestMapping("/system/user/roles")
	@ResponseBody
	public List<TreeNodeDto> userRole(String userId) {
		List<TreeNodeDto> treeNodes = new ArrayList<TreeNodeDto>();
		// 所有有效的角色
		List<Role> roles = roleService.queryAll();
		// 已经分配的角色
		List<Role> grantedRole = new ArrayList<Role>();
		// 指定了用户Id,则指定勾选的节点
		if (StringUtils.isNotEmpty(userId)) {
			grantedRole.addAll(roleService.queryByUserId(userId));
		}
		for (Role r : roles) {
			treeNodes.add(r.geTreeNode(grantedRole.contains(r)));
		}
		return treeNodes;
	}

	@RequiresPermissions("p2p.permission.user.save")
	@FunctionEx(code = "p2p.permission.user.save", name = "保存编辑或新增用户", parentCd = "p2p.permission.user.list")
	@RequestMapping("/system/usersave")
	public String userSave(User user,String ids) {
		if(StringUtils.isEmpty(user.getUserId())){
			user.setUserId(null);
		}
		userService.save(user, ids);
		return "system/user_list";
	}

}
