package com.company.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path.document}")
    private String uploadPathDocument;
    @Value("${upload.path.resolution}")
    private String uploadPathResolution;


    public MvcConfig() {
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/static/**"}).addResourceLocations(new String[]{"classpath:/static/"});
        registry.addResourceHandler(new String[]{"/pdf/**"}).addResourceLocations(new String[]{"file:" + this.uploadPathDocument + "/"});
        registry.addResourceHandler(new String[]{"/resolve/**"}).addResourceLocations(new String[]{"file:" + this.uploadPathResolution + "/"});


    }

}
