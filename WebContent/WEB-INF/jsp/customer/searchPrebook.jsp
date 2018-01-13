<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>北京市出入境网上预约</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/searchPrebook.js"></script>
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
    <div id="prebookSearchDiv" class="container">
        <form name="bookingForm" method="post"
              action="${pageContext.request.contextPath}/customer/searchPrebook.form" onsubmit="return check();"
              id="sear_form">
            <div class="mtop"></div>
            <div class="biaoge" align="center">
                <table width="752" border="0" cellspacing="0">
                    <tbody>
                    <tr class="dibian" style="height:37px; border-bottom:1px solid #D1EAFA;">
                        <td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
                            <span>身份证号或台胞证号或护照号:</span>
                        </td>
                        <td style="width:209px; border-right:1px solid; border-top:1px solid; border-bottom:1px solid; border-color: #D1EAFA;">
                            <input type="text" name="passportId" maxlength="18" value="" id="SFZH">
                        </td>
                    </tr>
                    <tr style="height:37px; border-bottom:1px solid #D1EAFA;">
                        <td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
                            <span>取号密码:</span>
                        </td>
                        <td style="width:209px; border-right:1px solid; border-bottom:1px solid; border-color: #D1EAFA;">
                            <div class="kuang">
                                <input type="text" name="verification" value="" id="QHMM">
                            </div>
                        </td>
                    </tr>
                    <tr style="height:37px; border-bottom:1px solid #D1EAFA;">
                        <td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
                            <span>验证码:</span>
                        </td>
                        <td style="width:209px; border-right:1px solid; border-bottom:1px solid; border-color: #D1EAFA;">
                            <input type="text" name="yzm" value="" id="vercode" onblur="checkVerification();"><img
                                id="codeCheckResult"/>
                            <img src="${pageContext.request.contextPath}/createImageCode.form" height="28" width="120"
                                 id="imgVcode"/>
                            <a href="javascript:;" style="font-size: 10pt;" onclick="changeCode();">换一张</a>
                        </td>
                    </tr>
                    <tr style="height:37px; border-bottom:1px solid #D1EAFA;">
                        <td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
                            <span>相关提示:</span>
                        </td>
                        <td style="width:209px; border-right:1px solid; border-bottom:1px solid; border-color: #D1EAFA;">
                            <font id="errorInfo" color="red"></font>
                            <span id="showLoadImg4" style="display:none"> </span>
                        </td>
                    </tr>
                    <tr style="margin-top: 8px;">
                        <td colspan="2" align="center">
                            <a href="${pageContext.request.contextPath}/index.html"><input id="qxye" name="qxye"
                                                                                           type="button"></a>
                            <input id="cxye" name="cxye" type="button">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </form>
    </div>

    <div id="prebookResultDiv" class="container" align="center" style="display: none">
        <h3 style="color: green;text-align: center;">查询结果</h3>
        <table border='1' cellpadding='0' cellspacing='0' class="prebook_result">
            <tr>
                <td>证件号</td>
                <td>电话</td>
                <td>预约网点</td>
                <td>预约服务</td>
                <td>预约日期</td>
                <td>预约时间</td>
                <td>预约状态</td>
            </tr>
            <tr>
                <td id="passport_id">${prebook.passport_id}</td>
                <td id="phone">${prebook.phone}</td>
                <td id="department_name">${prebook.department_name}</td>
                <td id="service_name">${prebook.service_name}</td>
                <td id="appointment_date">${prebook.appointment_date} </td>
                <td id="appointment_time">${prebook.appointment_time}</td>
                <td id="status">
                    <c:if test="${prebook.status eq 0 }">未办理</c:if>
                    <c:if test="${prebook.status eq 1 }">已办理</c:if>
                </td>
            </tr>
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
<!--/Wrap-->
</body>
</html>
