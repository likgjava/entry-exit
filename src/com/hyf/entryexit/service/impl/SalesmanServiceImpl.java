package com.hyf.entryexit.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hyf.entryexit.dao.SalesmanDAO;
import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.entity.Prebook;
import com.hyf.entryexit.service.SalesmanService;
@Service
public class SalesmanServiceImpl implements SalesmanService {
	
	@Resource
	private SalesmanDAO salesmanDAO;
	
	@Override
	public Department findDepartmentById(Integer departmentId) {
		return salesmanDAO.findDepartmentById(departmentId);
	}

	@Override
	public List<Prebook> findPrebookByClerkId(Integer clerkId) {
		return salesmanDAO.findPrebookByClerkId(clerkId);
	}

	@Override
	public Integer updatePrebook(Map<String, Object> params) {
		return salesmanDAO.updatePrebook(params);
	}

}
