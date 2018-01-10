package com.hyf.entryexit.dao;

import com.hyf.entryexit.annotation.MyBatisRepository;
import com.hyf.entryexit.entity.Clerk;
import com.hyf.entryexit.entity.SuperUser;
/**
 * 业务员和管理员登录
 * @author HuangYongFeng
 */
@MyBatisRepository
public interface LoginDAO {
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
