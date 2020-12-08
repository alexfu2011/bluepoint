package com.identity.bluepoint.web.application;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author lazyman
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Url {

    String mountUrl();

    /**
     * If empty {@link Url#mountUrl()} + "/**" will be used for URL ant pattern matching in security configuration.
     * See {@link DescriptorLoader}, {@link com.evolveum.midpoint.web.security.MidPointGuiAuthorizationEvaluator}.
     */
    String matchUrlForSecurity() default "";
}
