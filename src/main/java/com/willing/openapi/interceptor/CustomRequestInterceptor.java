package com.willing.openapi.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.interceptor.Interceptor;
import com.willing.openapi.constans.Constants;
import com.willing.openapi.entity.vo.AuthTokenResponse;
import com.willing.openapi.exception.ExceptionEnum;
import com.willing.openapi.exception.WillingOpenApiErrorException;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义请求拦截器
 *
 * @author xzhou
 */
@Slf4j
public class CustomRequestInterceptor<T> implements Interceptor<T> {

    @Override
    public boolean beforeExecute(ForestRequest request) {
        String url = request.getUrl();
        if (!url.contains("access_token")) {
            String appId = (String) request.getVariableValue("appId");
            String appSecret = (String) request.getVariableValue("appSecret");
            String baseUrl = (String) request.getVariableValue("baseUrl");
            if (StrUtil.isEmpty(baseUrl)) {
                baseUrl = Constants.PRD_DOMAIN;
            }
            String accessTokenUrl = StrUtil.format(baseUrl + Constants.Auth.AUTH_TOKEN_URL, appId, appSecret);
            String resultStr = HttpUtil.get(accessTokenUrl);
            AuthTokenResponse accessTokenResp = JSONUtil.toBean(resultStr, AuthTokenResponse.class);
            log.info("获取accessToken结果:{}", JSONUtil.toJsonStr(accessTokenResp));
            if (!accessTokenResp.isSuccess()) {
                throw new WillingOpenApiErrorException(ExceptionEnum.APP_ID_OR_APP_SECRET_INVALID);
            }
            String accessToken = accessTokenResp.getData().getAccessToken();
            request.setUrl(url + "?access_token=" + accessToken);
        }
        return true;
    }
}
