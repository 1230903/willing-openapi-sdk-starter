package com.willing.openapi.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@Data
public class BaseResponse implements Serializable {

    private Integer code;

    private String msg;
}
