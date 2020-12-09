package com.identity.bluepoint.web.page.error;

import com.identity.bluepoint.web.application.PageDescriptor;

/**
 * @author lazyman
 */
@PageDescriptor(url = "/error/401", permitAll = true)
public class PageError401 extends PageError {

    public PageError401() {
        super(401);
    }
}
