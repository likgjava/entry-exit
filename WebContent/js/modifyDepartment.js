var flag = {"departmentName":false,"departmentAddress":false};
//增加新网点信息
$(function(){
	//网点名称
	$('#departmentName').blur(function(){
		var departmentName = $('#departmentName').val();
		departmentName = departmentName.replace(/\s/g,'');//去除两边空格
		$('#error').html('');
		if(departmentName == ""){
			$('#error').html("网点名称不能为空！");
			return;
		}
		flag.departmentName = true;
	});
	//网点地址
	$('#departmentAddress').blur(function(){
		var departmentAddress = $('#departmentAddress').val();
		departmentAddress = departmentAddress.replace(/\s/g,'');//去除两边空格
		$('#error').html('');
		if(departmentAddress == ""){
			$('#error').html("网点地址不能为空！");
			return;
		}
		flag.departmentAddress = true;
	});
	//表单提交
	$("#submitDepartment").submit(function(){
		$('#departmentName').blur();
		$('#departmentAddress').blur();
		var ok = flag.departmentName && flag.departmentAddress;
		if(ok){
			return true;
		}
		return false;
	});
	
});









