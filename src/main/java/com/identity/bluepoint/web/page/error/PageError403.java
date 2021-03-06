package com.identity.bluepoint.web.page.error;

import com.identity.bluepoint.web.application.PageDescriptor;

/**
 * @author lazyman
 */
@PageDescriptor(url = "/error/403", permitAll = true)
public class PageError403 extends PageError {

    public PageError403() {
        super(403);
    }

    @Override
    protected String getErrorMessageKey(){
        return "PageError403.message";
    }
}
