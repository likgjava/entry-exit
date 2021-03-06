<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.hyf.entryexit.entity.*" %>
<% SuperUser superUser = (SuperUser) session.getAttribute("superUser"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>网点信息管理--北京市出入境网上预约</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript">var contextPath = '${pageContext.request.contextPath}';</script>
    <STYLE type="text/css">
        .edit_td {
            font-size: 11pt;
        }
    </STYLE>
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
        <div class="login_user">
            <span>您好，管理员&nbsp;<span style="color: blue;"><%=superUser.getSuper_name()%></span>&nbsp;&nbsp;</span>
            <a href="${pageContext.request.contextPath}/superUserLoginOut.form"
               onclick="JavaScript:return confirm('确定要退出系统吗?')">退出登录</a>
        </div>
        <br/>
        <h3 style="color: green;text-align: center;">网点信息管理</h3>
        <table border='1' cellpadding='0' cellspacing='0' class="prebook_result" style="font-size: 11pt;">
            <tr>
                <td colspan="5">
                    <a class="newdept" href="${pageContext.request.contextPath}/admin/toAddDepartment.form"><img
                            alt="new" src="${pageContext.request.contextPath}/images/plus.png" width="20" height="20">新增网点</a>
                </td>
            </tr>
            <tr>
                <td>序号</td>
                <td>网点名称</td>
                <td>网点地址</td>
                <td>可办理业务</td>
                <td>操作</td>
            </tr>
            <tbody id="departmentListTbody"></tbody>
        </table>
        <br/><br/>
        <p style="text-align: center;font-size: 11pt;"><a href="${pageContext.request.contextPath}/index.html">返回首页</a>
        </p>
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
</body>
<script type="text/javascript">
    function delDepartment(departmentId, department_name) {
        if (!confirm('确定要删除['+department_name+']网点吗？')){
            return false;
        }

        var url = contextPath + '/admin/deleteDepartment.form';
        $.post(url, {departmentId:departmentId}, function (json) {
            if(json.code == '0000'){
                alert('删除成功！');
                window.location.href = contextPath + '/admin/departmentManager.form';
            }else {
                alert(json.msg);
            }
        });
    }

    $(document).ready(function() {

        var url = contextPath + '/admin/listDepartment.form';
        $.post(url, {}, function (json) {
            if(json.code == '0000'){
                var departmentList = json.data.departmentList;
                var html;
                for(var i=0; i<departmentList.length; i++) {
                    var department = departmentList[i];
                    html += '<tr>';
                    html += '<td>'+(i+1)+'</td>';
                    html += '<td>'+department.department_name+'</td>';
                    html += '<td>'+department.department_address+'</td>';
                    var departmentServices = [];
                    $.each(department.services, function(i, service){
                        departmentServices.push(service.service_name);
                    });
                    html += '<td>'+departmentServices.join('<br/>')+'</td>';
                    html += '<td>';
                    html += '<a href="'+contextPath+'/admin/toModifyDepartment.form?departmentId='+department.department_id+'">编辑</a>';
                    html += '&nbsp;&nbsp;';
                    html += '<a href="javascript:;" onclick="delDepartment('+department.department_id+',\''+department.department_name+'\')">删除</a>';
                    html += '</td>';
                    html += '</tr>';
                }
                $('#departmentListTbody').html(html);
            }else {
                alert(json.msg);
            }
        });
    });
</script>
</html>
