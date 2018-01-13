<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.hyf.entryexit.entity.*" %>
<% Clerk clerk = (Clerk) session.getAttribute("clerk"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>处理预约记录--北京市出入境网上预约</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/prebook.js"></script>
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
        <div class="login_user">
            您好，业务员&nbsp;<span style="color: blue;"><%=clerk.getClerk_username()%></span>&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/clerkLoginOut.form"
               onclick="JavaScript:return confirm('确定要退出系统吗?')">退出登录</a>
        </div>
        <h3 style="color: green;text-align: center;">预约单管理</h3>
        <table border='1' cellpadding='0' cellspacing='0' class="prebook_result">
            <tr>
                <td>序号</td>
                <td>证件号</td>
                <td>电话</td>
                <td>预约服务</td>
                <td>预约日期</td>
                <td>预约时间</td>
                <td>预约状态</td>
                <td>操作</td>
            </tr>
            <tbody id="prebookListTbody">
            </tbody>
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
    function changeStatus(prebookId, status, passport_id) {
        if (!confirm('确定要更改'+passport_id+'的预约状态吗？')){
            return false;
        }

        status = (status==0 ? 1 : 0);
        var url = contextPath + '/salesman/modifyPrebook.form';
        $.post(url, {prebookId:prebookId, status:status}, function (json) {
            if(json.code == '0000'){
                alert('更改成功！');
                window.location.href = contextPath + '/salesman/toModifyPrebook.form';
            }else {
                alert(json.msg);
            }
        });
    }

    $(document).ready(function() {

        var clerkId = '${clerkId}';
        var url = contextPath + '/salesman/listPrebook.form';
        $.post(url, {clerkId:clerkId}, function (json) {
            if(json.code == '0000'){
                var prebookList = json.data.prebookList;
                var html;
                for(var i=0; i<prebookList.length; i++) {
                    var prebook = prebookList[i];
                    html += '<tr>';
                    html += '<td>'+(i+1)+'</td>';
                    html += '<td>'+prebook.passport_id+'</td>';
                    html += '<td>'+prebook.phone+'</td>';
                    html += '<td>'+prebook.service_name+'</td>';
                    html += '<td>'+prebook.appointment_date+'</td>';
                    html += '<td>'+prebook.appointment_time+'</td>';
                    html += '<td>'+(prebook.status==0 ? '未办理' : '已办理')+'</td>';
                    html += '<td><a href="javascript:;" onclick="changeStatus('+prebook.prebook_id+','+prebook.status+','+prebook.passport_id+')">更改状态</a></td>';
                    html += '</tr>';
                }
                $('#prebookListTbody').html(html);

                //$('#departmentInfoDiv').show();
            }else {
                alert(json.msg);
            }
        });
    });
</script>
</html>
