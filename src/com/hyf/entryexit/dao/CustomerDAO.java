package com.hyf.entryexit.dao;

import java.util.List;
import java.util.Map;

import com.hyf.entryexit.annotation.MyBatisRepository;
import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.entity.Prebook;
import com.hyf.entryexit.entity.Service;

@MyBatisRepository
public interface CustomerDAO {
	/**
	 * 查询所有的受理网点
	 * @return
	 */
	List<Department> findDepartmentAll();
	/**
	 * 通过网点id查询出网点信息
	 * @param departmentId
	 * @return
	 */
	Department findDepartmentById(Integer departmentId);
	/**
	 * 通过网点id关连查询出所有可以办理业务
	 * @param departmentId 网点id
	 * @return List<Service> 办理业务
	 */
	List<Service> findServiceByDepartmentId(Integer departmentId);
	/**
	 * 增加prebook预约记录
	 * @param prebook 传入Prebook对象
	 */
	Integer savePrebook(Prebook prebook);
	/**
	 * 通过身份证和提取密码查询预约信息
	 * @param params 多参数，用map传入myBatis方便, passportId 身份证号码 verification 提取密码
	 * @return Prebook预约对象信息
	 */
	Prebook findPrebookByPassportIdAndVerification(Map<String,Object> params);
	
}










