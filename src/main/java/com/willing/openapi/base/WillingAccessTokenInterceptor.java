package com.willing.openapi.base;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import com.willing.openapi.entity.vo.AuthTokenResponse;
import com.willing.openapi.service.impl.WillingOpnenapiService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@Slf4j
public class WillingAccessTokenInterceptor extends BasePathMatchInterceptor {

    private String accessToken;

    public WillingAccessTokenInterceptor(OkHttpClient okHttpClient, WillingOpenapiAccessTokenProperties properties) throws IOException {
        String url = properties.getUrl() + "?" + properties.getAppId() + "&" + properties.getAppSecret();
        Request request = new Request.Builder().url(url).build();
        Response execute = okHttpClient.newCall(request).execute();

        String response = null;
        if (Objects.nonNull(execute.body())) {
            response = execute.body().string();
        }
        AuthTokenResponse ret = JSONUtil.toBean(response, AuthTokenResponse.class);
        if (Objects.isNull(ret)) {
            log.error("== !!!!!!!!!!!!! 获取TOKEN失败!!!!!!!!!!!!!!");
        }
        this.accessToken = ret.getData().getAccessToken();
    }


    @Override
    protected Response doIntercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrl = request.url();

        HttpUrl.Builder builder = httpUrl.newBuilder();
        String url = httpUrl.url().toString();
        if (!url.contains("access_token")) {
            if (StrUtil.isNotBlank(accessToken)) {
                builder.addQueryParameter("access_token", accessToken);
            }
        }
        log.info("请求的url:{}", url);
        Request newReq = request.newBuilder().url(builder.build()).headers(request.headers()).build();
        return chain.proceed(newReq);
    }
}
