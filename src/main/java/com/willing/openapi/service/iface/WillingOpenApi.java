package com.willing.openapi.service.iface;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.Intercept;
import com.willing.openapi.base.WillingAccessTokenInterceptor;
import com.willing.openapi.entity.vo.CompanyDetailResponse;
import com.willing.openapi.exception.WillingOpenApiErrorException;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.willing.openapi.constans.WillingOpenApiPath.Company.COMPANY_DETAIL;


/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@RetrofitClient(baseUrl = "${willing.openapi.url}")
@Intercept(exclude = {"/auth/token"}, handler = WillingAccessTokenInterceptor.class)
public interface WillingOpenApi {

    /**
     * 获取企业详情
     *
     * @param companyId
     * @param companyName
     * @return
     * @throws WillingOpenApiErrorException
     */
    @GET(COMPANY_DETAIL)
    CompanyDetailResponse getCompanyDetail(@Query("company_id") String companyId, @Query("company_name") String companyName) throws WillingOpenApiErrorException;
}
