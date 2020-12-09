package com.identity.bluepoint.web.page.error;

import com.identity.bluepoint.web.application.PageDescriptor;

/**
 * @author lazyman
 */
@PageDescriptor(url = "/error/404", permitAll = true)
public class PageError404 extends PageError {

    public PageError404() {
        super(404);
    }
}
