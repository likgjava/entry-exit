//添加新业务，使用ajax请求。添加成功后在原页面的表格上添加新数据
function newService(maxRow) {
    maxRow = maxRow + 1;
    var serviceName = prompt('请输入新业务名称', '');//window对象的方法，弹出一个框
    if (serviceName == '' || serviceName == null) {
        return;
    }

    var url = contextPath + '/admin/addService.form';
    $.post(url, {service_name:serviceName}, function (json) {
        if(json.code == '0000'){
            window.location.href = contextPath + '/admin/serviceManage.form';
        }else {
            alert(json.msg);
        }
    });
}

//更新业务信息(修改业务名称)
function updateService(serviceId, serviceName) {
    serviceName = prompt('请输入业务名称', serviceName);
    if (serviceName == '' || serviceName == null) {
        return;
    }

    var url = contextPath + '/admin/modifyService.form';
    $.post(url, {service_id:serviceId, service_name:serviceName}, function (json) {
        if(json.code == '0000'){
            window.location.href = contextPath + '/admin/serviceManage.form';
        }else {
            alert(json.msg);
        }
    });
}

function deleteService(service_id, department_name) {
    if (!confirm('确定要删除['+department_name+']业务吗？')){
        return false;
    }

    var url = contextPath + '/admin/deleteService.form';
    $.post(url, {serviceId:service_id}, function (json) {
        if(json.code == '0000'){
            alert('删除成功！');
            window.location.href = contextPath + '/admin/serviceManage.form';
        }else {
            alert(json.msg);
        }
    });
}

$(document).ready(function() {

    var url = contextPath + '/admin/listService.form';
    $.post(url, {}, function (json) {
        if(json.code == '0000'){
            var serviceList = json.data.serviceList;
            var html;
            for(var i=0; i<serviceList.length; i++) {
                var service = serviceList[i];
                html += '<tr>';
                html += '<td>'+(i+1)+'</td>';
                html += '<td>'+service.service_name+'</td>';
                html += '<td>';
                html += '<a href="javascript:updateService('+service.service_id+',\''+service.service_name+'\');">编辑</a>';
                html += '&nbsp;&nbsp;';
                html += '<a href="javascript:;" onclick="deleteService('+service.service_id+',\''+service.service_name+'\')">删除</a>';
                html += '</td>';
                html += '</tr>';
            }
            $('#serviceListTbody').html(html);
        }else {
            alert(json.msg);
        }
    });
});

