package com.identity.bluepoint.web.page.error;

import com.identity.bluepoint.web.application.PageDescriptor;

/**
 * @author lazyman
 */
@PageDescriptor(url = "/error/410", permitAll = true)
public class PageError410 extends PageError {

    public PageError410() {
        super(410);
    }
}
