package com.identity.bluepoint.util;

/**
 * @author semancik
 *
 */
@FunctionalInterface
public interface HeteroComparator<A,B> {

    boolean isEquivalent(A a, B b);

}
