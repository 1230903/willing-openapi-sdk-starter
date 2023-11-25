package com.willing.openapi.service.iface;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Query;
import com.willing.openapi.entity.vo.CompanyDetailResponse;
import com.willing.openapi.exception.WillingOpenApiErrorException;

import static com.willing.openapi.constans.Constants.Company.COMPANY_DETAIL;


/**
 * @author xiaozhou
 * @date 2023/11/25
 * <p>openapi开放api
 * </p>
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
    @Get(COMPANY_DETAIL)
    CompanyDetailResponse getCompanyDetail(@Query("company_id") String companyId, @Query("company_name") String companyName) throws WillingOpenApiErrorException;

}
