package com.willing.openapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * 异常错误码
 *
 * @author sunyong
 * @date 2021/1/15
 */
@AllArgsConstructor
@Getter
public enum ExceptionEnum {
    // 预留1000-30000
    FORBIDDEN(403, "无访问权限"),
    USER_NOT_EXIST(404, "员工不存在"),
    SERVER_ERROR(500, "服务器内部错误"),
    CODE_PROBLEM(500, "代码错误"),

    // 通用
    PARAM_MISS(1000, "参数缺失"),
    PARAM_NOT_VALID(1001, "不合法的参数"),
    PARAM_WRONG_FORMAT(1013, "参数格式或值错误"),

    WILLING_ENCRYPTION_AND_DECRYPTION_FAILED(1014, "卫瓴加解密失败"),
    WILLING_ENCRYPTION_AND_DECRYPTION_DATA_EXCEPTION(1015, "卫瓴加解密数据异常"),

    WILLING_CURSOR_INVALID(1016, "游标参数错误"),

    /**
     * rpc异常
     */
    CODE_1017(1017, "服务器繁忙，请稍后重试"),

    DATA_EXIST(1002, "数据已存在"),
    DATA_NOT_EXIST(1003, "数据不存在"),
    REPEAT_REQUEST(1004, "重复操作"),
    REQUEST_FAILED(1005, "操作失败"),
    EMPTY_DATA(1006, "数据加载完毕"),
    ILLEGAL_REQ(1007, "非法访问"),
    CODE_1008(1008, "该Ip已被禁用"),
    CODE_1009(1009, "接口调用超过频率限制"),
    CODE_1010(1010, "不支持的请求方法"),
    CODE_1011(1011, "body参数解析异常"),
    CODE_1012(1012, "数据超过限制"),
    CODE_1013(1009, "接口短期调用超过频率限制，请稍后再试"),
    CODE_2000(2000, "回调地址不合法，请检查是否是可访问的地址"),
    CODE_2001(2001, "回调地址校验不通过"),
    CODE_3000(3000, "客户不存在"),
    CODE_4000(4000, "群聊不存在或已解散"),
    CODE_4100(4100, "应用数超过限制"),


    ILLEGAL_USER(3001, "员工未激活"),
    DIMISSION_USER(3002, "员工已离职"),

    FILE_NOT_SUPPORT(6001, "文件格式不支持");

    private final int code;
    private final String msg;


    /**
     * 通过code查找异常枚举
     *
     * @param code 代码
     * @return {@link ExceptionEnum}
     */
    public static ExceptionEnum getByCode(Integer code) {
        Optional<ExceptionEnum> exceptionEnum = Arrays.stream(ExceptionEnum.values())
                .filter(e -> e.getCode() == code)
                .findFirst();
        return exceptionEnum.orElse(null);
    }
}
