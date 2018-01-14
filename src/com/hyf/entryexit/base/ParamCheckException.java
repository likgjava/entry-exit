package com.hyf.entryexit.base;

public class ParamCheckException extends RuntimeException {

    private BaseErrorMsg errorMsg;

    public ParamCheckException() {
        super();
    }

    public ParamCheckException(BaseErrorMsg errorMsg) {
        this.errorMsg = errorMsg;
    }

    public BaseErrorMsg getErrorMsg() {
        return errorMsg;
    }
}
