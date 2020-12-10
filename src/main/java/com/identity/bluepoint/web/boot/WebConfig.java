package com.identity.bluepoint.web.boot;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.Configuration;
import com.identity.bluepoint.web.security.BluePointApplication;

@Configuration
@EnableWebMvc
public class WebConfig {

    @Bean
    public BluePointApplication bluePointApplication() {
        return new BluePointApplication();
    }
}