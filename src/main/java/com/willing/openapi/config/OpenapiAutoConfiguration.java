package com.willing.openapi.config;

import cn.hutool.core.collection.ListUtil;
import com.dtflys.forest.Forest;
import com.dtflys.forest.config.ForestConfiguration;
import com.dtflys.forest.converter.json.ForestJacksonConverter;
import com.willing.openapi.base.WillingOpenapiConfigProperties;
import com.willing.openapi.interceptor.CustomRequestInterceptor;
import com.willing.openapi.service.iface.WillingOpenApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaozhou
 * <p>
 * </p>
 */
@Configuration
@EnableConfigurationProperties(WillingOpenapiConfigProperties.class)
public class OpenapiAutoConfiguration {

    @Bean
    public WillingOpenApi WillingOpenApi(WillingOpenapiConfigProperties properties) {
        ForestConfiguration fc = Forest.config()
                .setVariableValue("appId", properties.getAppId())
                .setVariableValue("appSecret", properties.getAppSecret())
                .setVariableValue("baseUrl", properties.getUrl())
                .setInterceptors(ListUtil.list(false, CustomRequestInterceptor.class))
                .setJsonConverter(new ForestJacksonConverter())
                .setBackendName("okhttp3")
                .setMaxConnections(1000)
                .setConnectTimeout(3000)
                .setReadTimeout(3000);
        return fc.client(WillingOpenApi.class);
    }
}
