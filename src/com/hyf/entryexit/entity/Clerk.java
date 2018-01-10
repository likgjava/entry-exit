package com.hyf.entryexit.entity;
/**
 * 业务员信息表
 * @author HuangYongFeng
 */
public class Clerk implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**业务员的ID*/
	private Integer clerk_id;
	/**业务员的名称*/
	private String clerk_username;
	/**业务员的名称*/
	private String clerk_password;
	/**业务员的名称*/
	private Integer department_id;
	
	/**业务员在的网点信息*/
	private Department department;
	
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Integer getClerk_id() {
		return clerk_id;
	}
	public void setClerk_id(Integer clerk_id) {
		this.clerk_id = clerk_id;
	}
	public String getClerk_username() {
		return clerk_username;
	}
	public void setClerk_username(String clerk_username) {
		this.clerk_username = clerk_username;
	}
	public String getClerk_password() {
		return clerk_password;
	}
	public void setClerk_password(String clerk_password) {
		this.clerk_password = clerk_password;
	}
	public Integer getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	
	


}
