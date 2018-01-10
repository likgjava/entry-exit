package com.hyf.entryexit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hyf.entryexit.entity.SuperUser;

/**
 * 管理员模块拦截器，不登录不能进入
 * @author HuangYongFeng
 */
public class SuperUserLoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		SuperUser superUser = (SuperUser) request.getSession().getAttribute("superUser");
		//管理员模版拦截器，没登录不能进入管理员模块
		if(superUser == null){
			response.sendRedirect(request.getContextPath()+"/su_login.jsp");
			return false;
		}
		return true;
	}

}
