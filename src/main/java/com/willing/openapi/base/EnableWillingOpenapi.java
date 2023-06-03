package com.willing.openapi.base;

import com.willing.openapi.config.OpenapiAccessTokenAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author xiaozhou
 * @date 2023/6/3
 * <p>
 * </p>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({OpenapiAccessTokenAutoConfiguration.class})
public @interface EnableWillingOpenapi {
}
