package com.hyf.entryexit.entity;
/**
 * 网点可办理的业务表，N:N关系表
 * @author HuangYongFeng
 */
public class DepartmentService implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**表记录的主键，自动增长*/
	private Integer id;
	/**网点的ID*/
	private Integer department_id;
	/**服务类型的ID*/
	private Integer service_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public Integer getService_id() {
		return service_id;
	}
	public void setService_id(Integer service_id) {
		this.service_id = service_id;
	}
	
	
}
