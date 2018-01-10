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
