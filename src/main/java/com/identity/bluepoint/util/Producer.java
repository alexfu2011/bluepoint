package com.identity.bluepoint.util;

import java.io.Serializable;

/**
 * Almost the same as java.util.function.Supplier, but this one is Serializable.
 * That is very useful especially in use in Wicket models.
 *
 * @author Radovan Semancik
 */
@FunctionalInterface
public interface Producer<T> extends Serializable {

    T run();        // todo shouldn't be 'get'?

}
