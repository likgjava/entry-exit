package com.hyf.entryexit.dao;

import java.util.List;
import java.util.Map;

import com.hyf.entryexit.annotation.MyBatisRepository;
import com.hyf.entryexit.entity.Clerk;
import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.entity.Service;

/**
 * 管理员模块
 * @author HuangYongFeng
 */
@MyBatisRepository
public interface AdminDAO {
	
	/**
	 * 查询所有网点信息、业务信息
	 */
	List<Department> findAllDepartmentAndService();
	/**
	 * 查询出所有的业务信息
	 * @return
	 */
	List<Service> findAllService();
	/**
	 * 增加网点记录,还要增加网点和业务关系表
	 * @param department 网点对象
	 * @return
	 */
	Integer saveDepartment(Department department);
	/**
	 * 增加网点记录,还要增加网点和业务关系表
	 * @param params 网点id和业务id的集合
	 */
	Integer saveDepartmentService(Map<String,Object> params);
	/**
	 * 难过业务员关联的网点id去查询所有预约、询业务、网点信息
	 * 通过网点id查询出网点信息
	 * @param departmentId 网点id，
	 * @return Department
	 */
	Department findDepartmentById(Integer departmentId);
	/**
	 * 更新网点信息
	 * @param department 网点对象
	 * @return
	 */
	Integer updateDepartmentById(Department department);
	/**
	 * 删除网点记录
	 * @param department 网点id
	 * @return
	 */
	Integer deleteDepartmentById(Integer departmentId);
	/**
	 * 删除网点的业务关系表
	 * @param params 网点id和业务id
	 */
	Integer deleteDepartmentServiceByDepartmentIdAndServiceId(Map<String,Object> params);
	/**
	 * 删除网点对应的业务关系记录
	 * @param department 网点id
	 * @return
	 */
	Integer deleteDepartmentServiceByDepartmentId(Integer departmentId);
	/**
	 * 查询所有的受理网点
	 * @return
	 */
	List<Department> findDepartmentAll();
	/**
	 * 增加业务记录
	 * @param service 业务对象
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
	 * 删除业务记录
	 * @param serviceId 业务id
	 * @return
	 */
	Integer deleteService(Integer serviceId);
	/**
	 * 删除网点对应的业务关系记录
	 * @param serviceId 业务id
	 * @return
	 */
	Integer deleteDepartmentServiceByServiceId(Integer serviceId);
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













