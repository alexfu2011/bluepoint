package com.identity.bluepoint.util;

import java.io.Serializable;

import com.identity.bluepoint.util.DisplayableValue;

/**
 * @author lazyman
 */
public class AuthorizationActionValue implements DisplayableValue<String>, Serializable {
    private static final long serialVersionUID = 1L;

    private String value;
    private String label;
    private String description;

    public AuthorizationActionValue(String value, String label, String description) {
        this.value = value;
        this.label = label;
        this.description = description;
    }

    /**
     * @return actionURI
     */
    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "AuthorizationActionValue(value=" + value + ", label=" + label + ", description="
                + description + ")";
    }

}
