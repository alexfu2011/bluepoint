package com.identity.bluepoint.util;

import java.io.Serializable;

/**
 * @author semancik
 * @author mederly
 *
 */
public interface LocalizableMessage extends Serializable, ShortDumpable {

    String getFallbackMessage();

    boolean isEmpty();

    static boolean isEmpty(LocalizableMessage msg) {
        return msg == null || msg.isEmpty();
    }
}
