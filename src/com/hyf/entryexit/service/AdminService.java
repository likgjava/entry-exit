package com.hyf.entryexit.service;

import java.util.List;
import java.util.Map;

import com.hyf.entryexit.entity.Clerk;
import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.entity.Service;

/**
 * 管理员模块业务功能
 * @author HuangYongFeng
 */
public interface AdminService {

	/**
	 * 查询所有网点信息、业务信息
	 */
	List<Department> findAllDepartmentAndService();
	/**
	 * 增加网点时，要查询出所有的业务信息
	 * @return
	 */
	List<Service> findAllService();
	/**
	 * 增加网点记录,还要增加网点和业务关系表
	 * @param department 网点对象
	 * @param selectedServiceId 所有业务id
	 * @return
	 */
	Integer saveDepartment(Department department,String[] selectedServiceId);
	/**去到修改网点页面
	 * 难过业务员关联的网点id去查询所有预约、询业务、网点信息
	 * 通过网点id查询出网点信息
	 * @param departmentId 网点id，
	 * @return Map<String,Object> 集合，有网点和业务信息
	 */
	Map<String,Object> findDepartmentById(Integer departmentId);
	
	/**修改网点信息并修改对应网点的业务信息
	 * @param department Department对象信息
	 * @param selectedServiceId 业务id数组
	 */
	void updateDepartment(Department department,String[] selectedServiceId);
	/**
	 * 删除网点的业务关系表
	 * @param params 网点id和业务id
	 */
	void deleteDepartmentService(Map<String,Object> params);
	/**
	 * 删除网点，和网点相关的业务关系表一起删除
	 * @param departmentId 网点id
	 */
	void deleteDepartment(Integer departmentId);
	/**
	 * 增加添加业务员时，查出所有的网点信息
	 * @return List<Department>
	 */
	List<Department> findDepartmentAll();
	/**
	 * 增加业务员记录
	 * @param service 业务员对象
	 * @return
	 */
	Integer saveService(Service service);
	/**
	 * 更新业务信息
	 * @param service 业务对象
	 * @return
	 */
	Integer updateService(Service service);
	/**
	 * 删除业务记录,并要删除业务现网点的关系表记录
	 * @param serviceId 业务id
	 * @return
	 */
	void deleteService(Integer serviceId);
	/**
	 * 查询所有业务员信息
	 * @return List<Clerk>
	 */
	List<Clerk> findAllClerk();
	/**
	 * 增加业务员记录
	 * @param clerk 业务员对象
	 * @return
	 */
	Integer saveClerk(Clerk clerk);
	/**
	 * 通过id查询出业务员信息
	 * @param clerkId 业务员id
	 * @return
	 */
	Clerk findClerkById(Integer clerkId);
	/**
	 * 更改业务员信息
	 * @param clerk 业务员对象
	 * @return
	 */
	Integer updateClerk(Clerk clerk);
	/**
	 * 删除业务员
	 * @param clerkId 业务员id
	 * @return
	 */
	Integer deleteClerk(Integer clerkId);
	
	
}










