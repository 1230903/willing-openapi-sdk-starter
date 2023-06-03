package com.willing.openapi.service.impl;

import com.willing.openapi.base.WillingOpenapiAccessTokenProperties;
import com.willing.openapi.service.iface.WillingOpenApi;

/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
public class WillingOpnenapiService {

    private WillingOpenApi willingOpenApi;

    /**
     * willing-开放平台-指定应用的appId
     */
    private final String appId;

    /**
     * willing-开放平台-指定应用的appSecret
     */
    private final String appSecret;

    /**
     * willingOpenapi服务地址
     */
    private final String url;

    public WillingOpnenapiService(WillingOpenapiAccessTokenProperties properties) {
        this.appId = properties.getAppId();
        this.appSecret = properties.getAppSecret();
        this.url = properties.getUrl();
    }


    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getUrl() {
        return url;
    }
    public WillingOpenApi getWillingOpenApi() {
        return willingOpenApi;
    }
}
