package com.hyf.entryexit.entity;
/**
 * 管理员信息表
 * @author HuangYongFeng
 *
 */
public class SuperUser implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**管理员ID*/
	private Integer super_id;
	/**管理员名称*/
	private String super_name;
	/**管理员登录密码*/
	private String super_pwd;
	
	public Integer getSuper_id() {
		return super_id;
	}
	public void setSuper_id(Integer super_id) {
		this.super_id = super_id;
	}
	public String getSuper_name() {
		return super_name;
	}
	public void setSuper_name(String super_name) {
		this.super_name = super_name;
	}
	public String getSuper_pwd() {
		return super_pwd;
	}
	public void setSuper_pwd(String super_pwd) {
		this.super_pwd = super_pwd;
	}

	

}
