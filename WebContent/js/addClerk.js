var flag = {"clerkUsername": false, "clerkPassword": false, "departmentId": false};
//增加业务员
$(function () {
    //业务名称
    $('#clerkUsername').blur(function () {
        var clerkUsername = $('#clerkUsername').val();
        clerkUsername = clerkUsername.replace(/\s/g, '');//去除两边空格
        $('#error').html('');
        if (clerkUsername == "") {
            $('#error').html("业务员名称不能为空！");
            return;
        }
        flag.clerkUsername = true;
    });
    //业务员密码
    $('#clerkPassword').blur(function () {
        var clerkPassword = $('#clerkPassword').val();
        clerkPassword = clerkPassword.replace(/\s/g, '');//去除两边空格
        $('#error').html('');
        if (clerkPassword == "") {
            $('#error').html("业务员密码不能为空！");
            return;
        }
        flag.clerkPassword = true;
    });
    //业务员网点
    $('#departmentId').change(function () {
        // 1、当前选中的option的value属性
        var departmentId = $('#departmentId').val();
        departmentId = departmentId.replace(/\s/g, '');//去除两边空格
        $('#error').html('');
        if (departmentId == -1) {
            $('#error').html("请选择网点！");
            return;
        }
        flag.departmentId = true;
    });
    //表单提交
    /*$("#submitClerk").submit(function () {
        $('#clerkUsername').blur();
        $('#clerkPassword').blur();
        $('#departmentId').change();
        var ok = flag.clerkUsername && flag.clerkPassword && flag.departmentId;
        if (ok) {
            return true;
        }
        return false;
    });*/

    $("#submitBut").click(function() {
        //数据校验
        $('#clerkUsername').blur();
        $('#clerkPassword').blur();
        $('#departmentId').change();
        var ok = flag.clerkUsername && flag.clerkPassword && flag.departmentId;
        if(!ok) {
            return false;
        }

        $('#submitClerk').ajaxSubmit(function(json) {
            if(json.code == '0000'){
                alert('添加成功！');
                window.location.href = contextPath + '/admin/clerkManage.form';
            }else {
                alert(json.msg);
            }
        });
        return false;
    });

});









