package com.lnlib.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class ApplicationConfiguration
{
    /**
     * Configuration for the localization
     */
    @Bean
    public ResourceBundleMessageSource messageSource()
    {
        var source = new ResourceBundleMessageSource();
        source.setBasenames("messages/exception_message");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
}
