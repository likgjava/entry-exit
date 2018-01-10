<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>北京市出入境网上预约</title>
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
			<div class="container">
				<img alt="bg" src="${pageContext.request.contextPath}/images/main_bg.png" style="text-align: center;margin-bottom: 80px; margin-left: 240px;">
				<div align="center" style="margin-bottom: 50px;">
					<h3>${msg}
						<c:if test="${msg == null}">
							<span style="color: red;">系统繁忙，请稍后重试！谢谢！</span>
						</c:if>
					</h3>
					<p><a href="${pageContext.request.contextPath}/index.html">返回首页</a></p>
				</div>
			</div>
			<!--/Container-->
			<div class="foot">
				<p>
					北京市公安局出入境管理处
				</p>
				<p>
					信息产业部备案号：粤ICP备0332491号
				</p>
			</div>
			<!--/Foot-->
		</div>
		<!--/Wrap-->
	</body>
</html>