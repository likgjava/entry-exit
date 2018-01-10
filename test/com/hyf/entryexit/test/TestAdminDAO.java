package com.hyf.entryexit.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hyf.entryexit.dao.AdminDAO;
import com.hyf.entryexit.entity.Clerk;
import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.entity.Service;

public class TestAdminDAO {
	
	private String[] conf = {"/com/hyf/entryexit/config/srping/applicationContext-mvc.xml"};
	ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
	AdminDAO dao = ac.getBean(AdminDAO.class);
	
	@Test
	@Ignore
	public void findAllDepartmentAndService(){
		List<Department> lists = dao.findAllDepartmentAndService();
		for (Department department : lists) {
			System.out.println(department.getDepartment_name());
			List<Service> ser = department.getServices();
			System.out.println("===============================");
			for (Service s : ser) {
				System.out.println(s.getService_name() + s.getService_id());
			}
			System.out.println("----------------------------");
		}
	}
	@Test
	@Ignore
	public void saveDepartment(){
		Department department = new Department();
		department.setDepartment_name("测试name");
		department.setDepartment_address("测试地址");
		System.out.println("id=:"+department.getDepartment_id());
		System.out.println("------------------------");
		dao.saveDepartment(department);
		System.out.println("id=:"+department.getDepartment_id());

	}
	@Test
	public void findAllClerk(){
		List<Clerk> clerks = dao.findAllClerk();
		for (Clerk clerk : clerks) {
			System.out.println(clerk.getClerk_username());
			System.out.println("----------------");
			System.out.println(clerk.getDepartment().getDepartment_name());
		}
	}
	
	
}
