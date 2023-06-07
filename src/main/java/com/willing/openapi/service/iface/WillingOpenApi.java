package com.willing.openapi.service.iface;

import com.dtflys.forest.annotation.*;
import com.willing.openapi.bean.SimpleCustomInterceptor;
import com.willing.openapi.constans.Constants;
import com.willing.openapi.entity.vo.CompanyDetailResponse;
import com.willing.openapi.exception.WillingOpenApiErrorException;

import static com.willing.openapi.constans.WillingOpenApiPath.Company.COMPANY_DETAIL;


/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@Backend("httpclient")
@BaseRequest(interceptor = SimpleCustomInterceptor.class)
@ForestClient
public interface WillingOpenApi {

    /**
     * 获取企业详情
     *
     * @param companyId
     * @param companyName
     * @return
     * @throws WillingOpenApiErrorException
     */
    @Get(Constants.URL_SUFFIX + COMPANY_DETAIL)
    CompanyDetailResponse getCompanyDetail(@Query("company_id") String companyId, @Query("company_name") String companyName) throws WillingOpenApiErrorException;
}
