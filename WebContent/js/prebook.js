//点击网点时，把对应的申办业务查询并显示出来
function loadService(domE){
	var departmentId = $(domE).val();
	//alert('departmentId='+departmentId);

	//使用ajax与服务商交互，获取选定网点可办理的所有业务，并填充到相应的下拉框中
	//如果是-1，就是没选择网点，那就清空之前的网点
	if(departmentId == -1){
		$("#serviceType").empty();
		$("#serviceType").append("<option value='-1' >--请选择申办业务--</option>");
		return;
	}
	//发post请求查询出网点的所有业务
	$.post("findServiceByDepartmentId.form"
			,{"departmentId":departmentId}
			,function(data){
				//选清空里面数据
				$("#serviceType").empty();
				//如果查询不到数据是返回""字符串
				if(data == ""){
					$("#serviceType").append("<option value='-1'>无可办理业务</option>");
				}else{
					for(var i=0;i<data.length;i++){
						$("#serviceType").append("<option value='"+data[i].service_id+"'>"+data[i].service_name+"</option>");
					}
				}
			}
	);
}
//填充“预约日期”下拉框，可白提前五个工作日预约，不能预约周末日期
//function fillPrebookDate(){
//	$.post('getAvailableDate.form'
//			,function(data){
//				$('#preDate').empty();
//				for(var i=0; i<data.length;i++){
//					$('#preDate').append("<option value='"+data[i]+"' onclick='op("+i+");' id='op"+i+"' >"+data[i]+"</option>");
//				}
//			}
//	);
//}
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
	var input2 = $('#LXDH').val();//电话
	var input3 = $('#SLDW').val();//受理单位
	var input4 = $('#serviceType').val();//申办业务
	var input5 = $('#preDate').val();//预约日期
	var input6 = $('#YYSJ').val();//预约时间
	checkVerification();//验证码
	if(input1 != '' && input2 != '' && input3 != -1 && input4 != -1 && input5 != -1 && input6 != -1 && codeOk){
		return true;
	}
	$('#errorInfo').html('填写信息有误，请查询！');
	return false;
}


//更改预约记录状态
//function changePrebookStatus(pid,status){
//	status = status==0?1:0;//如果当前状态为已办理，要改成0未办理然后再传到服务端，否则改成1
//	$.get(
//		'modifyPrbookStatus',
//		{'prebookId':pid,'status':status},
//		function(data){
//			//如果返回值为1说明修改成功
//			if(data == 1){
//				if(status==1){//如果原来的状态为未办理，则把状态改为“已办理”
//					$('#status_'+pid).text('已办理');
//					$('#a_'+pid).attr('href','javascript:changePrebookStatus('+pid+',1);');
//				}
//				else{//如果原来的状态为已办理，则把状态改为“未办理”
//					$('#status_'+pid).text('未办理');
//					$('#a_'+pid).attr('href','javascript:changePrebookStatus('+pid+',0);');
//				}
//			}else if(data == -1){
//				alert('系统繁忙，请稍候再试');
//			}
//		}
//	);
//}

$(document).ready(function() {


    $("#yuyue").click(function() {
        //数据校验
		if(!check()) {
			return false;
		}

        $('#frm').ajaxSubmit(function(json) {
            if(json.code == '0000'){
                alert("预约成功，取号密码为：" + json.data.verification + " 请记取号密码！");
                window.location.href = contextPath + '/index.html';
            }else {
                alert(json.msg);
            }
        });
        return false;
    });
});
