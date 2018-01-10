package com.hyf.entryexit.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyf.entryexit.entity.Clerk;
import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.entity.Service;
import com.hyf.entryexit.entity.SuperUser;
import com.hyf.entryexit.service.AdminService;

/**
 * 实现管理员模块所有业务请求：
 * 1、网点信息管理
 * 2、业务信息管理
 * 3、业务员信息管理
 * @author HuangYongFeng
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Resource
	private AdminService adminService;
	
	/**1、网点信息管理 -- 
	 * 点网点信息管理，跳到departmentManager.jsp页面
	 * 没登录返回管理面登录页面su_login.jsp
	 * @param session 
	 * @param model
	 * @return
	 */
	@RequestMapping("/departmentManager.form")
	@Transactional(readOnly=true)
	public String departmentManager(HttpSession session,Model model){
		SuperUser superUser = (SuperUser) session.getAttribute("superUser");
		if(superUser == null){
			return "redirect:../su_login.jsp";
		}
		List<Department> departments = adminService.findAllDepartmentAndService();
		model.addAttribute("departments", departments);
		return "admin/departmentManager";
	}
	/**1、网点信息管理 -- 跳到新增网点
	 * 跳到新增网点 addDepartment.jsp
	 * 查询出Service 所有的业务
	 */
	@RequestMapping("/toAddDepartment.form")
	@Transactional(readOnly=true)
	public String toAddDepartment(Model model){
		List<Service> services = adminService.findAllService();
		model.addAttribute("services", services);
		return "admin/addDepartment";
	}
	/**1、网点信息管理 -- 新增网点
	 * 新增成功后跳到所有信息 departmentManager.jsp 页面
	 */
	@RequestMapping("/addDepartment.form")
	@Transactional(rollbackFor=Exception.class)
	public String addDepartment(Model model,Department department,String[] selectedServiceId){
//		System.out.println(Arrays.toString(selectedServiceId));
		adminService.saveDepartment(department, selectedServiceId);
		return "redirect:departmentManager.form";
	}
	/**1、网点信息管理 -- 去到修改网点页面
	 * 跳到修改页面 modifyDepartment.jsp 页面
	 */
	@RequestMapping("/toModifyDepartment.form")
	@Transactional(readOnly=true)
	public String toModifyDepartment(Model model,Integer departmentId){
//		System.out.println("departmentId--------->"+departmentId);
		//网点和网点没有的业务信息
		Map<String,Object> maps = adminService.findDepartmentById(departmentId);
		model.addAttribute("maps", maps);
		return "admin/modifyDepartment";
	}
	
	/**1、网点信息管理 -- 修改网点信息
	 * 修改完网点信息后，返回 departmentManager.jsp 页面
	 */
	@RequestMapping("/modifyDepartment.form")
	@Transactional(rollbackFor=Exception.class)
	public String modifyDepartment(Department department,String[] selectedServiceId){
		adminService.updateDepartment(department,selectedServiceId);
		return "redirect:departmentManager.form";
	}
	
	/**1、网点信息管理 -- 删除网点的业务关系记录
	 * 删除完后，返回 modifyDepartment.jsp 页面
	 * 通过网点和id和业务的id
	 */
	@RequestMapping("/deleteDepartmentService.form")
	@Transactional(rollbackFor=Exception.class)
	public String deleteDepartmentService(Integer departmentId,Integer serviceId){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("departmentId", departmentId);
		params.put("serviceId", serviceId);
		adminService.deleteDepartmentService(params);
		return "redirect:toModifyDepartment.form?departmentId="+departmentId;
	}
	
	/**1、网点信息管理 -- 删除网点信息
	 * 删除完网点信息后，返回 departmentManager.jsp 页面
	 */
	@RequestMapping("/deleteDepartment.form")
	@Transactional(rollbackFor=Exception.class)
	public String deleteDepartment(Integer departmentId){
		adminService.deleteDepartment(departmentId);
		return "redirect:departmentManager.form";
	}
	/**2、业务信息管理
	 * 去到业务管理serviceManage.jsp页面
	 */
	@RequestMapping("/serviceManage.form")
	@Transactional(readOnly=true)
	public String toServiceManage(Model model,HttpSession session){
		SuperUser superUser = (SuperUser) session.getAttribute("superUser");
		if(superUser == null){
			return "redirect:../su_login.jsp";
		}
		List<Service> services = adminService.findAllService();
		model.addAttribute("services", services);
		return "admin/serviceManage";
	}
	/**2、业务信息管理-- 新增业务
	 * 新增成功后跳到所有信息 serviceManage.jsp页面
	 */
	@RequestMapping("/addService.form")
	@Transactional(rollbackFor=Exception.class)
	public String addService(Service service){
//		System.out.println("-----begin----->"+service.getService_name());
		try {
			//页面传来的是连接传来的，如果是中文那就要转下编码
			String serviceName = new String(service.getService_name().getBytes("iso-8859-1"),"UTF-8");
			service.setService_name(serviceName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		System.out.println("-----end----->"+service.getService_name());
		adminService.saveService(service);
		return "redirect:serviceManage.form";
	}
	/**2、业务信息管理 -- 修改业务信息
	 * 修改完网点信息后，返回 serviceManage.jsp 页面
	 */
	@RequestMapping("/modifyService.form")
	@Transactional(rollbackFor=Exception.class)
	public String modifyService(Service service){
		try {
			//页面传来的是连接传来的，如果是中文那就要转下编码
			String serviceName = new String(service.getService_name().getBytes("iso-8859-1"),"UTF-8");
			service.setService_name(serviceName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		adminService.updateService(service);
		return "redirect:serviceManage.form";
	}
	/**2、业务信息管理 -- 删除业务信息,并删除业务与网点关联记录
	 * 删除完后，返回 serviceManage.jsp 页面
	 */
	@RequestMapping("/deleteService.form")
	@Transactional(rollbackFor=Exception.class)
	public String deleteService(Integer serviceId){
		adminService.deleteService(serviceId);
		return "redirect:serviceManage.form";
	}
	
	/**3、业务员信息管理
	 * 去到业务员管理clerkManage.jsp页面
	 */
	@RequestMapping("/clerkManage.form")
	@Transactional(readOnly=true)
	public String toClerkManage(Model model,HttpSession session){
		SuperUser superUser = (SuperUser) session.getAttribute("superUser");
		if(superUser == null){
			return "redirect:../su_login.jsp";
		}
		List<Clerk> clerks = adminService.findAllClerk();
		model.addAttribute("clerks",clerks);
		return "admin/clerkManage";
	}
	/**3、业务员信息管理-- 跳到新增业务员页面
	 * 跳到addClerk.jsp 页面
	 * 查出所有网点信息
	 */
	@RequestMapping("/toAddClerk.form")
	@Transactional(readOnly=true)
	public String toAddClerk(Model model){
		List<Department> departments = adminService.findDepartmentAll();
		model.addAttribute("departments", departments);
		return "admin/addClerk";
	}
	/**3、业务员信息管理-- 新增业务员
	 * 新增成功后跳到所有信息 clerkManage.jsp页面
	 */
	@RequestMapping("/addClerk.form")
	@Transactional(rollbackFor=Exception.class)
	public String addClerk(Model model,Clerk clerk){
		adminService.saveClerk(clerk);
		return "redirect:clerkManage.form";
	}
	/**3、业务员信息管理-- 跳到修改业务员页面
	 * 跳到modifyClerk.jsp 页面
	 */
	@RequestMapping("/toModifyClerk.form")
	@Transactional(readOnly=true)
	public String toModifyClerk(Model model,Integer clerkId){
		//业务员信息
		Clerk clerk = adminService.findClerkById(clerkId);
		//所有网点信息
		List<Department> departments = adminService.findDepartmentAll();
		model.addAttribute("departments", departments);
		model.addAttribute("clerk", clerk);
		return "admin/modifyClerk";
	}
	/**3、业务员信息管理-- 修改业务员信息
	 * 新增成功后跳到所有信息 clerkManage.jsp页面
	 */
	@RequestMapping("/modifyClerk.form")
	@Transactional(rollbackFor=Exception.class)
	public String modifyClerk(Clerk clerk){
		adminService.updateClerk(clerk);
		return "redirect:clerkManage.form";
	}
	/**3、业务员信息管理 -- 删除业务员信息
	 * 删除完后，返回 clerkManage.jsp 页面
	 */
	@RequestMapping("/deleteClerk.form")
	@Transactional(rollbackFor=Exception.class)
	public String deleteClerk(Integer clerkId){
		adminService.deleteClerk(clerkId);
		return "redirect:clerkManage.form";
	}
	
	
}













