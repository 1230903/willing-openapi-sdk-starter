package com.willing.openapi.config;

import cn.hutool.core.collection.ListUtil;
import com.dtflys.forest.Forest;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.interceptor.Interceptor;
import com.willing.openapi.base.WillingOpenapiAccessTokenProperties;
import com.willing.openapi.service.iface.WillingOpenApi;
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
        this.properties = properties;
        return Forest.config()
//                .setBaseAddress(new ForestAddress("https", null, -1, properties.getUrl()))
                .setInterceptors(ListUtil.list(false, CustomRequestInterceptor.class))
                .setMaxConnections(1000)
                .setConnectTimeout(3000)
                .setReadTimeout(3000)
                .client(WillingOpenApi.class);
    }


    /**
     * 自定义请求拦截器
     *
     * @author xzhou
     * @date 2023/06/15
     */
    public static class CustomRequestInterceptor<T> implements Interceptor<T> {

        @Override
        public boolean beforeExecute(ForestRequest request) {
            String url = request.getUrl();

//            ForestRequest<?> getTokenRequest = Forest.get(properties.getUrl() + "/openapi/auth/access_token/get");
//            getTokenRequest.addQuery("app_id", properties.getAppId());
//            getTokenRequest.addQuery("app_secret", properties.getAppSecret());
//            Object execute = getTokenRequest.execute();

            if (!url.contains("access_token")) {
                request.setUrl(url + "?access_token=" + "ynnyxuEaskH5SItZCpyNrcSzNx3X7-ZxjSthxAPSkulECLC_425P4Dq2a16RQQC9e3OoLsviJgSwSM6rhxDebzdBqbAX23M70Yv62IIXSpF3bA_WflruEJr9-zIX9pqCbYG7fFIbPmBnNZwXzDk-1dyXctmgwCvZnEMU9uAhbWw");
            }
            return true;
        }
    }
}
