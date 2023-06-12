package com.willing.openapi.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.willing.openapi.base.UTF8ResponseHandler;
import com.willing.openapi.base.WillingOpenapiAccessTokenProperties;
import com.willing.openapi.constans.Constants;
import com.willing.openapi.constans.WillingOpenApiPath;
import com.willing.openapi.entity.vo.CompanyDetailResponse;
import com.willing.openapi.exception.WillingOpenApiErrorException;
import com.willing.openapi.service.iface.WillingOpenApi;
import lombok.Data;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@Data
public class WillingOpnenapiService implements WillingOpenApi {

    /**
     * willing-开放平台-指定应用的appId
     */
    private final String appId;

    /**
     * willing-开放平台-指定应用的appSecret
     */
    private final String appSecret;

    /**
     * willingOpenapi服务地址
     */
    private final String url;

    private final CloseableHttpClient httpClient;

    public WillingOpnenapiService(WillingOpenapiAccessTokenProperties properties, CloseableHttpClient httpClient) {
        this.appId = properties.getAppId();
        this.appSecret = properties.getAppSecret();
        this.url = properties.getUrl();
        this.httpClient = httpClient;
    }

    @Override
    public String execute(String uri, String queryParam) throws IOException {
        if (queryParam != null) {
            if (uri.indexOf('?') == -1) {
                uri += '?';
            }
            uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
        }
        HttpGet httpGet = new HttpGet(uri);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            return UTF8ResponseHandler.INSTANCE.handleResponse(response);
        } finally {
            httpGet.releaseConnection();
        }
    }

    @Override
    public CompanyDetailResponse getCompanyDetail(String companyId, String companyName) throws IOException {
        String response = this.execute(url + Constants.URL_SUFFIX + WillingOpenApiPath.Company.COMPANY_DETAIL, StrUtil.isBlank(companyId) ? companyName : companyId);
        return JSONUtil.toBean(response, CompanyDetailResponse.class);
    }
}
