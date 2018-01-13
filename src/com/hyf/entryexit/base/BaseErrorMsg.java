package com.hyf.entryexit.base;

public class BaseErrorMsg {

    public static final BaseErrorMsg ERROR_SUCCESS = new BaseErrorMsg("0000", "success");
    public static final BaseErrorMsg ERROR_API_FAIL = new BaseErrorMsg("1001", "接口请求失败");
    public static final BaseErrorMsg ERROR_QUERY_PARAM = new BaseErrorMsg("1002", "查询参数有误");
    public static final BaseErrorMsg ERROR_PREBOOK_NOT_EXIST = new BaseErrorMsg("1003", "预约单不存在");

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
