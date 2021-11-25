package com.klm.cases.df;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.klm.cases")
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/index.html").addResourceLocations("classpath:/static/index.html");
        registry.addResourceHandler("/airfare.html").addResourceLocations("classpath:/static/airfare.html");
        registry.addResourceHandler("/airfare.js").addResourceLocations("classpath:/static/airfare.js");
		
		registry.addResourceHandler("/jquery-3.6.0.js").addResourceLocations("classpath:/static/jquery-3.6.0.js");
		registry.addResourceHandler("/select2.min.js").addResourceLocations("classpath:/static/select2.min.js");
		registry.addResourceHandler("/select2.min.css").addResourceLocations("classpath:/static/select2.min.css");	
    }

   
}
