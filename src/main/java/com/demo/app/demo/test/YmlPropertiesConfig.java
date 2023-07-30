package com.demo.app.demo.test;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.lang.model.element.Name;

@Configuration
@ConfigurationProperties(prefix = "key")
@Setter
@Getter
public class YmlPropertiesConfig {

    private String appname;
    private String appscret;
    private String baseUrl;


}
