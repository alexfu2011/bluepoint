package com.identity.bluepoint.web.application;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author lazyman
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizationAction {

    String label() default "";

    String description() default "";

    String actionUri();
}
