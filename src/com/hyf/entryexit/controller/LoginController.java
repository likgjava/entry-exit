package com.hyf.entryexit.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyf.entryexit.entity.Clerk;
import com.hyf.entryexit.entity.SuperUser;
import com.hyf.entryexit.service.LoginService;

/**
 * 业务员与管理员登录
 * 
 * @author HuangYongFeng
 */
@Controller
public class LoginController {

	@Resource
	private LoginService loginService;

	/**
	 * 业务员登录
	 */
	@RequestMapping("/login.form")
	@Transactional(readOnly = true)
	public String clerkLogin(String clerkUserName, String clerkPassword,HttpSession session) {
		if (clerkUserName == null || "".equals(clerkUserName.trim())
				|| clerkPassword == null || "".equals(clerkPassword.trim())) {
			session.setAttribute("login_error", "用户名或密码不能为空！");
			return "redirect:login.jsp";
		}
		Clerk clerk = loginService.findClerkByClerkName(clerkUserName);
		if (clerk == null) {
			session.setAttribute("login_error", "用户名或密码错误！");
			return "redirect:login.jsp";
		}
		if (!clerk.getClerk_password().equals(clerkPassword)) {
			session.setAttribute("login_error", "用户名或密码错误！");
			return "redirect:login.jsp";
		}
		session.removeAttribute("login_error");
		// 如果用户对，绑定用户到session
		session.setAttribute("clerk", clerk);
		return "redirect:index.html";
	}

	/**
	 * 业务员退出登录
	 */
	@RequestMapping("/clerkLoginOut.form")
	public String loginOut(HttpSession session) {
		session.removeAttribute("clerk");
		return "redirect:index.html";
	}

	/**
	 * 管理员登录
	 */
	@RequestMapping("/superUserLogin.form")
	@Transactional(readOnly = true)
	public String superUserLogin(String superName, String superPassword,HttpSession session) {
		if (superName == null || "".equals(superName.trim())
				|| superPassword == null || "".equals(superPassword.trim())) {
			session.setAttribute("login_error", "用户名或密码不能为空！");
			return "redirect:su_login.jsp";
		}
		SuperUser superUser = loginService.findSuperUserBySuperName(superName);
		if (superUser == null) {
			session.setAttribute("login_error", "用户名或密码错误！");
			return "redirect:su_login.jsp";
		}
		if (!superUser.getSuper_pwd().equals(superPassword)) {
			session.setAttribute("login_error", "用户名或密码错误！");
			return "redirect:su_login.jsp";
		}
		session.removeAttribute("login_error");
		// 如果用户对，绑定用户到session
		session.setAttribute("superUser", superUser);
		return "redirect:index.html";
	}
	/**
	 * 管理员退出登录
	 */
	@RequestMapping("/superUserLoginOut.form")
	public String superUserLoginOut(HttpSession session) {
		session.removeAttribute("superUser");
		return "redirect:index.html";
	}
	
}
