package com.nuaa.supermarket.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
* @author 刘超明
* @version 创建时间：2019年11月5日 下午5:22:57
* 拦截除了loin.action的所有请求，确保是密码登录
*/
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		//得到登录用户的信息
		Object sessionUser = request.getSession().getAttribute("userSession");
		//判断是否有登录用户的信息
		if (sessionUser != null) {
			return true;//有登录的信息，可以执行方法
		}else{
			//没有登录，不可以执行请求
			response.sendRedirect(request.getContextPath()+"/401.jsp");
			return false;
		}	
		
	}

}
