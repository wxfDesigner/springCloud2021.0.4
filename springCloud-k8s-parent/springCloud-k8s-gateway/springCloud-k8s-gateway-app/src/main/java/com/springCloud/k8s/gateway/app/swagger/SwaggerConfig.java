package com.springCloud.k8s.gateway.app.swagger;

import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

/**
 * 
 * @ClassName: SwaggerConfig  
 * @Description: swagger配置类
 * @author WangXf  
 * @date 2023年3月20日 上午9:38:10  
 *
 */
//@Configuration
public class SwaggerConfig {

    @Bean
    public SecurityConfiguration securityConfiguration(){
        return SecurityConfigurationBuilder.builder().build();
    }

    @Bean
    public UiConfiguration uiConfiguration(){
        return UiConfigurationBuilder.builder().build();
    }

}
