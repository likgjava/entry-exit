package com.hyf.entryexit.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hyf.entryexit.dao.CustomerDAO;
import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.entity.Prebook;
import com.hyf.entryexit.service.CustomerService;
/**
 * 实现普通客户 模块业务功能
 * @author HuangYongFeng
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Resource
	private CustomerDAO customerDAO;
	
	@Override
	public List<Department> findDepartmentAll() {
		return customerDAO.findDepartmentAll();
	}

	@Override
	public Department findDepartmentById(Integer departmentId) {
		return customerDAO.findDepartmentById(departmentId);
	}

	@Override
	public List<com.hyf.entryexit.entity.Service> findServiceByDepartmentId(Integer departmentId) {
		return customerDAO.findServiceByDepartmentId(departmentId);
	}

	@Override
	public String savePrebook(Prebook prebook) {
		//设置下固定属性
		prebook.setStatus("0");//受理状态，：0为未受理，1为已办理
		//生成的提取密码 当前时间,
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(System.currentTimeMillis());
		prebook.setVerification(str);//取号密码
		customerDAO.savePrebook(prebook);
		return str;
	}

	@Override
	public Prebook findPrebook(String passportId, String verification) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("passportId", passportId);
		params.put("verification", verification);
		return customerDAO.findPrebookByPassportIdAndVerification(params);
	}

}
