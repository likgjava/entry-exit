var flag = {"departmentName": false, "departmentAddress": false};

function deleteDepartmentService(departmentId, serviceId, service_name) {
    if (!confirm('确定要删除['+service_name+']业务吗？')){
        return false;
    }

    var url = contextPath + '/admin/deleteDepartmentService.form';
    $.post(url, {departmentId:departmentId, serviceId:serviceId}, function (json) {
        if(json.code == '0000'){
            //alert('删除成功！');
            window.location.href = contextPath + '/admin/toModifyDepartment.form?departmentId='+departmentId;
        }else {
            alert(json.msg);
        }
    });
}

//增加新网点信息
$(function () {
    //网点名称
    $('#departmentName').blur(function () {
        var departmentName = $('#departmentName').val();
        departmentName = departmentName.replace(/\s/g, '');//去除两边空格
        $('#error').html('');
        if (departmentName == "") {
            $('#error').html("网点名称不能为空！");
            return;
        }
        flag.departmentName = true;
    });
    //网点地址
    $('#departmentAddress').blur(function () {
        var departmentAddress = $('#departmentAddress').val();
        departmentAddress = departmentAddress.replace(/\s/g, '');//去除两边空格
        $('#error').html('');
        if (departmentAddress == "") {
            $('#error').html("网点地址不能为空！");
            return;
        }
        flag.departmentAddress = true;
    });

    $("#submitBut").click(function() {
        //数据校验
        $('#departmentName').blur();
        $('#departmentAddress').blur();
        var ok = flag.departmentName && flag.departmentAddress;
        if (!ok) {
            return false;
        }

        $('#submitDepartment').ajaxSubmit(function(json) {
            if(json.code == '0000'){
                alert('修改成功！');
                window.location.href = contextPath + '/admin/departmentManager.form';
            }else {
                alert(json.msg);
            }
        });
        return false;
    });

});









