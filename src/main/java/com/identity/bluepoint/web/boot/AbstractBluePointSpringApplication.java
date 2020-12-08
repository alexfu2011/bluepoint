package com.identity.bluepoint.web.boot;

import javax.servlet.DispatcherType;
import org.apache.wicket.Application;
import org.apache.wicket.protocol.http.WicketFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;

public abstract class AbstractBluePointSpringApplication {
	
    @Bean
    public FilterRegistrationBean<WicketFilter> wicket() {
        FilterRegistrationBean<WicketFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new WicketFilter());
        registration.setDispatcherTypes(DispatcherType.ERROR, DispatcherType.REQUEST, DispatcherType.FORWARD);
        registration.addUrlPatterns("/*");
        registration.addInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        registration.addInitParameter(Application.CONFIGURATION, "deployment");
        registration.addInitParameter("applicationBean", "bluePointApplication");
        registration.addInitParameter(WicketFilter.APP_FACT_PARAM, "org.apache.wicket.spring.SpringWebApplicationFactory");

        return registration;
    }
}
