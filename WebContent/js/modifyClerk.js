var flag = {"clerkUsername":false,"clerkPassword":false};
//修改业务员
$(function(){
	//业务名称
	$('#clerkUsername').blur(function(){
		var clerkUsername = $('#clerkUsername').val();
		clerkUsername = clerkUsername.replace(/\s/g,'');//去除两边空格
		$('#error').html('');
		if(clerkUsername == ""){
			$('#error').html("业务员名称不能为空！");
			return;
		}
		flag.clerkUsername = true;
	});
	//业务员密码
	$('#clerkPassword').blur(function(){
		var clerkPassword = $('#clerkPassword').val();
		clerkPassword = clerkPassword.replace(/\s/g,'');//去除两边空格
		$('#error').html('');
		if(clerkPassword == ""){
			$('#error').html("业务员密码不能为空！");
			return;
		}
		flag.clerkPassword = true;
	});
	//表单提交
	$("#submitClerk").submit(function(){
		$('#clerkUsername').blur();
		$('#clerkPassword').blur();
		var ok = flag.clerkUsername && flag.clerkPassword;
		if(ok){
			return true;
		}
		return false;
	});
	
});









