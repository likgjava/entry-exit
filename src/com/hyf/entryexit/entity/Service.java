package com.hyf.entryexit.entity;

/**
 * 业务类型信息表
 * @author HuangYongFeng
 *
 */
public class Service implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**业务类型ID*/
	private Integer service_id;
	/**业务名称*/
	private String service_name;
	
	public Integer getService_id() {
		return service_id;
	}
	public void setService_id(Integer service_id) {
		this.service_id = service_id;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	
	
	
}
