<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>北京市出入境网上预约</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.2.js"></script>
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
        <div class="mtop"></div>
        <form action="${pageContext.request.contextPath}/customer/departmentSearchResult.form" method="post" id="sear_form">
            <div class="biaoge" align="center">
                <table width="752" border="0" cellspacing="0">
                    <tbody>
                    <tr class="dibian" style="height:37px; border-bottom:1px solid #D1EAFA;">
                        <td style="width:209px; border-right:1px solid #D1EAFA; background:#F1F1F1;text-align: right;">
                            <span>请选择网点：</span>
                        </td>
                        <td style="width:209px; border-right:1px solid; border-top:1px solid; border-bottom:1px solid; border-color: #D1EAFA;">
                            <select id="department_id" name="department_id">
                                <option value="-1">--请选择网点--</option>
                                <c:forEach items="${departments}" var="department">
                                    <option value="${department.department_id}">${department.department_name}</option>
                                </c:forEach>
                            </select>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="button" value="查询网点" onclick="searchDepartmentInfo()">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <br/>

            <div id="departmentInfoDiv" style="display: none;">
                <table border='1' cellpadding='0' cellspacing='0' class="prebook_result" style="font-size: 11pt;">
                    <tr>
                        <td>网点名称</td>
                        <td>网点地址</td>
                        <td>可办理业务</td>
                        <td>排队状况</td>
                    </tr>
                    <tr>
                        <td id="departmentName"></td>
                        <td id="departmentAddress"></td>
                        <td id="departmentServices" align="left"></td>
                        <td>该网点目前共有<span style="color:red;font-weight:bold;" id="departmentQueuingNum"></span>人排队</td>
                    </tr>
                </table>
            </div>

        </form>

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
</body>
<script type="text/javascript">
function searchDepartmentInfo() {
    var department_id = $('#department_id').val();
    if (department_id == -1) {
        alert('请选择网点！');
        return;
    }

    var url = contextPath + '/customer/searchDepartmentInfo.form';
    $.post(url, {department_id:department_id}, function (json) {
        if(json.code == '0000'){
            var department = json.data.department;
            $('#departmentName').html(department.department_name);
            $('#departmentAddress').html(department.department_address);
            var departmentServices = [];
            $.each(department.services, function(i, service){
                departmentServices.push(service.service_name);
            });
            $('#departmentServices').html(departmentServices.join('<br/>'));
            $('#departmentQueuingNum').html(department.queuingNum);

            $('#departmentInfoDiv').show();
        }else {
            alert(json.msg);
        }
    });
}
</script>
</html>