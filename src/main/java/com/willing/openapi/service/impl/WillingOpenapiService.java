package com.willing.openapi.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Dict;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.dtflys.forest.Forest;
import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.config.ForestConfiguration;
import com.dtflys.forest.converter.json.ForestJacksonConverter;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.interceptor.Interceptor;
import com.willing.openapi.base.WillingOpenapiAccessTokenProperties;
import com.willing.openapi.entity.vo.CompanyDetailResponse;
import com.willing.openapi.exception.WillingOpenApiErrorException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@Data
@Slf4j
public class WillingOpenapiService {

    /**
     * willing-开放平台-指定应用的appId
     */
    private String appId;

    /**
     * willing-开放平台-指定应用的appSecret
     */
    private String appSecret;

    /**
     * willingOpenapi服务地址
     */
    private String baseUrl;

    /**
     * openapi开放api
     */
    private final WillingOpenApi willingOpenApi;


    public WillingOpenapiService(WillingOpenapiAccessTokenProperties properties) {
        this.appId = properties.getAppId();
        this.appSecret = properties.getAppSecret();
        this.baseUrl = properties.getUrl();

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
        this.willingOpenApi = fc.client(WillingOpenApi.class);
    }

    /**
     * 自定义请求拦截器
     *
     * @author xzhou
     * @date 2023/06/19
     */
    public static class CustomRequestInterceptor<T> implements Interceptor<T> {

        @Override
        public boolean beforeExecute(ForestRequest request) {
            String url = request.getUrl();
            if (!url.contains("access_token")) {
                String appId = (String) request.getVariableValue("appId");
                String appSecret = (String) request.getVariableValue("appSecret");
                String accessTokenUrl = String.format("https://open.demo.weiling.cn/openapi/auth/access_token/get?app_id=%s&app_secret=%s", appId, appSecret);
                String resultStr = HttpUtil.get(accessTokenUrl);
                Dict result = JSONUtil.toBean(resultStr, Dict.class);
                Dict results = Dict.parse(result.getBean("data"));
                String accessToken = results.getStr("access_token");
                request.setUrl(url + "?access_token=" + accessToken);
            }
            return true;
        }
    }


    /**
     * openapi开放api
     *
     * @author xzhou
     * @date 2023/06/20
     */
    @BaseRequest(baseURL = "{baseUrl}", contentType = "application/json")
    public interface WillingOpenApi {

        /**
         * 获取企业详情
         *
         * @param companyId
         * @param companyName
         * @return
         * @throws WillingOpenApiErrorException
         */
        @Get("openapi/company/detail")
        CompanyDetailResponse getCompanyDetail(@Query("company_id") String companyId, @Query("company_name") String companyName) throws WillingOpenApiErrorException;
    }
}
