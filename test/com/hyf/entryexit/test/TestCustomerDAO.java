package com.hyf.entryexit.test;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hyf.entryexit.dao.CustomerDAO;
import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.entity.Prebook;
import com.hyf.entryexit.entity.Service;

public class TestCustomerDAO {
	
	private String[] conf = {"/com/hyf/entryexit/config/srping/applicationContext-mvc.xml"};
	ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
	CustomerDAO dao = ac.getBean(CustomerDAO.class);
	
	@Test
	@Ignore
	public void findDepartmentAll(){
		List<Department> lists = dao.findDepartmentAll();
		for (Department department : lists) {
			System.out.println(department.getDepartment_name());
		}
	}
	@Test
	@Ignore
	public void findDepartmentById(){
		Department department = dao.findDepartmentById(4);
		System.out.println(department.getDepartment_address());
		System.out.println("-----------------------");
		List<Service> lists = department.getServices();
		for (Service service : lists) {
			System.out.println(service.getService_name());
		}
		System.out.println("------------------------");
		System.out.println(department.getQueuingNum());
	}
	@Test
	@Ignore
	public void savePrebook(){
		Prebook prebook = new Prebook();
		prebook.setPassport_id("440785197809128522");
		prebook.setPhone("13423432");
		prebook.setDepartment_id(1);
		prebook.setService_id(1);
		prebook.setAppointment_date("2015-02-05 星期四");
		prebook.setAppointment_time("10:30-11:00");
		prebook.setStatus("0");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(System.currentTimeMillis());
		prebook.setVerification(str);
		Integer n = dao.savePrebook(prebook);
		System.out.println(n);
	}
	@Test
	public void findPrebook(){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("passportId", "342585197807689");
		params.put("verification", "34220130311pm18");
		Prebook prebook = dao.findPrebookByPassportIdAndVerification(params);
		System.out.println(prebook.getPhone());
		System.out.println("----------------");
		System.out.println(prebook.getDepartment_name());
		System.out.println("-----------------");
		System.out.println(prebook.getService_name());
	}
	
	
	
}
