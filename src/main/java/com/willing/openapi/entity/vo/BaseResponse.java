package com.willing.openapi.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xiaozhou
 * <p>
 * </p>
 */
@Data
public class BaseResponse implements Serializable {

    private Integer code;

    private String msg;

    public boolean isSuccess() {
        return code == 0;
    }
}
