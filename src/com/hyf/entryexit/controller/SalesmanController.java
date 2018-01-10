package com.hyf.entryexit.controller;

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
import com.hyf.entryexit.entity.Prebook;
import com.hyf.entryexit.service.SalesmanService;

/**
 * 业务员模版：
 * 1、本网点排队情况查询
 * 2、修改预约单状态
 * @author HuangYongFeng
 */
@Controller
@RequestMapping("/salesman")
public class SalesmanController {
	
	@Resource
	private SalesmanService salesmanService;
	/**1、本网点排队情况查询
	 * 通过网点id查询网点信息、可办理业务、排队人数情况,如果业务员没登录，就返回登录页面login.html
	 * @param id 网点id
	 * @param model 绑定查询数据返回给页面
	 * @param ra 返回没选择网点查询的错误提示
	 * @return 返回页对应的路径面文件名
	 */
	@RequestMapping("/departmentSearchResult.form")
	@Transactional(readOnly=true)
	public String findDepartment(HttpSession session,Model model){
		//如果业务员没登录是不能查询的
		Clerk clerk = (Clerk) session.getAttribute("clerk");
		if(clerk == null){
			return "redirect:../login.jsp";
		}
		Integer departmentId = clerk.getDepartment_id();
		Department department = salesmanService.findDepartmentById(departmentId);
		model.addAttribute("department",department);
		return "salesman/departmentSearchResult";
	}
	/**2、修改预约单状态
	 * 跳到修改预约单页面modifyPrebook.jsp
	 * @param session 从sseion里找，看业务员是否登录
	 * @param model 传给页面的数据
	 * @return  modifyPrebook.jsp
	 */
	@RequestMapping("/toModifyPrebook.form")
	@Transactional(readOnly=true)
	public String findPrebooktoModifyprebook(HttpSession session,Model model){
		//如果业务员没登录是不能查询的
		Clerk clerk = (Clerk) session.getAttribute("clerk");
		if(clerk == null){
			return "redirect:../login.jsp";
		}
		Integer clerkId = clerk.getClerk_id();
		List<Prebook> prebooks = salesmanService.findPrebookByClerkId(clerkId);
		model.addAttribute("prebooks",prebooks);
		return "salesman/modifyPrebook";
	}
	/**2、修改预约单状态
	 * 修改预约状态：预约单状态：0为未受理，1为已办理
	 * @param status 页面传来的预约状态 0或1
	 * @param prebookId 页面传来的prebook表id
	 */
	@RequestMapping("/modifyPrebook.form")
	@Transactional(rollbackFor=Exception.class)
	public String ModifyPrebook(String status,Integer prebookId){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("status", status);
		params.put("prebookId", prebookId);
		salesmanService.updatePrebook(params);
		return "redirect:toModifyPrebook.form";
	}
	
	

}
