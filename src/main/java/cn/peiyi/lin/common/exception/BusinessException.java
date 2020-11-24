package cn.peiyi.lin.common.exception;

import cn.peiyi.lin.common.base.Constant;

public class BusinessException extends RuntimeException{

    private String code;

    public BusinessException() {}

    public BusinessException(String message) {
        super(message);
        this.code = Constant.REP_CODE_FAIL;
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
