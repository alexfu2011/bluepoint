package com.identity.bluepoint.util;

import com.identity.bluepoint.util.annotation.Experimental;
import com.identity.bluepoint.util.exception.CommonException;

import java.io.Serializable;

/**
 * Almost the same as Producer but this one can throw CommonException.
 * EXPERIMENTAL
 */
@Experimental
@FunctionalInterface
public interface CheckedProducer<T> extends Serializable {
    T get() throws CommonException;
}
