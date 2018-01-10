<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>超级管理员登录</title>
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
				<div class="mtop"></div>
				<div class="notice"></div>
				<form action="${pageContext.request.contextPath}/superUserLogin.form" method="post">
					<span style="color: red;font-size: 15px;">${login_error}</span>
					<table class="index_table" border="1">
						<tr>
							<td>用户名</td>
							<td><input type="text" name="superName"></td>
						</tr>
						<tr>
							<td>密码</td>
							<td><input type="password" name="superPassword"></td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="submit" value="登录">
								<input type="button" value="返回首页" onclick="javascript:location.href='index.html';">
							</td>
						</tr>
					</table>
				</form>
				用户名/密码:admin/admin
			</div>
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