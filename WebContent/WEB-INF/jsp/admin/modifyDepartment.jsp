<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改网点信息--北京市出入境网上预约</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/modifyDepartment.js"></script>
    <script type="text/javascript">var contextPath = '${pageContext.request.contextPath}';</script>
</head>

<body>
<div class="wrap">
    <div class="top">
        <img src="${pageContext.request.contextPath}/images/banner.jpg" alt="出入境管理办事大厅" width="100%" height="100%"/>

    </div>
    <div class="nav">
        <a href="${pageContext.request.contextPath}/index.html" class="n1">首页</a>
        <a href="#" class="n2">机构设置</a>
        <a href="#" class="n3">办事指南</a>
        <a href="#" class="n4">便民措施</a>
        <a href="#" class="n5">表格下载</a>
        <a href="#" class="n6">法律法规</a>
        <a href="#" class="n7">收费标准</a>
    </div>
    <!--/Nav-->
    <div class="container" align="center">
        <h3 style="color: green;text-align: center;">修改网点信息</h3>
        <form action="${pageContext.request.contextPath}/admin/modifyDepartment.form" method="post"
              id="submitDepartment">
            <table border='1' cellpadding='0' cellspacing='0' class="prebook_result" style="font-size: 11pt;">
                <tr>
                    <td>网点名称</td>
                    <td>网点地址</td>
                    <td>当前可办理业务</td>
                    <td>新增业务</td>
                </tr>
                <tr>
                    <td><input type="text" name="department_name" value="${maps['department'].department_name}"
                               id="departmentName"/><input type="hidden" id="deptId" name="department_id"
                                                           value="${maps['department'].department_id}"/></td>
                    <td><input type="text" name="department_address" value="${maps['department'].department_address}"
                               id="departmentAddress"/></td>
                    <!-- 目前可办理业务 -->
                    <td style="text-align: left;">
                        <c:forEach items="${maps['department'].services}" var="service">
                            <p id="p_${service.service_id}">${service.service_name}&nbsp;
                                <a href="javascript:;"
                                    onclick="deleteDepartmentService(${maps['department'].department_id},${service.service_id},'${service.service_name}')">删除</a>
                            </p>
                        </c:forEach>
                    </td>
                    <!-- 可添加业务 -->
                    <td style="text-align: left;">
                        <c:forEach items="${maps['services']}" var="s">
                            <input type="checkbox" name="selectedServiceId" value="${s.service_id}"/>${s.service_name}
                            <br/>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <input id="submitBut" type="button" value="修改网点">
                        <span style="color:red;" id="error"></span>
                    </td>
                </tr>
            </table>
            <br/><br/>
        </form>
        <p style="text-align: center;font-size: 11pt;"><a
                href="${pageContext.request.contextPath}/admin/departmentManager.form">返回</a></p>
    </div>
    <!--/Container-->
    <div class="foot">
        <p>
            北京市公安局出入境管理处版权所有，未经许可请勿转载或镜像！
        </p>
        <p>
            信息产业部备案号：京ICP备05083646号
        </p>
    </div>
    <!--/Foot-->
</div>
<!--/Wrap-->
</body>
</html>
