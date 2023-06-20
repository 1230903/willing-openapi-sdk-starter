package com.willing.openapi.interceptor;

import cn.hutool.core.lang.Dict;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.interceptor.Interceptor;

/**
 * 自定义请求拦截器
 *
 * @author xzhou
 * @date 2023/06/19
 */
public class CustomRequestInterceptor<T> implements Interceptor<T> {

    @Override
    public boolean beforeExecute(ForestRequest request) {
        String url = request.getUrl();
        if (!url.contains("access_token")) {
            String appId = (String) request.getVariableValue("appId");
            String appSecret = (String) request.getVariableValue("appSecret");
            String accessTokenUrl = String.format("https://open.demo.weiling.cn/openapi/auth/access_token/get?app_id=%s&app_secret=%s", appId, appSecret);
            String resultStr = HttpUtil.get(accessTokenUrl);
            Dict result = JSONUtil.toBean(resultStr, Dict.class);
            Dict results = Dict.parse(result.getBean("data"));
            String accessToken = results.getStr("access_token");
            request.setUrl(url + "?access_token=" + accessToken);
        }
        return true;
    }
}
