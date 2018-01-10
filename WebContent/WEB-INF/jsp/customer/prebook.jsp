<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>北京市出入境网上预约</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/prebook.js"></script>
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
				<div class="mtop"></div>
				<div class="notice">
					<form name="bookingForm" method="post" action="${pageContext.request.contextPath}/customer/savePrebook.form" onsubmit="return check(this);" id="frm">
						<input type="hidden" name="token" value="${token}" />
						<div class="biaoge">
							<table width="752" border="0" cellspacing="0">
								<tbody><tr class="dibian" style="height:37px; border-bottom:1px solid #D1EAFA;">
									<td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
										<span>身份证号或台胞证号或护照号:</span>
									</td>
									<td style="width:209px; border:1px solid #D1EAFA;padding-left: 8px;">
										<div>
											<input type="text" name="passport_id" maxlength="18" value="" id="SFZH">
										</div>
									</td>
								</tr>
			
								<tr style="height:37px; border-bottom:1px solid #D1EAFA;">
									<td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
										<span>联系电话:</span>
									</td>
									<td style="width:209px; border-bottom:1px solid #D1EAFA;border-right:1px solid #D1EAFA;padding-left: 8px;">
										<div class="kuang">
											<input type="text" name="phone" id="LXDH">
										</div>
									</td>
								</tr>
			
								<tr style="height:37px; border-bottom:1px solid #D1EAFA;">
									<td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
										<span>受理单位:</span>
									</td>
									<td style="width:209px; border-bottom:1px solid #D1EAFA;border-right:1px solid #D1EAFA;padding-left: 8px;">
										<div class="xiala">
											<select name="department_id" id="SLDW" onchange="loadService(this);">
												<option value="-1">--请选择网点--</option>
												<c:forEach items="${departments}" var="department">
													<option value="${department.department_id}">${department.department_name}</option>
												</c:forEach>
											</select>
										</div>
									</td>
								</tr>
			
								<tr style="height:37px; border-bottom:1px solid #D1EAFA;">
									<td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
										<span>申办业务:</span>
									</td>
									<td style="width:209px; border-bottom:1px solid #D1EAFA;border-right:1px solid #D1EAFA;padding-left: 8px;">
										<div class="xiala">
											<select id="serviceType" name="service_id" >
												<option value="-1" >--请选择申办业务--</option>
											</select>
										</div>
									</td>
								</tr>
			
								<tr style="height:37px; border-bottom:1px solid #D1EAFA;">
									<td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
										<span>预约日期:</span>
									</td>
									<td style="width:209px; border-bottom:1px solid #D1EAFA;border-right:1px solid #D1EAFA;padding-left: 8px;">
										<div class="xiala">
											<select id="preDate" name="appointment_date" >
												<option value="-1" >--请选择预约日期--</option>
												<c:forEach items="${dates}" var="date">
													<option value="${date}" >${date}</option>
												</c:forEach>
											</select>
										</div>
									</td>
								</tr>
			
								<tr style="height:37px; border-bottom:1px solid #D1EAFA;">
									<td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
										<span>预约时间:</span>
									</td>
									<td style="width:209px; border-bottom:1px solid #D1EAFA;border-right:1px solid #D1EAFA;padding-left: 8px;">
										<div class="xiala">
											<select name="appointment_time" style="width:175px;" id="YYSJ">
												<option value="-1" selected="selected">--请选择时间--</option>
												<option value="09:30-10:30">09:30-10:30</option>
												<option value="10:30-11:30">10:30-11:30</option>
												<option value="14:30-15:30">14:30-15:30</option>
												<option value="15:30-16:30">15:30-16:30</option>
												<option value="15:30-16:30">15:30-16:30</option>
											</select>
										</div>
									</td>
								</tr>
			
								<tr style="height:37px; border-bottom:1px solid #D1EAFA;">
									<td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
										<span>验证码:</span>
									</td>
									<td style="width:209px; border-bottom:1px solid #D1EAFA;border-right:1px solid #D1EAFA;padding-left: 8px;">
										<div class="yanzheng">
												<input type="text" name="yzm" value="" id="vercode" onblur="checkVerification();"><img id="codeCheckResult"/>
												<img src="${pageContext.request.contextPath}/createImageCode.form" height="28" width="120" id="imgVcode"/>
												<a href="javascript:;" style="font-size: 10pt;" onclick="changeCode();">换一张</a>
										</div>
									</td>
								</tr>
			
								<tr style="height:37px; border-bottom:1px solid #D1EAFA;">
									<td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
										<span>相关提示:</span>
									</td>
									<td>&nbsp;&nbsp;
										<span id="errorInfo" style="color:red;"></span>
										<span id="showLoadImg4" style="display:none"> <img src="./addBooking.do_files/indicator1.gif"> </span>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<input id="yuyue" name="yuyue" type="submit" value="" onclick="setbookingform();">
										<a href="${pageContext.request.contextPath}/index.html"><input id="qxye" name="qxye" type="button"></a>
										<a href="${pageContext.request.contextPath}/customer/toSearchPrebook.form"><input id="cxye" name="cxye" type="button"></a>
									</td>
								</tr>
							</tbody>
						</table>
						</div>
					</form>
				</div>
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