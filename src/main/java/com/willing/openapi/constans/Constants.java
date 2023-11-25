package com.willing.openapi.constans;

/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
public class Constants {

    public static final String PRD_DOMAIN = "https://openapi.weiling.cn/";
    public static final String URL_SUFFIX = "openapi";

    public interface Auth {
        String authTokenUrl = URL_SUFFIX + "/auth/access_token/get?app_id={}&app_secret={}";
    }

    public interface Company {
        String COMPANY_DETAIL = URL_SUFFIX + "/company/detail";
    }
}
