package com.willing.openapi.base;

import com.willing.openapi.config.OpenapiAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author xiaozhou
 * <p>
 * </p>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({OpenapiAutoConfiguration.class})
public @interface EnableWillingOpenapi {
}
