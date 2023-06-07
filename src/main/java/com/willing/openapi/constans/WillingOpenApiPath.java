package com.willing.openapi.constans;

/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
public interface WillingOpenApiPath {

    interface Auth {
        String AUTH_TOKEN = "/auth/token";
    }

    interface Clue {
        String CLUE_CREATE = "/clue/create";
    }

    interface Customer {

    }

    interface Company {
        String COMPANY_DETAIL = "/company/detail";
    }
}
