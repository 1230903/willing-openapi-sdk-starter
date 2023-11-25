package com.willing.openapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常错误码
 *
 * @author sunyong
 * @date 2021/1/15
 */
@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    FORBIDDEN(403, "无访问权限"),

    SERVER_ERROR(500, "服务繁忙，请稍后重试"),
    APP_ID_OR_APP_SECRET_INVALID(501, "app_id或app_secret不合法,请检查配置"),

    PARAM_MISS(1000, "参数缺失"),
    PARAM_NOT_VALID(1001, "不合法的参数"),
    DATA_EXIST(1002, "数据已存在"),
    DATA_NOT_EXIST(1003, "数据不存在"),
    REPEAT_REQUEST(1004, "重复操作"),
    REQUEST_FAILED(1005, "操作失败"),
    EMPTY_DATA(1006, "数据加载完毕"),
    ILLEGAL_REQ(1007, "非法访问"),
    IP_FORBIDDEN(1008, "该Ip已被禁用"),
    REQUEST_UNSUPPORTED(1010, "不支持的请求方法"),
    BODY_EXCEPTION(1011, "body参数解析异常"),
    DATA_OUT_OF_LIMIT(1012, "数据超过限制"),
    PARAM_WRONG_FORMAT(1013, "参数格式或值错误"),
    CURSOR_INVALID(1014, "游标参数错误"),
    CODE_1015(1015, "接口调用超过频率限制，请稍后再试"),

    ;

    private final int code;
    private final String msg;
}
