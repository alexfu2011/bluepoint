package com.identity.bluepoint.web.boot;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import com.identity.bluepoint.web.page.HomePage;

public class BluePointApplication extends WebApplication {
	
    @Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}
    
	@Override
	public void init()
	{
		super.init();
	}
}
