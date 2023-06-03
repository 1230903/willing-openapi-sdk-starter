package com.willing.openapi.config;


import com.willing.openapi.base.WillingAccessTokenInterceptor;
import com.willing.openapi.service.impl.WillingOpnenapiService;
import com.willing.openapi.base.WillingOpenapiAccessTokenProperties;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@Configuration
@EnableConfigurationProperties(WillingOpenapiAccessTokenProperties.class)
public class OpenapiAccessTokenAutoConfiguration {

    private WillingOpenapiAccessTokenProperties properties;

    @Bean
    @ConditionalOnMissingBean(WillingOpnenapiService.class)
    public WillingOpnenapiService willingOpnenapiService(WillingOpenapiAccessTokenProperties properties) {
        this.properties = properties;
        return new WillingOpnenapiService(properties);
    }

    @Bean
    @ConditionalOnMissingBean(WillingOpenapiAccessTokenProperties.class)
    public WillingAccessTokenInterceptor willingAccessTokenInterceptor(@Autowired(required = false) OkHttpClient okHttpClient) throws IOException {
        return new WillingAccessTokenInterceptor(okHttpClient, properties);
    }
}
