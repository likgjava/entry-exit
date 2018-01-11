package com.hyf.entryexit.controller;

import java.util.List;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONObject;
import com.hyf.entryexit.base.BaseErrorMsg;
import com.hyf.entryexit.base.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hyf.entryexit.annotation.Token;
import com.hyf.entryexit.entity.Department;
import com.hyf.entryexit.entity.Prebook;
import com.hyf.entryexit.entity.Service;
import com.hyf.entryexit.service.CustomerService;
import com.hyf.entryexit.util.AvailableDateUtil;

/**
 * 实现普通客户模块的所有功能：
 * 1、网点查询
 * 2、网上预约
 * 3、预约查询
 *
 * @author HuangYongFeng
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    /**
     * 1、网点查询
     * 在主页点击网点查询时，查询出所有网点，并跳到/WEB-INF/jsp/customer/searchDepartment.jsp页面
     *
     * @param model 绑定查询数据返回给页面
     * @return 返回页对应的路径面文件名
     */
    @RequestMapping("/searchDepartment.form")
    @Transactional(readOnly = true)
    public String findDepartmentAll(Model model) {
        //通过service层查询
        List<Department> departments = customerService.findDepartmentAll();
        //绑定到model对象
        model.addAttribute("departments", departments);
        return "customer/searchDepartment";
    }

    /**
     * 1、网点查询
     * 通过网点id查询网点信息、可办理业务、排队人数情况
     *
     * @param id    网点id
     * @param model 绑定查询数据返回给页面
     * @param ra    返回没选择网点查询的错误提示
     * @return 返回页对应的路径面文件名
     */
    /*方法里的 @RequestParam("departmentId") Integer id， 是页面传来的参数，如果页面传的是参数名称 departmentId，而这里方法的参数
     *写成id,那就样写 @RequestParam("departmentId") 在方法的参数前面，如果方法参数写成 Integer departmentId ,那就不用在方法加
	 * @RequestParam("departmentId")
	 */
    @RequestMapping("/departmentSearchResult.form")
    @Transactional(readOnly = true)
    public String findDepartment(@RequestParam("department_id") Integer id, Model model, RedirectAttributes ra) {
        //如果id返回是-1，那就是没有选择网点，不给提交，提示用户请选择网点,否则提交查询
        if (id == -1) {
            ra.addFlashAttribute("department_error", "请选择一个网点！");
            return "redirect:searchDepartment.form";
        }
        Department department = customerService.findDepartmentById(id);
        model.addAttribute("department", department);
        return "customer/departmentSearchResult";
    }








    /**
     * 2、网上预约
     * 查询出所有受理单位网点，跳到prebook.jsp页面
     *
     * @param model 绑定查询数据返回给页面
     * @return 返回页对应的路径面文件名
     */
    @RequestMapping("/prebook.form")
    @Transactional(readOnly = true)
    @Token(save = true)//用来防止数据重复提交，这个是增加token
    public String toPrebook(Model model) {
        System.out.println("111111111111");
        List<Department> lists = customerService.findDepartmentAll();
        model.addAttribute("departments", lists);
        //获得可预约日期列表, 可提前5个工作日预约，周末不能预约，当天不能预约
        String[] dates = AvailableDateUtil.getAvailableDate();
        model.addAttribute("dates", dates);
        return "customer/prebook";
    }

    /**
     * 2、网上预约
     * 通过在ajax点网点id关连查询出所有可以办理业务
     *
     * @param departmentId 页面传来的网点id
     * @return List<Service> 对应的网点所可以办理的业务信息集合
     */
    // @ResponseBody 就可以把数据转成json对象
    @RequestMapping("/findServiceByDepartmentId.form")
    @ResponseBody
    @Transactional(readOnly = true)
    public List<Service> findServiceByDepartmentId(Integer departmentId) {
        List<Service> services = customerService.findServiceByDepartmentId(departmentId);
//		System.out.println("--------"+services);//查询不到是返回[]
        return services;
    }

    /**
     * 2、网上预约
     * 预约,预约成绩跳到msg.jsp页面
     */
    @RequestMapping("/savePrebook.form")
    @Transactional(rollbackFor = Exception.class)
    @Token(remove = true)//用来防止数据重复提交，这个是删除token
    public String savePrebookResult(Prebook prebook, Model model) {
        try {
            String verification = customerService.savePrebook(prebook);
            model.addAttribute("msg", "<span style='color:green;'>预约成功，取号密码为：" + verification + " 请记取号密码！</span>");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "<span style='color:red;'>网络异常！请稍后重试！谢谢！</span>");
            return "msg";
        }
        return "msg";
    }

    /**
     * 2、网上预约
     * 预约,预约成绩跳到msg.jsp页面
     */
    @ResponseBody
    @RequestMapping("/savePrebookNew.form")
    @Transactional(rollbackFor = Exception.class)
    //@Token(remove = true)
    public JsonResult savePrebookNew(Prebook prebook) {

        System.out.println("savePrebookNew........" + JSONObject.toJSONString(prebook));
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            String verification = customerService.savePrebook(prebook);
            //model.addAttribute("msg", "<span style='color:green;'>预约成功，取号密码为：" + verification + " 请记取号密码！</span>");

            jsonResult.getData().put("verification", verification);

        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("savePrebookNew reponse=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping("/searchDepartmentInfo.form")
    @Transactional(readOnly = true)
    public JsonResult searchDepartmentInfo(@RequestParam("department_id") Integer id) {
        JsonResult jsonResult = JsonResult.getInstance();
        try {
            //如果id返回是-1，那就是没有选择网点，不给提交，提示用户请选择网点,否则提交查询
            if (id == -1) {
                //ra.addFlashAttribute("department_error", "请选择一个网点！");
                jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_QUERY_PARAM);
            } else {
                Department department = customerService.findDepartmentById(id);
                //model.addAttribute("department", department);
                jsonResult.getData().put("department", department);
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult = JsonResult.getFailResult(BaseErrorMsg.ERROR_API_FAIL);
        }
        System.out.println("searchDepartmentInfo reponse=" + JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }





    /**
     * 增加预约时，如果重复提交，做出提示
     *
     * @param model 绑定错误信息给页面
     * @return
     */
    @RequestMapping("/savePrebookToMsg.form")
    public String savePrebookToMsg(Model model) {
        model.addAttribute("msg", "<span style='color:red;'>您已提交过数据，请不要重复提交！可以返回首页查询！谢谢！</span>");
        return "msg";
    }

    /**
     * 3、预约查询
     * 跳到searchPrebook.jsp页面
     *
     * @return
     */
    @RequestMapping("/toSearchPrebook.form")
    public String toSearchPrebook() {
        return "customer/searchPrebook";
    }

    /**
     * 3、预约查询
     * 查询预约
     *
     * @param passportId   页面传来的身份证号码
     * @param verification 页面传来的提取密码
     * @param model        绑定数据返回页面
     * @return 跳到searchPrebookResult.jsp
     */
    @RequestMapping("/searchPrebookResult.form")
    @Transactional(readOnly = true)
    public String searchPrebook(String passportId, String verification, Model model) {
        Prebook prebook = customerService.findPrebook(passportId, verification);
        if (prebook == null) {
            model.addAttribute("msg", "<span style='color:red;'>没有办理业务，请重新查询！</span>");
            return "msg";
        }
        model.addAttribute("prebook", prebook);
        return "customer/searchPrebookResult";
    }

}










