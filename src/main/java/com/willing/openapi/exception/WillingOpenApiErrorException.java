package com.willing.openapi.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WillingOpenApiErrorException extends RuntimeException {
    private int code;
    private String msg;
    /**
     * 详细信息
     */
    private String details;

    public WillingOpenApiErrorException(ExceptionEnum errCode) {
        super(errCode.getMsg());
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
        this.details = msg;
    }

    public WillingOpenApiErrorException(ExceptionEnum errCode, String details) {
        super(String.format("%d,%s,%s", errCode.getCode(), errCode.getMsg(), details));
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
        this.details = details;
    }
}
