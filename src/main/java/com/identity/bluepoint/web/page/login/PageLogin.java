package com.identity.bluepoint.web.page.login;

import com.identity.bluepoint.web.application.PageDescriptor;
import com.identity.bluepoint.web.application.Url;

@PageDescriptor(urls = {
        @Url(mountUrl = "/login", matchUrlForSecurity = "/login")
}, permitAll = true, loginPage = true)
public class PageLogin extends AbstractPageLogin {
    private static final long serialVersionUID = 1L;

    public PageLogin() {
    }

    @Override
    protected void initCustomLayer() {
    }

}
