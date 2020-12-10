package com.identity.bluepoint.gui.api.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;

public abstract class PageBase extends WebPage {

    private static final long serialVersionUID = 1L;
    
    private static final String ID_TITLE = "title";
    private static final String ID_BODY = "body";
    private static final String ID_MAIN_POPUP = "mainPopup";
	
	@Override
    protected void onInitialize() {
            super.onInitialize();
            initLayout();
    }

    private void initLayout() {
        TransparentWebMarkupContainer body = new TransparentWebMarkupContainer(ID_BODY);
        body.add(new AttributeAppender("class", "hold-transition ", " "));
        body.add(new AttributeAppender("class", "custom-hold-transition ", " "));
        add(body);
	    Label title = new Label(ID_TITLE, createPageTitleModel());
	    title.setRenderBodyOnly(true);
	    add(title);
    }
    
    protected IModel<String> createPageTitleModel() {
        return new IModel<String>() {
            @Override
            public String getObject() {
            	return "test";
            }
        };
    }
}
