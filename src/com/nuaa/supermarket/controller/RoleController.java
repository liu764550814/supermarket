package com.nuaa.supermarket.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.nuaa.supermarket.pojo.SmbmsRole;
import com.nuaa.supermarket.pojo.SmbmsUser;
import com.nuaa.supermarket.service.RoleService;
import com.nuaa.supermarket.utils.Page;

/**
* @author 刘超明
* @version 创建时间：2019年11月1日 下午6:16:53
* 类说明
*/
@Controller
@RequestMapping("role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("rolelistNoCondition")
	@ResponseBody
	public String rolelistNoCondition(){
		
		
		System.out.println("rolelistNoCondition 已经运行。。。。。");
		List<SmbmsRole> roles = roleService.getAllRoleNoCondition();
		
		System.out.println("role.name = "+roles.get(0).getRolename());
		return JSONArray.toJSONString(roles);
	}
	
	@RequestMapping("rolelist")
	public String rolelist(Model model,@RequestParam(value = "pageIndex" , required = false)String pageIndex){
		
		System.out.println("rolelist运行");
		System.out.println("pageIndex = "+pageIndex);
		
		Page<SmbmsRole> page;
		if (pageIndex == null) {
			page = roleService.getAllRole(1);
		}else{
			page = roleService.getAllRole(Integer.valueOf(pageIndex));
		}
		
		System.out.println("5555555555");
		
		model.addAttribute("roleList", page.getList());
/*		model.addAttribute("roleList", page.getList());
		model.addAttribute("roleList", page.getList());
		model.addAttribute("roleList", page.getList());*/
		return "rolelist";
	}
	
	/**
	 * 
	    * @Title: roleadd
	    * @Description: 跳转到添加角色的页面
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("roleadd")
	public String roleadd(){	
		return "roleadd";
	}
	
	
	/**
	 * 
	    * @Title: saveaddrole
	    * @Description: 用来保存添加的role
	    * @param @param roleCode
	    * @param @param roleName
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("saveaddrole")
	public String saveaddrole(Model model,String roleCode,String roleName,HttpSession session){
		
		System.out.println("roleCode = "+roleCode+",roleName =" +roleName);
		//获得当前登陆的用户信息
		SmbmsUser user = (SmbmsUser) session.getAttribute("userSession");
		//执行添加操作并且得到返回值
		String msString = roleService.insertRole(user.getId(),roleCode, roleName);
		//设置返回值
		model.addAttribute("message", msString);
		
		return "roleadd";
	}
	
	@RequestMapping(value = "deleterole")
	@ResponseBody
	public String deleterole(@RequestParam(name="roleid",required = false)String roleid){
		HashMap<String,Object>  result = new HashMap<String,Object>();
		System.out.println("deleterole 运行 。。。。。roleid = "+roleid);
		
		//执行删除用户操作，并得到返回值
		String mString = roleService.deleteRole(Integer.valueOf(roleid));
		//设置返回信息
		result.put("delResult", mString);
		return  JSONArray.toJSONString(result);
	}
	
	/**
	 * 
	    * @Title: updateRole
	    * @Description: 跳转到角色修改页面
	    * @param @param model
	    * @param @param roleid
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("updateRole")
	public String updateRole(Model model,Integer roleid){
		
		System.out.println("updateRole已经运行 roleid = "+roleid);
		
		SmbmsRole role = roleService.updateRole(roleid);
		
		if (role != null) {
			model.addAttribute("role", role);
			return "rolemodify";
		}
		model.addAttribute("msg", "没查询到该角色，请刷新页面重试！");
		return "rolelist";
	}
	
	
	/**
	 * 
	    * @Title: saveupdaterole
	    * @Description: 保存修改的信息
	    * @param @param model
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("saveupdaterole")
	public String saveupdaterole(Model model,SmbmsRole role){
		System.out.println("rolename:"+role.getRolename());
		System.out.println("roleid:"+role.getId());
		System.out.println("rolecode:"+role.getRolecode());
		System.out.println("saveupdaterole已经运行 role = "+role.getId());
		//执行修改操作
		String mString = roleService.saveupdateRole(role);
		//设置回显信息
		model.addAttribute("msg",mString);
		
		return "rolemodify";
	}
	
}
