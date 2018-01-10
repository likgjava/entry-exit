package com.hyf.entryexit.service;

import java.util.List;

import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.entity.Prebook;
import com.hyf.entryexit.entity.Service;

/**
 * 普通客户 模块业务功能
 * @author HuangYongFeng
 */
public interface CustomerService {
	/**
	 * 普通客户模块的网点查询功能
	 * @return List<Department>
	 */
	List<Department> findDepartmentAll();
	/**
	 * 通过网点id查询网点信息、可办理业务（service,department_service关连表）、排队人数情况（prebook表里的department_id）
	 * @param departmnetId 网点id
	 * @return Department
	 */
	Department findDepartmentById(Integer departmentId);
	/**
	 * 通过在ajax点网点id关连查询出所有可以办理业务
	 * @param departmentId 网点id
	 * @return List<Service> 办理业务
	 */
	List<Service> findServiceByDepartmentId(Integer departmentId);
	/**
	 * 增加预约记录
	 * @param prebook 预约表对象
	 * @return 返回提取密码
	 */
	String savePrebook(Prebook prebook);
	/**
	 * 通过身份证和提取密码查询预约信息
	 * @param passportId 身份证号码
	 * @param verification 提取密码
	 * @return 返回预约信息
	 */
	Prebook findPrebook(String passportId,String verification);
	
}
