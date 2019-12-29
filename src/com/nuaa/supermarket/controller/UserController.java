package com.nuaa.supermarket.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.nuaa.supermarket.pojo.QueryUser;
import com.nuaa.supermarket.pojo.SmbmsRole;
import com.nuaa.supermarket.pojo.SmbmsUser;
import com.nuaa.supermarket.pojo.UserAndRole;
import com.nuaa.supermarket.pojo.UserView;
import com.nuaa.supermarket.service.RoleService;
import com.nuaa.supermarket.service.UserService;
import com.nuaa.supermarket.utils.Page;

/**
* @author 刘超明
* @version 创建时间：2019年11月1日 上午9:37:59
* 类说明
*/
@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	
	/**
	 * 
	    * @Title: userlist
	    * @Description: 用户列表方法
	    * @param @param model
	    * @param @param user
	    * @param @param pageIndex
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("userlist")
	public String userlist(Model model,QueryUser user,@RequestParam(value ="pageIndex" ,required = false) String pageIndex){
		System.out.println("userlist 已经运行。。。。。。。。。。");
		
		System.out.println("user.name = "+user.getQueryname()+", user.role = "+user.getQueryUserRole()+", pageIndex = "+pageIndex);
		
		//无查询条件获取角色列表
		List<SmbmsRole> rolelist = roleService.getAllRoleNoCondition();
		
		
		//设置limit的开始查询的起始数据位置
		if (pageIndex != null) {
			System.out.println("11111111");
			user.setPage(Integer.valueOf(pageIndex));
		}
		
		//调用service的查询
		Page<UserAndRole> page = userService.getUserByCondition(user);
		
		//计算总分页
		int totalpage = 0;
		if (page.getTotal()%10 == 0) {
			totalpage = page.getTotal()/10;
		}else{
			totalpage = page.getTotal()/10 + 1;
		}
		
		//计算年龄
		List<UserAndRole> templist = page.getList();//.get(0).getBirthday().getTime();
		Date date  = new Date();
		for (UserAndRole smbmsUser : templist) {
			smbmsUser.setAge(date.getYear() - smbmsUser.getBirthday().getYear());
		}
		 
		
		System.err.println("total count = "+page.getTotal());
		
		//设置jsp回显内容
		model.addAttribute("userList", page.getList());
		model.addAttribute("totalCount", page.getTotal());
		model.addAttribute("currentPageNo", page.getPage());
		model.addAttribute("totalPageCount", totalpage);
		model.addAttribute("roleList", rolelist);
		model.addAttribute("queryUserRole", user.getQueryUserRole());
		model.addAttribute("queryUserName", user.getQueryname());
		return "userlist";
	}
	
	
	
	@RequestMapping("updatepass")
	public String updatepass() {
		return "pwdmodify";
	}
	
	/**
	 * 
	    * @Title: checkoldpass
	    * @Description: 修改密码的时候，js访问的方法，判断密码是否正确
	    * @param @param oldpw
	    * @param @param session
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping(value = "checkoldpass")
	@ResponseBody
	public String checkoldpass(@RequestParam(name="oldpw",required = false)String oldpw,HttpSession session){
		System.out.println("checkoldpass 已经运行。。。。。。。。");
		System.out.println("checkpass 已经运行  old = "+oldpw);
		HashMap<String,Object>  result = new HashMap<String,Object>();
		//登陆人的id
		SmbmsUser user= (SmbmsUser) session.getAttribute("userSession");
		System.out.println("user.name = "+user.getUsername()+", user.pw = "+user.getUserpassword());
		if(user==null){
			result.put("result","sessionerror");
		}else if(oldpw.equals("")){ //如果旧密码为空
			System.out.println("傳入的参数为空------ error");
			result.put("result","error");
		} else if(!user.getUserpassword().equals(oldpw)){//如果输入的旧密码与旧密码不同
			result.put("result","false");
		}else{
			result.put("result","true");
		}
		return  JSONArray.toJSONString(result);
	}
	
	
	/**
	 * 
	    * @Title: savepass
	    * @Description: 保存密码
	    * @param @param model
	    * @param @param newpassword
	    * @param @param session
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("savepass")
	public String savepass(Model model,String newpassword,HttpSession session){
		//得到保存在session里的user的数据
		SmbmsUser user= (SmbmsUser) session.getAttribute("userSession");

		System.out.println("newpassword = "+newpassword);
		
		//更改密码
		String mString = userService.updatePassWord(newpassword, (SmbmsUser) session.getAttribute("userSession"));
		
		//更改session里面的user值
		if (mString.equals("密码设置成功")) {
			System.out.println("controller 设置成功");
			SmbmsUser tempuser = (SmbmsUser) session.getAttribute("userSession");
			tempuser.setUserpassword(newpassword);
			session.setAttribute("userSession", tempuser);
		}
		//设置回显信息
		model.addAttribute("message", mString);
		
		return "pwdmodify";
	}
	
	
	
	/**
	 * 
	    * @Title: adduser
	    * @Description: 跳转到添加用户的页面
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("adduser")
	public String adduser(){
		return "useradd";
	}
	
	/**
	 * 
	    * @Title: ucexist
	    * @Description: 查询新添加的用户账号是否存在
	    * @param @param userCode
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("ucexist")
	@ResponseBody
	public String ucexist(String userCode){
		System.out.println("ucexist 已经运行 userCode = "+userCode);
		HashMap<String, Object> result =new HashMap<String, Object>();
		//执行查询
		int count = userService.getCountByCondition(userCode);
		if (count == 0) {
			result.put("userCode", "noexist");
		}else{
			result.put("userCode", "exist");
		}
		
		return JSONArray.toJSONString(result);
	}
	
	/**
	 * 
	    * @Title: view
	    * @Description: 查看用户信息
	    * @param @param uid
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("view")
	@ResponseBody
	public String view(Integer uid){
		
		System.out.println("view 已经运行。。。。。uid = "+uid);
		//获得要查看的用户
		UserView userView = userService.selectOneById((long)uid);
		//判断有没有取到数据
		if (userView == null) {
			return JSONArray.toJSONString("nodata");
		}else{
			//把出生日期转换一下
			SimpleDateFormat dFormat =new SimpleDateFormat("yyyy-MM-dd");
			userView.setSbirthday(dFormat.format(userView.getBirthday()));
			System.out.println("user.birthday = "+userView.getSbirthday());
			
			return JSONArray.toJSONString(userView);
		}
		
	}
	
	/**
	 * 
	    * @Title: modify
	    * @Description: 跳转到修改用户页面
	    * @param @param model
	    * @param @param uid
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("modify")
	public String modify(Model model,int uid){
		System.out.println("modify已经运行 uid"+uid);
		//获得要修改用户的信息
		UserView userView = userService.selectOneById(uid);
		//设置回显信息
		model.addAttribute("user", userView);
		
		return "usermodify";
	}
	
	//保存要修改的用户信息
	@RequestMapping("modifysave")
	public String modifysave(Model model,UserView user,HttpSession session){
		System.out.println("modifysave已经运行 。。。");
		System.out.println("userView.id = "+user.getUserid()+",,,,,userView.rolename = "+user.getUsername());
		//得到当前登录用户的信息
		SmbmsUser sessionUser = (SmbmsUser) session.getAttribute("userSession");
		
		//执行用户信息修改操作，并且得到影响的行数
		int count  = userService.updateuser(user,sessionUser.getId());
		//判断修改是否执行成功
		if (count > 0 ) {
			model.addAttribute("msg", "修改成功！");
		}else{
			model.addAttribute("msg", "修改失败！");
		}	
		return "usermodify";
	}
	
	//执行保存添加用户操作
	@RequestMapping("saveuser")
	public String saveuser(Model model,SmbmsUser user,String sbirthday,HttpSession session){
		System.out.println("saveuser");
		
		//把string时间转换为date类型
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(sbirthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//添加新用户的信息
		user.setBirthday(date);
		user.setCreationdate(new Date());
		System.out.println("3");
		//获取当前登录的用户
		SmbmsUser sessionUser = (SmbmsUser)session.getAttribute("userSession");
		//给新用户传入createBy的值
		user.setCreatedby(sessionUser.getId());

		int count = userService.insertUser(user);
		if (count > 0) {
			model.addAttribute("msg", "添加新用户成功！");
		}else{
			model.addAttribute("msg", "添加新用户失败！");
		}
		
		return "useradd";
	}
	
	
	@RequestMapping("deluser")
	@ResponseBody
	public String deluser(int userid){
		HashMap<String, Object> result = new HashMap<String,Object>();
		
		System.out.println("deluser已经运行 。。。userid = "+userid);
		
		String mString = userService.deleteUser(userid);
		
		result.put("result", mString);
		return JSONArray.toJSONString(result);
	}
}
