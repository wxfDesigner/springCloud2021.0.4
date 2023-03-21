/**  
 * All rights Reserved, Designed By 15821434334@163.com
 * @Title: SwaggerHandler.java   
 * @Package com.springCloud.k8s.gateway.app.swagger   
 * @Description: 自定义异常处理  
 * @author: WangXf     
 * @date: 2023年3月21日 下午5:08:23   //2023/03/21 17:08:23
 * @version V1.0 
 * @Copyright: 2023 15821434334@163.com Inc. All rights reserved. 
 * 
 */
package com.springCloud.k8s.gateway.app.swagger;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

/**
 * 
 * @ClassName: SwaggerHandler   
 * @Description: swagger的数据接口，在访问swagger-ui中会拉去此接口的数据
 * @author: WangXf 
 * @date: 2023年3月21日 下午5:08:23 
 *     
 * @Copyright: 2023 15821434334@163.com Inc. All rights reserved. 
 *
 */
//@RestController
//@RequestMapping("/swagger-resources")
@AllArgsConstructor
public class SwaggerHandler {

    private final SecurityConfiguration securityConfiguration;
    private final UiConfiguration uiConfiguration;
    private final SwaggerResourcesProvider swaggerResourcesProvider;

    @GetMapping("/configuration/security")
    public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration(){
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()), HttpStatus.OK));
    }

    @GetMapping("configuration/ui")
    public Mono<ResponseEntity<UiConfiguration>> uiConfiguration(){
        return Mono.just(new ResponseEntity<>(
                Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()),HttpStatus.OK));
    }

    @GetMapping
    public Mono<ResponseEntity<List<SwaggerResource>>> swaggerResources(){
        return Mono.just((new ResponseEntity<>(swaggerResourcesProvider.get(),HttpStatus.OK)));
    }


}
