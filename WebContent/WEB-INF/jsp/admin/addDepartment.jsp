<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.hyf.entryexit.entity.*" %>
<% SuperUser superUser = (SuperUser) session.getAttribute("superUser"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加新网点--北京市出入境网上预约</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
    <script type="text/javascript">var contextPath = '${pageContext.request.contextPath}';</script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/addDepartment.js"></script>
</head>

<body>
<div class="wrap">
    <div class="top">
        <embed src="${pageContext.request.contextPath}/images/top.swf" quality="high" width="100%"
               height="100%" type="application/x-shockwave-flash">
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
        <div class="login_user">
            <span>您好，管理员&nbsp;<span style="color: blue;"><%=superUser.getSuper_name()%></span>&nbsp;&nbsp;</span>
            <a href="${pageContext.request.contextPath}/superUserLoginOut.form"
               onclick="JavaScript:return confirm('确定要退出系统吗?')">退出登录</a>
        </div>
        <br/>
        <form action="${pageContext.request.contextPath}/admin/addDepartment.form" method="post" id="submitDepartment">
            <table border='1' cellpadding='0' cellspacing='0' class="prebook_result" style="font-size: 11pt;">
                <tr>
                    <td colspan="2">
                        <h3 style="color: #2FA8D4;text-align: center;">增加新网点信息</h3>
                        <span style="color:red;" id="error"></span>
                    </td>
                </tr>
                <tr>
                    <td>网点名称</td>
                    <td><input type="text" name="department_name" id="departmentName"/></td>
                </tr>
                <tr>
                    <td>网点地址</td>
                    <td><input type="text" name="department_address" id="departmentAddress"/></td>
                </tr>
                <tr>
                    <td>可办理业务</td>
                    <td style="text-align: left;">
                        <c:forEach items="${services}" var="service">
                            <input type="checkbox" name="selectedServiceId"
                                   value="${service.service_id}"/>${service.service_name}<br/>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input id="submitBut" type="button" value="添加新网点"/>&nbsp;&nbsp;
                        <a href="${pageContext.request.contextPath}/admin/departmentManager.form">返回</a>
                    </td>
                </tr>
            </table>
        </form>
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
