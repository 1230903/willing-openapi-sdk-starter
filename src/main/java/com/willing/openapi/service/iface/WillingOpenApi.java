package com.willing.openapi.service.iface;

import com.willing.openapi.entity.vo.CompanyDetailResponse;
import com.willing.openapi.exception.WillingOpenApiErrorException;

import java.io.IOException;


/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
public interface WillingOpenApi {

    /**
     * 执行
     *
     * @param uri  uri
     * @param data 数据
     * @return {@link String}
     * @throws IOException ioexception
     */
    String execute(String uri, String data) throws IOException;


    /**
     * 获取企业详情
     *
     * @param companyId
     * @param companyName
     * @return
     * @throws WillingOpenApiErrorException
     */
    CompanyDetailResponse getCompanyDetail(String companyId, String companyName) throws WillingOpenApiErrorException, IOException;
}
