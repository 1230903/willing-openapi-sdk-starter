package com.willing.openapi.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.dtflys.forest.Forest;
import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.config.ForestConfiguration;
import com.dtflys.forest.converter.json.ForestJacksonConverter;
import com.willing.openapi.base.WillingOpenapiAccessTokenProperties;
import com.willing.openapi.entity.vo.CompanyDetailResponse;
import com.willing.openapi.exception.WillingOpenApiErrorException;
import com.willing.openapi.interceptor.CustomRequestInterceptor;
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
