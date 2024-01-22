package com.willing.openapi.base;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @author xiaozhou
 * <p>
 * </p>
 */
@Data
@ConfigurationProperties(prefix = "willing.openapi")
public class WillingOpenapiConfigProperties {

    /**
     * willing-开放平台-指定应用的appId
     */
    private String appId;

    /**
     * willing-开放平台-指定应用的appSecret
     */
    private String appSecret;

    /**
     * willingOpenapi服务地址
     */
    private String url;
}
