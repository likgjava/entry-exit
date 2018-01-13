var codeOk = false;
//更换验证码
function changeCode() {
	$("#imgVcode").attr('src', '../createImageCode.form?m=' + Math.random());
}
//验证码检验
function checkVerification(){
	$('#errorInfo').html('');
	$('#codeCheckResult').attr('src', '');
	var code = $('#vercode').val();
	if(code == ''){
		$('#errorInfo').html('请输入验证码');
	}else{
		$.post('../validateCode.form',{"imageCode":code}
			,function(data){
				if(!data){
					$('#errorInfo').html('验证码错误');
				}else{
					codeOk = true;
					$('#codeCheckResult').attr('src', '/entry-exit/images/ok.png');
				}
			}
		);
	}
}
//检查表单是否填写正确
function check(){
	var input1 = $('#SFZH').val();//身份证
	var input2 = $('#QHMM').val();//取号密码
	checkVerification();//验证码
	if(input1 != '' && input2 != '' && codeOk){
		return true;
	}
	$('#errorInfo').html('填写信息有误，请查询！');
	return false;
}

$(document).ready(function() {


    $("#cxye").click(function() {
        //数据校验
        if(!check()) {
            return false;
        }

        $('#sear_form').ajaxSubmit(function(json) {
            if(json.code == '0000'){
				var prebook = json.data.prebook;
				$('#passport_id').html(prebook.passport_id);
				$('#phone').html(prebook.phone);
				$('#department_name').html(prebook.department_name);
				$('#service_name').html(prebook.service_name);
				$('#appointment_date').html(prebook.appointment_date);
				$('#appointment_time').html(prebook.appointment_time);
				$('#status').html(prebook.status==0 ? '未办理' : '已办理');
				$('#passport_id').html(prebook.passport_id);

				$('#prebookSearchDiv').hide();
				$('#prebookResultDiv').show();
            }else {
                alert(json.msg);
            }
        });
        return false;
    });
});