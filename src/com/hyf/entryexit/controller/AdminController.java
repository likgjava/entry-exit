package com.hyf.entryexit.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.hyf.entryexit.base.BaseErrorMsg;
import com.hyf.entryexit.base.JsonResult;
import com.hyf.entryexit.entity.Prebook;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hyf.entryexit.entity.Clerk;
import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.entity.Service;
import com.hyf.entryexit.entity.SuperUser;
import com.hyf.entryexit.service.AdminService;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 实现管理员模块所有业务请求：
 * 1、网点信息管理
 * 2、业务信息管理
 * 3、业务员信息管理
 *
 * @author HuangYongFeng
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 1、网点信息管理 --
     * 点网点信息管理，跳到departmentManager.jsp页面
     * 没登录返回管理面登录页面su_login.jsp
     *
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/departmentManager.form")
    @Transactional(readOnly = true)
    public String departmentManager(HttpSession session, Model model) {
        SuperUser superUser = (SuperUser) session.getAttribute("superUser");
        if (superUser == null) {
            return "redirect:../su_login.jsp";
        }
        //List<Department> departments = adminService.findAllDepartmentAndService();
        //model.addAttribute("departments", departments);
        return "admin/departmentManager";
    }

    @ResponseBody
    @RequestMapping("/listDepartment.form")
    @Transactional(readOnly = true)
    public JsonResult listDepartment() {
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            List<Department> departmentList = adminService.findAllDepartmentAndService();
            jsonResult.getData().put("departmentList", departmentList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("listDepartment reponse=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 1、网点信息管理 -- 跳到新增网点
     * 跳到新增网点 addDepartment.jsp
     * 查询出Service 所有的业务
     */
    @RequestMapping("/toAddDepartment.form")
    @Transactional(readOnly = true)
    public String toAddDepartment(Model model) {
        List<Service> services = adminService.findAllService();
        model.addAttribute("services", services);
        return "admin/addDepartment";
    }

    /**
     * 1、网点信息管理 -- 新增网点
     * 新增成功后跳到所有信息 departmentManager.jsp 页面
     */
    @ResponseBody
    @RequestMapping("/addDepartment.form")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addDepartment(Department department, String[] selectedServiceId) {
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            adminService.saveDepartment(department, selectedServiceId);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("addDepartment response=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 1、网点信息管理 -- 去到修改网点页面
     * 跳到修改页面 modifyDepartment.jsp 页面
     */
    @RequestMapping("/toModifyDepartment.form")
    @Transactional(readOnly = true)
    public String toModifyDepartment(Model model, Integer departmentId) {
//		System.out.println("departmentId--------->"+departmentId);
        //网点和网点没有的业务信息
        Map<String, Object> maps = adminService.findDepartmentById(departmentId);
        model.addAttribute("maps", maps);
        return "admin/modifyDepartment";
    }

    /**
     * 1、网点信息管理 -- 修改网点信息
     * 修改完网点信息后，返回 departmentManager.jsp 页面
     */
    @ResponseBody
    @RequestMapping("/modifyDepartment.form")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult modifyDepartment(Department department, String[] selectedServiceId) {
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            adminService.updateDepartment(department, selectedServiceId);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("modifyDepartment response=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 1、网点信息管理 -- 删除网点的业务关系记录
     * 删除完后，返回 modifyDepartment.jsp 页面
     * 通过网点和id和业务的id
     */
    @ResponseBody
    @RequestMapping("/deleteDepartmentService.form")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult deleteDepartmentService(Integer departmentId, Integer serviceId) {
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("departmentId", departmentId);
            params.put("serviceId", serviceId);
            adminService.deleteDepartmentService(params);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("modifyPrebook reponse=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 1、网点信息管理 -- 删除网点信息
     * 删除完网点信息后，返回 departmentManager.jsp 页面
     */
    @ResponseBody
    @RequestMapping("/deleteDepartment.form")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult deleteDepartment(Integer departmentId) {
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            adminService.deleteDepartment(departmentId);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("modifyPrebook reponse=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 2、业务信息管理
     * 去到业务管理serviceManage.jsp页面
     */
    @RequestMapping("/serviceManage.form")
    @Transactional(readOnly = true)
    public String toServiceManage(Model model, HttpSession session) {
        SuperUser superUser = (SuperUser) session.getAttribute("superUser");
        if (superUser == null) {
            return "redirect:../su_login.jsp";
        }
        List<Service> services = adminService.findAllService();
        model.addAttribute("services", services);
        return "admin/serviceManage";
    }

    @ResponseBody
    @RequestMapping("/listService.form")
    @Transactional(readOnly = true)
    public JsonResult listService() {
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            List<Service> serviceList = adminService.findAllService();
            jsonResult.getData().put("serviceList", serviceList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("listService response=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 2、业务信息管理-- 新增业务
     * 新增成功后跳到所有信息 serviceManage.jsp页面
     */
    @ResponseBody
    @RequestMapping("/addService.form")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addService(Service service) {
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            adminService.saveService(service);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("addService response=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 2、业务信息管理 -- 修改业务信息
     * 修改完网点信息后，返回 serviceManage.jsp 页面
     */
    @ResponseBody
    @RequestMapping("/modifyService.form")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult modifyService(Service service) {
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            adminService.updateService(service);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("modifyService response=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 2、业务信息管理 -- 删除业务信息,并删除业务与网点关联记录
     * 删除完后，返回 serviceManage.jsp 页面
     */
    @ResponseBody
    @RequestMapping("/deleteService.form")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult deleteService(Integer serviceId) {
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            adminService.deleteService(serviceId);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("deleteService response=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 3、业务员信息管理
     * 去到业务员管理clerkManage.jsp页面
     */
    @RequestMapping("/clerkManage.form")
    @Transactional(readOnly = true)
    public String toClerkManage(Model model, HttpSession session) {
        SuperUser superUser = (SuperUser) session.getAttribute("superUser");
        if (superUser == null) {
            return "redirect:../su_login.jsp";
        }
        //List<Clerk> clerks = adminService.findAllClerk();
        //model.addAttribute("clerks", clerks);
        return "admin/clerkManage";
    }

    @ResponseBody
    @RequestMapping("/listClerk.form")
    @Transactional(readOnly = true)
    public JsonResult listClerk() {
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            List<Clerk> clerkList = adminService.findAllClerk();
            jsonResult.getData().put("clerkList", clerkList);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("listClerk response=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 3、业务员信息管理-- 跳到新增业务员页面
     * 跳到addClerk.jsp 页面
     * 查出所有网点信息
     */
    @RequestMapping("/toAddClerk.form")
    @Transactional(readOnly = true)
    public String toAddClerk(Model model) {
        List<Department> departments = adminService.findDepartmentAll();
        model.addAttribute("departments", departments);
        return "admin/addClerk";
    }



    /**
     * 3、业务员信息管理-- 新增业务员
     * 新增成功后跳到所有信息 clerkManage.jsp页面
     */
    @ResponseBody
    @RequestMapping("/addClerk.form")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult addClerk(Clerk clerk) {
        System.out.println("addClerk........" + JSONObject.toJSONString(clerk));
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            adminService.saveClerk(clerk);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("addClerk response=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }


    /**
     * 3、业务员信息管理-- 跳到修改业务员页面
     * 跳到modifyClerk.jsp 页面
     */
    @RequestMapping("/toModifyClerk.form")
    @Transactional(readOnly = true)
    public String toModifyClerk(Model model, Integer clerkId) {
        //业务员信息
        Clerk clerk = adminService.findClerkById(clerkId);
        //所有网点信息
        List<Department> departments = adminService.findDepartmentAll();
        model.addAttribute("departments", departments);
        model.addAttribute("clerk", clerk);
        return "admin/modifyClerk";
    }

    /**
     * 3、业务员信息管理-- 修改业务员信息
     * 新增成功后跳到所有信息 clerkManage.jsp页面
     */
    @ResponseBody
    @RequestMapping("/modifyClerk.form")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult modifyClerk(Clerk clerk) {
        System.out.println("modifyClerk........" + JSONObject.toJSONString(clerk));
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            adminService.updateClerk(clerk);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("modifyClerk response=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 3、业务员信息管理 -- 删除业务员信息
     * 删除完后，返回 clerkManage.jsp 页面
     */
    @ResponseBody
    @RequestMapping("/deleteClerk.form")
    @Transactional(rollbackFor = Exception.class)
    public JsonResult deleteClerk(Integer clerkId) {
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            adminService.deleteClerk(clerkId);
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("deleteClerk response=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }


}













