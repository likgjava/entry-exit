<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>预约查询结果--北京市出入境网上预约</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
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
				<h3 style="color: green;text-align: center;">查询结果</h3>
				<table border='1' cellpadding='0' cellspacing='0' class="prebook_result">
					<tr>
						<td>证件号</td><td>电话</td><td>预约网点</td><td>预约服务</td><td>预约日期</td><td>预约时间</td><td>预约状态</td>
					</tr>
					<tr>
						<td>${prebook.passport_id}</td>
						<td>${prebook.phone}</td>
						<td>${prebook.department_name}</td>
						<td>${prebook.service_name}</td>
						<td>${prebook.appointment_date} </td>
						<td>${prebook.appointment_time}</td>
						<td>
							<c:if test="${prebook.status eq 0 }">未办理</c:if>
							<c:if test="${prebook.status eq 1 }">已办理</c:if>
						</td>
					</tr>
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
