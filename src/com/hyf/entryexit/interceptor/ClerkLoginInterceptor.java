package com.hyf.entryexit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hyf.entryexit.entity.Clerk;
/**
 * 业务模版拦截器，没登录不能进入业务员模块
 * @author HuangYongFeng
 */
public class ClerkLoginInterceptor implements HandlerInterceptor {

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
		Clerk clerk = (Clerk) request.getSession().getAttribute("clerk");
		//业务模版拦截器，没登录不能进入业务员模块
		if(clerk == null){
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return false;
		}
		return true;
	}

}
