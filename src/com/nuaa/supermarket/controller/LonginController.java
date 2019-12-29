package com.nuaa.supermarket.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nuaa.supermarket.pojo.SmbmsUser;
import com.nuaa.supermarket.service.LoginService;

/**
* @author 刘超明
* @version 创建时间：2019年10月30日 下午2:19:13
* 类说明
*/
@Controller
public class LonginController{

	@Autowired
	private LoginService loginService;
	
	/**
	 * 
	    * @Title: login
	    * @Description: 
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("login")
	public String login() {
		System.out.println("login controller running....");
		return "forward:login.jsp";
	}
	
	/**
	 * 
	    * @Title: doLogin
	    * @Description: 查询登录者信息
	    * @param @param request
	    * @param @param userCode
	    * @param @param userPassword
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("doLogin")
	public String doLogin (Model model,HttpServletRequest request,String userCode,String userPassword) {
		//System.out.println("userCode = "+userCode+",userPassword = "+userPassword);
		//查询登录信息
		if (userCode == null || userPassword == null) {	
			model.addAttribute("error", "用户名或者密码错误!");
			return "forward:login.jsp";
		}
		SmbmsUser user = loginService.getUserByQuery(userCode,userPassword);
		System.out.println("user = "+user);
		//判断用户密码是否正确
		if(user == null){
			System.out.println("777777");
			System.out.println("user == null");
			String mString = "用户名或者密码错误!";
			model.addAttribute("error", mString);
			System.out.println("8888888");
			return "forward:login.jsp";
		}else{
			//model.addAttribute("userSession", user);
			request.getSession().setAttribute("userSession", user);
			System.out.println(user.getUsername());
			return "frame";
		}	
		
	}
	
	/**
	 * 
	    * @Title: outLogin
	    * @Description: 退出登录
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	@RequestMapping("outLogin")
	public String outLogin(HttpSession session){
		//清除登录信息
		session.removeAttribute("userSession");
		return "forward:login.jsp";
	}
}
