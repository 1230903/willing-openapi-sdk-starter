package com.willing.openapi.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author xiaozhou
 * <p>
 * 获取授权token的响应
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthTokenResponse extends BaseResponse implements Serializable {

    private AuthTokenDTO data;

    @Data
    public static class AuthTokenDTO {

        private String accessToken;

        private String expiresIn;
    }

}
