package com.willing.openapi.service.iface;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import com.willing.openapi.entity.vo.AuthTokenResponse;
import com.willing.openapi.entity.vo.CompanyDetailResponse;
import com.willing.openapi.exception.WillingOpenApiErrorException;


/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@BaseRequest(contentType = "application/json", baseURL = "https://open.demo.weiling.cn/")
public interface WillingOpenApi {


    /**
     * 获取access_token
     *
     * @param appId
     * @param appSecret
     * @return {@link AuthTokenResponse}
     * @throws WillingOpenApiErrorException
     */
    @Get("/openapi/auth/access_token/get")
    AuthTokenResponse getAccessToken(@Query("app_id") String appId, @Query("app_secret") String appSecret) throws WillingOpenApiErrorException;


    /**
     * 获取企业详情
     *
     * @param companyId
     * @param companyName
     * @return
     * @throws WillingOpenApiErrorException
     */
    @Get("/openapi/company/detail")
    CompanyDetailResponse getCompanyDetail(@Query("company_id") String companyId, @Query("company_name") String companyName) throws WillingOpenApiErrorException;
}
