package com.willing.openapi.config;

import cn.hutool.core.collection.ListUtil;
import com.dtflys.forest.Forest;
import com.dtflys.forest.config.ForestConfiguration;
import com.dtflys.forest.converter.json.ForestJacksonConverter;
import com.willing.openapi.base.WillingOpenapiConfigProperties;
import com.willing.openapi.interceptor.CustomRequestInterceptor;
import com.willing.openapi.service.iface.WillingOpenApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
@EnableConfigurationProperties(WillingOpenapiConfigProperties.class)
public class OpenapiAccessTokenAutoConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "willing.openapi", name = "enable", havingValue = "true")
    public WillingOpenApi WillingOpenApi(WillingOpenapiConfigProperties properties) {
        ForestConfiguration fc = Forest.config()
                .setVariableValue("appId", properties.getAppId())
                .setVariableValue("appSecret", properties.getAppSecret())
                .setVariableValue("baseUrl", properties.getUrl())
                .setInterceptors(ListUtil.list(false, CustomRequestInterceptor.class))
                .setJsonConverter(new ForestJacksonConverter())
                .setBackendName("httpclient")
                .setMaxConnections(1000)
                .setConnectTimeout(3000)
                .setReadTimeout(3000);
        return fc.client(WillingOpenApi.class);
    }
}
