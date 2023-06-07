package com.willing.openapi.bean;

import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.interceptor.Interceptor;
import com.willing.openapi.base.WillingOpenapiAccessTokenProperties;
import lombok.extern.slf4j.Slf4j;


/**
 * @author xiaozhou
 * @date 2023/6/7
 * <p>
 * </p>
 */
@Slf4j
public class SimpleCustomInterceptor implements Interceptor<Object> {

    private WillingOpenapiAccessTokenProperties properties;

    private static final String URL_FORMAT = "%s/%s";

    public SimpleCustomInterceptor(WillingOpenapiAccessTokenProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean beforeExecute(ForestRequest request) {
        String url = String.format(URL_FORMAT, properties.getUrl(), request.getUrl());
        if (!url.contains("access_token")) {
            request.addQuery("access_token", "");
        }
        return true;
    }
}
