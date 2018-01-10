package com.hyf.entryexit.entity;

import java.util.List;

/**
 * 受理网点表
 * @author HuangYongFeng
 */
public class Department implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**网点ID，自动增长*/
	private Integer department_id;
	/**网点名称*/
	private String department_name;
	/**网点地址*/
	private String department_address;
	/**
	 * 关联属性，用于封装对应的一组业务类型 ，此关联属性的值是MyBatis在自动查询后装配进来的；
	 */
	/** 一个网点 可以有多个业务类型  */
	private List<Service> services;
	/**每个网点有多少人还没办理业务的  预约单状态：0为未受理，1为已办理*/
	private Integer queuingNum;
	
	
	public Integer getQueuingNum() {
		return queuingNum;
	}
	public void setQueuingNum(Integer queuingNum) {
		this.queuingNum = queuingNum;
	}
	public List<Service> getServices() {
		return services;
	}
	public void setServices(List<Service> services) {
		this.services = services;
	}
	
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getDepartment_address() {
		return department_address;
	}
	public void setDepartment_address(String department_address) {
		this.department_address = department_address;
	}
	
	


}
