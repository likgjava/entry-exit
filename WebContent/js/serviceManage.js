//添加新业务，使用ajax请求。添加成功后在原页面的表格上添加新数据
function newService(maxRow){
	maxRow = maxRow+1;
	var serviceName = prompt('请输入新业务名称','');//window对象的方法，弹出一个框
	if(serviceName == '' || serviceName==null){
		return;
	}
	window.location.href="addService.form?service_name="+serviceName;
}
//更新业务信息(修改业务名称)
function updateService(serviceId, serviceName){
	serviceName = prompt('请输入业务名称',serviceName);
	if(serviceName == '' || serviceName == null)
		return;
	window.location.href="modifyService.form?service_name="+serviceName+"&service_id="+serviceId;
}



