package com.willing.openapi.constans;

/**
 * @author xiaozhou
 * <p>
 * </p>
 */
public class Constants {

    public static final String PRD_DOMAIN = "https://openapi.weiling.cn/";
    public static final String URL_SUFFIX = "openapi";

    public interface Auth {
        String AUTH_TOKEN_URL = URL_SUFFIX + "/auth/access_token/get?app_id={}&app_secret={}";
    }

    public interface Company {
        String COMPANY_DETAIL = URL_SUFFIX + "/company/detail";
    }
}
