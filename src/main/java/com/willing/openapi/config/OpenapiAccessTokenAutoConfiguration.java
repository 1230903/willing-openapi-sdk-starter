package com.willing.openapi.config;

import com.dtflys.forest.Forest;
import com.dtflys.forest.ForestGenericClient;
import com.dtflys.forest.annotation.ForestClient;
import com.dtflys.forest.springboot.ForestAutoConfiguration;
import com.willing.openapi.base.WillingOpenapiAccessTokenProperties;
import com.willing.openapi.bean.SimpleCustomInterceptor;
import com.willing.openapi.service.impl.WillingOpnenapiService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@Configuration
@EnableConfigurationProperties(WillingOpenapiAccessTokenProperties.class)
@Import({ForestAutoConfiguration.class})
public class OpenapiAccessTokenAutoConfiguration {

    private WillingOpenapiAccessTokenProperties properties;

    @Bean
    public WillingOpnenapiService willingOpnenapiService(WillingOpenapiAccessTokenProperties properties) {
        this.properties = properties;
        return new WillingOpnenapiService(properties);
    }

    @Bean
    @ConditionalOnMissingBean(SimpleCustomInterceptor.class)
    public SimpleCustomInterceptor simpleCustomInterceptor() {
        return new SimpleCustomInterceptor(properties);
    }
}
