package com.hyf.entryexit.service;

import java.util.List;
import java.util.Map;

import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.entity.Prebook;

/**
 * 业务员模版
 * @author HuangYongFeng
 */
public interface SalesmanService {
	/**
	 * 通过网点id查询网点信息、可办理业务（service,department_service关连表）、排队人数情况（prebook表里的department_id）
	 * @param departmnetId 网点id
	 * @return Department
	 */
	Department findDepartmentById(Integer departmentId);
	/**
	 * 跳到 修改预约单状态 要查询的信息 ,通过业务员id查询相关网点的预约
	 * @param clerkId 业务员员表id
	 * @return List<Prebook> 预约信息
	 */
	List<Prebook> findPrebookByClerkId(Integer clerkId);
	/**
	 * 通过prebook的id和预约状态值更改数据
	 * @param params map集合，里的是prebook的id和预约状态的值
	 * @return Integer
	 */
	Integer updatePrebook(Map<String,Object> params);
}
