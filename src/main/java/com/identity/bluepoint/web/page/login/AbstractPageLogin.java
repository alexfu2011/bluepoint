package com.identity.bluepoint.web.page.login;

import com.identity.bluepoint.gui.api.page.PageBase;

public abstract class AbstractPageLogin extends PageBase {
    private static final long serialVersionUID = 1L;

    public AbstractPageLogin() {
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        initLayer();
    }

    private void initLayer() {
        initCustomLayer();
    }

    protected abstract void initCustomLayer();

    @Override
    protected void onConfigure() {
        super.onConfigure();
    }
    
    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
    }

}
