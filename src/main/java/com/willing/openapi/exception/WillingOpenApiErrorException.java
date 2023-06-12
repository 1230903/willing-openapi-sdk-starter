package com.willing.openapi.exception;

import lombok.Getter;
import lombok.ToString;

import java.io.IOException;

@Getter
@ToString
public class WillingOpenApiErrorException extends RuntimeException {
    private final int code;
    private final String msg;
    /**
     * 详细信息
     */
    private final String details;

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
