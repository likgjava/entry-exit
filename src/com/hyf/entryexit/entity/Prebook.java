package com.hyf.entryexit.entity;


/**
 * 预约记录表
 * @author HuangYongFeng
 *
 */
public class Prebook implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**预约记录的主键，自动增长*/
	private Integer prebook_id;
	/**身份证号或台胞证号或护照号*/
	private String passport_id;
	/**联系电话*/
	private String phone;
	/**受理单位的ID*/
	private Integer department_id;
	/**申办业务的ID*/
	private Integer service_id;
	/**预约日期*/
	private String appointment_date;
	/**预约时间，可选的时间段为：10:30-11:30(am1)、14:30-15:30(pm1)、15:30-16:30(pm2)、16:30-17:30(pm3)*/
	private String appointment_time;
	/**预约单状态：0为未受理，1为已办理*/
	private String status;
	/**取号密码*/
	private String verification;
	
	/**受理网点名*/
	private String department_name;//做关联
	/**业务类型信息名*/
	private String service_name;//做关联
	
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public Integer getPrebook_id() {
		return prebook_id;
	}
	public void setPrebook_id(Integer prebook_id) {
		this.prebook_id = prebook_id;
	}
	public String getPassport_id() {
		return passport_id;
	}
	public void setPassport_id(String passport_id) {
		this.passport_id = passport_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getAppointment_date() {
		return appointment_date;
	}
	public void setAppointment_date(String appointment_date) {
		this.appointment_date = appointment_date;
	}
	public String getAppointment_time() {
		return appointment_time;
	}
	public void setAppointment_time(String appointment_time) {
		this.appointment_time = appointment_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVerification() {
		return verification;
	}
	public void setVerification(String verification) {
		this.verification = verification;
	}
	
	

}
