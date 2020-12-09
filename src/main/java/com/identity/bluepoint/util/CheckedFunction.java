package com.identity.bluepoint.util;

import com.identity.bluepoint.web.util.annotation.Experimental;
import com.identity.bluepoint.web.util.exception.CommonException;

import java.io.Serializable;

/**
 * Almost the same as Function but this one is Serializable and can throw CommonException.
 * EXPERIMENTAL
 */
@Experimental
@FunctionalInterface
public interface CheckedFunction<T, R> extends Serializable {

    R apply(T argument) throws CommonException;
}
