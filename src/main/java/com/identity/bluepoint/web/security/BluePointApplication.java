package com.identity.bluepoint.web.security;

import org.apache.wicket.markup.html.WebPage;

import java.util.Locale;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.request.mapper.parameter.PageParametersEncoder;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.settings.ApplicationSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;

import com.identity.bluepoint.web.page.HomePage;
import com.identity.bluepoint.web.page.login.PageLogin;
import com.identity.bluepoint.web.application.DescriptorLoader;
import com.identity.bluepoint.web.page.error.*;

public class BluePointApplication extends AuthenticatedWebApplication implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;
    
    public static final String MOUNT_INTERNAL_SERVER_ERROR = "/error";
    public static final String MOUNT_UNAUTHORIZED_ERROR = "/error/401";
    public static final String MOUNT_FORBIDEN_ERROR = "/error/403";
    public static final String MOUNT_NOT_FOUND_ERROR = "/error/404";
    public static final String MOUNT_GONE_ERROR = "/error/410";
    
    @Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return PageLogin.class;
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return BluePointAuthWebSession.class;
    }
	
	@Override
	public void init()
	{
		super.init();
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext, true));
        
        //exception handling an error pages
        ApplicationSettings appSettings = getApplicationSettings();
        appSettings.setAccessDeniedPage(PageError401.class);
        appSettings.setInternalErrorPage(PageError.class);
        appSettings.setPageExpiredErrorPage(PageError.class);
        
        mount(new MountedMapper(MOUNT_INTERNAL_SERVER_ERROR, PageError.class, new PageParametersEncoder()));
        mount(new MountedMapper(MOUNT_UNAUTHORIZED_ERROR, PageError401.class, new PageParametersEncoder()));
        mount(new MountedMapper(MOUNT_FORBIDEN_ERROR, PageError403.class, new PageParametersEncoder()));
        mount(new MountedMapper(MOUNT_NOT_FOUND_ERROR, PageError404.class, new PageParametersEncoder()));
        mount(new MountedMapper(MOUNT_GONE_ERROR, PageError410.class, new PageParametersEncoder()));

        new DescriptorLoader().loadData(this);
	}
	
    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

	public static boolean containsLocale(Locale locale) {
		// TODO Auto-generated method stub
		return false;
	}

	public static Locale getDefaultLocale() {
		// TODO Auto-generated method stub
		return null;
	}
}
