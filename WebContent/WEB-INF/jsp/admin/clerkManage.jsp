<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.hyf.entryexit.entity.*" %>
<% SuperUser superUser = (SuperUser)session.getAttribute("superUser"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>业务员管理--北京市出入境网上预约</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
		<STYLE type="text/css">
			.edit_td{
				font-size: 10pt;
			}
		</STYLE>
	</head>

	<body>
		<div class="wrap">
			<div class="top">
				<img src="${pageContext.request.contextPath}/images/banner.jpg" alt="出入境管理办事大厅" width="100%" height="100%" />

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
					<a href="${pageContext.request.contextPath}/superUserLoginOut.form" onclick="JavaScript:return confirm('确定要退出系统吗?')">退出登录</a>
				</div><br/>
				<h3 style="color: green;text-align: center;">业务员管理</h3>
				<table border='1' cellpadding='0' cellspacing='0' class="prebook_result" style="font-size: 11pt;">
					<tr>
						<td colspan="5">
							<a class="newdept" href="${pageContext.request.contextPath}/admin/toAddClerk.form">
							<img alt="new" src="${pageContext.request.contextPath}/images/plus.png" width="20" height="20">添加业务员</a>
						</td>
					</tr>
					<tr style="font-weight: bold;">
						<td>序号</td><td width="125">业务员</td><td width="325">所属网点</td><td>操作</td>
					</tr>
					<c:forEach items="${clerks}" var="clerk" varStatus="i">
						<tr id="clerk_${clerk.clerk_id}">
							<td>${i.count}</td>
							<td>${clerk.clerk_username}</td>
							<td>${clerk.department.department_name}</td>
							<td class="edit_td"><a href="${pageContext.request.contextPath}/admin/toModifyClerk.form?clerkId=${clerk.clerk_id}">编辑</a>&nbsp;
							<a href="${pageContext.request.contextPath}/admin/deleteClerk.form?clerkId=${clerk.clerk_id}" onclick="javascript: return confirm('你确定的要删除   ${clerk.clerk_username} 业务员记录？')">删除</a>
							</td>
						</tr>
					</c:forEach>
				</table><br/><br/>
				<p style="text-align: center;font-size: 11pt;"><a href="${pageContext.request.contextPath}/index.html">返回首页</a></p>
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
