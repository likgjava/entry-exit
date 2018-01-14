package com.hyf.entryexit.base;

public class BaseErrorMsg {

    public static final BaseErrorMsg ERROR_SUCCESS = new BaseErrorMsg("0000", "success");
    public static final BaseErrorMsg ERROR_API_FAIL = new BaseErrorMsg("1001", "接口请求失败");
    public static final BaseErrorMsg ERROR_QUERY_PARAM = new BaseErrorMsg("1002", "查询参数有误");
    public static final BaseErrorMsg ERROR_PREBOOK_NOT_EXIST = new BaseErrorMsg("1003", "预约单不存在");
    public static final BaseErrorMsg ERROR_DEPARTMENT_IS_NULL = new BaseErrorMsg("1004", "请选择一个网点");
    public static final BaseErrorMsg ERROR_SAVE_PREBOOK_PASSPORT_ID_IS_NULL = new BaseErrorMsg("1005", "证件号不能为空");
    public static final BaseErrorMsg ERROR_SAVE_PREBOOK_PHONE_IS_NULL = new BaseErrorMsg("1006", "联系电话不能为空");
    public static final BaseErrorMsg ERROR_SAVE_PREBOOK_DEPARTMENT_IS_NULL = new BaseErrorMsg("1007", "受理单位不能为空");
    public static final BaseErrorMsg ERROR_SAVE_PREBOOK_SERVICE_IS_NULL = new BaseErrorMsg("1008", "申办业务不能为空");
    public static final BaseErrorMsg ERROR_SAVE_PREBOOK_DATE_IS_NULL = new BaseErrorMsg("1009", "预约日期不能为空");
    public static final BaseErrorMsg ERROR_SAVE_PREBOOK_TIME_IS_NULL = new BaseErrorMsg("1010", "预约时间不能为空");

    private String code;
    private String msg;

    public BaseErrorMsg() {
    }

    public BaseErrorMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public BaseErrorMsg setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public BaseErrorMsg setMsg(String msg) {
        this.msg = msg;
        return this;
    }

}
