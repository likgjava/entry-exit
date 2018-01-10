package com.hyf.entryexit.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hyf.entryexit.dao.LoginDAO;
import com.hyf.entryexit.entity.Clerk;
import com.hyf.entryexit.entity.SuperUser;
import com.hyf.entryexit.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Resource
	private LoginDAO loginDAO;
	
	@Override
	public Clerk findClerkByClerkName(String clerkUserName) {
		return loginDAO.findClerkByClerkName(clerkUserName);
	}

	@Override
	public SuperUser findSuperUserBySuperName(String superName) {
		return loginDAO.findSuperUserBySuperName(superName);
	}

}
