package com.willing.openapi.config;

import com.willing.openapi.base.WillingOpenapiAccessTokenProperties;
import com.willing.openapi.service.impl.WillingOpenapiService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@Configuration
@EnableConfigurationProperties(WillingOpenapiAccessTokenProperties.class)
public class OpenapiAccessTokenAutoConfiguration {

    @Bean
    public WillingOpenapiService willingOpenapiService(WillingOpenapiAccessTokenProperties properties) {
        return new WillingOpenapiService(properties);
    }
}
