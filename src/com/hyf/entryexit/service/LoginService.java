package com.hyf.entryexit.service;

import com.hyf.entryexit.entity.Clerk;
import com.hyf.entryexit.entity.SuperUser;

/**
 *  业务员和管理员登录
 * @author HuangYongFeng
 *
 */
public interface LoginService {
	
	/**
	 * 通过业务员登录用户名查出业务员信息
	 * @param clerkUserName 业务员登录名
	 * @return Clerk业务员对象
	 */
	Clerk findClerkByClerkName(String clerkUserName);
	/**
	 * 通过管理员用户名登录管理员登录
	 * @param superName 管理员登录名
	 * @return SuperUser管理员对象
	 */
	SuperUser findSuperUserBySuperName(String superName);

}
