/**  
 * All rights Reserved, Designed By 15821434334@163.com
 * @Title: SwaggerConfig.java   
 * @Package com.springCloud.k8s.gateway.app.swagger   
 * @Description: 自定义异常处理  
 * @author: WangXf     
 * @date: 2023年3月21日 下午5:08:23   //2023/03/21 17:08:23
 * @version V1.0 
 * @Copyright: 2023 15821434334@163.com Inc. All rights reserved. 
 * 
 */
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
 * @author: WangXf 
 * @date: 2023年3月21日 下午5:08:23 
 *     
 * @Copyright: 2023 15821434334@163.com Inc. All rights reserved. 
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
