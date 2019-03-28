package org.background.controller.system;

import java.util.ArrayList;
import java.util.List;

import org.annotation.FunctionEx;
import org.annotation.MenuEx;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.business.system.ResourceService;
import org.business.system.RoleService;
import org.domain.system.Resource;
import org.domain.system.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.vo.ResponseMsg;
import org.vo.TreeNodeDto;

import pagination.PageRequest;
import pagination.PageResponse;

@RequestMapping("/system")
@Controller
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	
	@RequiresPermissions("p2p.permission.role.list")
    @MenuEx(code = "p2p.permission.role.list", name = "角色管理", parentCd = "p2p.system.profile", listSort = 50)
	@RequestMapping("/roleList")
	public String roleList(){
		return "system/role_list";
	}
	
	@RequiresPermissions("p2p.permission.role.list")
	@RequestMapping("/roleList/data")
	public @ResponseBody PageResponse<Role> queryRolesByPage(PageRequest request){
		return roleService.queryRolesByPage(request);
	}
	
	@RequiresPermissions("p2p.permission.role.edit")
	@FunctionEx(code="p2p.permission.role.edit",name="编辑角色",parentCd="p2p.permission.role.list")
	@RequestMapping("/roleEdit")
	public ModelAndView roleEdit(String roleId){
		ModelAndView mv = new ModelAndView("system/role_edit");
		if(StringUtils.isNotEmpty(roleId)){
			Role role = roleService.queryById(roleId);
			mv.addObject("role", role);
		}
		return mv;
	}
	
	@RequiresPermissions("p2p.permission.role.save")
	@FunctionEx(code="p2p.permission.role.save",name="保存角色",parentCd="p2p.permission.role.list")
	@RequestMapping("/roleSave")
	public String roleSaveOrUpdate(Role role,String ids){
		if(StringUtils.isEmpty(role.getId())){
			role.setId(null);
		}
		roleService.save(role, ids);
		return "system/role_list";
	}
	
	@RequiresPermissions("p2p.permission.role.disable")
	@FunctionEx(code="p2p.permission.role.disable",name="禁用角色",parentCd="p2p.permission.role.list")
	@RequestMapping("/roleDisable")
	public @ResponseBody ResponseMsg<String> disableRoles(String ids){
		logger.error("禁用角色：{}",ids);
		roleService.disable(StringUtils.isEmpty(ids)?null:ids.split(","));
		return new ResponseMsg<String>();
	}
	
	@RequiresPermissions("p2p.permission.role.enable")
	@FunctionEx(code="p2p.permission.role.enable",name="启用角色",parentCd="p2p.permission.role.list")
	@RequestMapping("/roleEnable")
	public @ResponseBody ResponseMsg<String> enableRoles(String ids){
		logger.error("启用角色：{}",ids);
		roleService.enable(StringUtils.isEmpty(ids)?null:ids.split(","));
		return new ResponseMsg<String>();
	}
	
	/**
     * 获取角色的具有的权限,没有选中的权限也会显示出来.
     **/
	@RequiresPermissions("p2p.permission.role.edit")
    @RequestMapping("/role/permissions")
    @ResponseBody
    public List<TreeNodeDto> rolePermission(String roleId){
    	List<TreeNodeDto> treeNodes=new ArrayList<TreeNodeDto>();
    	//所有角色树
    	List<Resource> resources=resourceService.findAll();
    	//已经分配的权限
    	List<Resource> grantedRes=new ArrayList<Resource>();
    	//指定了角色Id,则指定勾选的节点
    	if(StringUtils.isNotEmpty(roleId)){
    		grantedRes.addAll(resourceService.findByRoleId(roleId));
    	}
    	logger.debug("roleId={},权限数:{}",roleId,grantedRes.size());
    	for (Resource r : resources){  
    		treeNodes.add(r.geTreeNode(grantedRes.contains(r)));
		}
    	return treeNodes;
    }
}
