package com.identity.bluepoint.web.page.login;

import com.identity.bluepoint.web.application.PageDescriptor;
import com.identity.bluepoint.web.application.Url;

import com.identity.bluepoint.web.component.form.BluePointForm;

@PageDescriptor(urls = {
        @Url(mountUrl = "/login", matchUrlForSecurity = "/login")
}, permitAll = true, loginPage = true)
public class PageLogin extends AbstractPageLogin {
    private static final long serialVersionUID = 1L;
    
    private static final String ID_FORM = "form";

    public PageLogin() {
    }

    @Override
    protected void initCustomLayer() {
    	BluePointForm form = new BluePointForm(ID_FORM);
        add(form);
    }

}
