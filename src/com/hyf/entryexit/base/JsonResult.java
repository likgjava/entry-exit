package com.hyf.entryexit.base;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于传递AJAX请求的返回结果
 * @author likaige
 * @create 2015年8月20日 上午10:03:30
 */
public class JsonResult {

	/**
	 * 错误码
	 */
	private String code;
	
	/**
	 * 错误描述
	 */
	private String msg;

	/**
	 *
	 */
	private Map<String, Object> data = new HashMap<>();
	
	/**
	 * 生成操作成功的信息
	 * @return
	 * @author likaige
	 * @create 2015年8月20日 上午10:12:05
	 */
	public static JsonResult getInstance(){
		JsonResult json = new JsonResult();
		json.setCode(BaseErrorMsg.ERROR_SUCCESS.getCode());
		json.setMsg(BaseErrorMsg.ERROR_SUCCESS.getMsg());
		return json;
	}

	public static JsonResult getFailResult(BaseErrorMsg errorMsg) {
		JsonResult json = new JsonResult();
		json.setCode(errorMsg.getCode());
		json.setMsg(errorMsg.getMsg());
		return json;
	}

	public static JsonResult getFailResult(BaseErrorMsg errorMsg, Object... msgParam) {
		JsonResult json = new JsonResult();
		json.setCode(errorMsg.getCode());
		json.setMsg(String.format(errorMsg.getMsg(), msgParam));
		return json;
	}
	
	/**
	 * 生成操作成功的信息
	 * @return
	 * @author likaige
	 * @create 2015年8月20日 上午10:12:05
	 */
	public static JsonResult getFailResult(String result){
		JsonResult json = new JsonResult();
//		json.setSuccess(false);
//		json.setResult(result);
		return json;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
}
