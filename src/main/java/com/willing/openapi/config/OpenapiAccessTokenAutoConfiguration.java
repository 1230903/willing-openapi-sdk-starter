package com.willing.openapi.config;

import cn.hutool.core.collection.ListUtil;
import com.dtflys.forest.Forest;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.interceptor.Interceptor;
import com.willing.openapi.base.WillingOpenapiAccessTokenProperties;
import com.willing.openapi.entity.vo.AuthTokenResponse;
import com.willing.openapi.service.iface.WillingOpenApi;
import lombok.Data;
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

    /**
     * openapi
     *
     * @param properties 属性
     * @return {@link WillingOpenApi}
     */
    @Bean
    public WillingOpenApi willingOpenApi(WillingOpenapiAccessTokenProperties properties) {
        WillingOpenApi willingOpenApi = Forest.config()
                .setInterceptors(ListUtil.list(false, CustomRequestInterceptor.class))
                .setMaxConnections(1000)
                .setConnectTimeout(3000)
                .setReadTimeout(3000)
                .client(WillingOpenApi.class);
        this.properties = properties;
        return willingOpenApi;
    }


    /**
     * 自定义请求拦截器
     *
     * @author xzhou
     * @date 2023/06/15
     */
    @Data
    public class CustomRequestInterceptor<T> implements Interceptor<T> {

        @Override
        public boolean beforeExecute(ForestRequest request) {
            String url = request.getUrl();

            Forest.get("")

            AuthTokenResponse token = willingOpenApi.getAccessToken(properties.getAppId(), properties.getAppSecret());
            if (!url.contains("access_token")) {
                request.setUrl(url + "?access_token=" + token.getData().getAccessToken());
            }
            return true;
        }
    }
}
