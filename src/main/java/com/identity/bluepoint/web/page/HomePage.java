package com.identity.bluepoint.web.page;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
    public HomePage() {
           add(new Label("helloMessage", "Hello WicketWorld!"));
    }
}
