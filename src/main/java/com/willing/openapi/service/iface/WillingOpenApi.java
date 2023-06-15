package com.willing.openapi.service.iface;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Query;
import com.dtflys.forest.annotation.Request;
import com.willing.openapi.entity.vo.CompanyDetailResponse;
import com.willing.openapi.exception.WillingOpenApiErrorException;

import java.io.IOException;


/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@BaseRequest(contentType = "application/json",baseURL = "https://open.demo.weiling.cn/")
public interface WillingOpenApi {

    /**
     * 获取企业详情
     *
     * @param companyId
     * @param companyName
     * @return
     * @throws WillingOpenApiErrorException
     */
    @Request("/openapi/company/detail")
    CompanyDetailResponse getCompanyDetail(@Query("company_id") String companyId, @Query("company_name") String companyName) throws WillingOpenApiErrorException, IOException;
}
