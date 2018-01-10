package com.hyf.entryexit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hyf.entryexit.dao.AdminDAO;
import com.hyf.entryexit.entity.Clerk;
import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminDAO adminDAO;
	
	@Override
	public List<Department> findAllDepartmentAndService() {
		return adminDAO.findAllDepartmentAndService();
	}

	@Override
	public List<com.hyf.entryexit.entity.Service> findAllService() {
		return adminDAO.findAllService();
	}

	@Override
	public Integer saveDepartment(Department department,String[] selectedServiceId) {
		adminDAO.saveDepartment(department);
		//获取插入后的主键id,由mybatis生成
		Integer departmentId = department.getDepartment_id();
//		System.out.println(departmentId);
		Integer n = null;
		if(selectedServiceId != null){
			for (int i = 0; i < selectedServiceId.length; i++) {
	//			System.out.println("============================");
				Integer serviceId = Integer.parseInt(selectedServiceId[i]);
				Map<String, Object> params = new HashMap<String,Object>();
				params.put("departmentId", departmentId);
				params.put("serviceId", serviceId);
				n = adminDAO.saveDepartmentService(params);
	//			System.out.println(n);
			}
		}	
		return n;
	}

	@Override
	public Map<String,Object> findDepartmentById(Integer departmentId) {
		Map<String,Object> map = new HashMap<String,Object>();
		//查出网点信息
		Department department = adminDAO.findDepartmentById(departmentId);
		map.put("department", department);
		//查出所有业务信息
		List<com.hyf.entryexit.entity.Service> lists = adminDAO.findAllService();
		//去掉当前网点有的业务信息
		List<com.hyf.entryexit.entity.Service> services = new ArrayList<com.hyf.entryexit.entity.Service>();
		List<com.hyf.entryexit.entity.Service> ds = department.getServices();//当前网点里有的业务
		for (com.hyf.entryexit.entity.Service service : lists) {
			boolean flag = false;
			for (com.hyf.entryexit.entity.Service s : ds) {
				//找到有的业务
				if(service.getService_id() == s.getService_id() ){
					flag = true;
					break;
				}
			}
			//如果没找到放到集合里
			if(!flag){
				services.add(service);
			}
		}
		map.put("services", services);
		return map;
	}

	@Override
	public void updateDepartment(Department department,String[] selectedServiceId) {
		//修改网点信息
		adminDAO.updateDepartmentById(department);
		//修改网点与业务关系表
		if(selectedServiceId != null){
			for (int i = 0; i < selectedServiceId.length; i++) {
				Integer serviceId = Integer.parseInt(selectedServiceId[i]);
				Map<String, Object> params = new HashMap<String,Object>();
				params.put("departmentId", department.getDepartment_id());
				params.put("serviceId", serviceId);
				adminDAO.saveDepartmentService(params);
			}
		}
	}

	@Override
	public void deleteDepartment(Integer departmentId) {
		//删除网点
		adminDAO.deleteDepartmentById(departmentId);
		//删除当前网点对应的业务关系表记录
		adminDAO.deleteDepartmentServiceByDepartmentId(departmentId);
	}

	@Override
	public void deleteDepartmentService(Map<String, Object> params) {
		adminDAO.deleteDepartmentServiceByDepartmentIdAndServiceId(params);
	}

	@Override
	public List<Department> findDepartmentAll() {
		return adminDAO.findDepartmentAll();
	}

	@Override
	public Integer saveService(com.hyf.entryexit.entity.Service service) {
		return adminDAO.saveService(service);
	}

	@Override
	public Integer updateService(com.hyf.entryexit.entity.Service service) {
		return adminDAO.updateService(service);
	}

	@Override
	public void deleteService(Integer serviceId) {
		//删除业务记录
		adminDAO.deleteService(serviceId);
		//删除业务与网点关系表记录
		adminDAO.deleteDepartmentServiceByServiceId(serviceId);
	}

	@Override
	public List<Clerk> findAllClerk() {
		return adminDAO.findAllClerk();
	}
	
	@Override
	public Integer saveClerk(Clerk clerk) {
		return adminDAO.saveClerk(clerk);
	}

	@Override
	public Clerk findClerkById(Integer clerkId) {
		return adminDAO.findClerkById(clerkId);
	}

	@Override
	public Integer updateClerk(Clerk clerk) {
		return adminDAO.updateClerk(clerk);
	}

	@Override
	public Integer deleteClerk(Integer clerkId) {
		return adminDAO.deleteClerk(clerkId);
	}


}
