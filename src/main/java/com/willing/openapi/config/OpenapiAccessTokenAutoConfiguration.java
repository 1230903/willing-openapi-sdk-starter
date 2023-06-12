package com.willing.openapi.config;

import com.willing.openapi.base.CustomHttpClient;
import com.willing.openapi.base.WillingOpenapiAccessTokenProperties;
import com.willing.openapi.service.impl.WillingOpnenapiService;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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

    private WillingOpenapiAccessTokenProperties properties;

    @Bean
    @ConditionalOnMissingBean(CloseableHttpClient.class)
    public CloseableHttpClient httpClient() {
        return new CustomHttpClient(properties).create();
    }

    @Bean
    @ConditionalOnMissingBean(WillingOpnenapiService.class)
    public WillingOpnenapiService willingOpnenapiService(WillingOpenapiAccessTokenProperties properties,
                                                         CloseableHttpClient httpClient) {
        this.properties = properties;
        return new WillingOpnenapiService(properties, httpClient);
    }
}
