package com.identity.bluepoint.util;

/**
 * @author semancik
 *
 */
@FunctionalInterface
public interface Processor<T> {

    void process(T object);

}
