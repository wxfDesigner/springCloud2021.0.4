/**  
 * All rights Reserved, Designed By 15821434334@163.com
 * @Title: ExceptionConfig.java   
 * @Package com.springCloud.k8s.gateway.app.configuration   
 * @Description: 网关全局异常处理初始化配置类 
 * @author: WangXf     
 * @date: 2023年3月21日 下午5:11:11   //2023/03/21 17:11:11
 * @version V1.0 
 * @Copyright: 2023 15821434334@163.com Inc. All rights reserved. 
 * 
 */
package com.springCloud.k8s.gateway.app.configuration;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * 
 * @ClassName: ExceptionConfig   
 * @Description: 网关全局异常处理初始化配置类
 * @author: WangXf 
 * @date: 2023年3月21日 下午5:11:11 
 *     
 * @Copyright: 2023 15821434334@163.com Inc. All rights reserved. 
 *
 */
@Configuration
public class ExceptionConfig {

	/**
	 * 
	 * @Title: errorWebExceptionHandler  
	 * @Description: 自定义异常处理[@@]注册Bean时依赖的Bean，会从容器中直接获取，所以直接注入即可
	 * @param viewResolversProvider
	 * @param serverCodecConfigurer
	 * @param customWebExceptionHandler 
	 * @return ErrorWebExceptionHandler    返回类型  
	 * @throws
	 */
    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                             ServerCodecConfigurer serverCodecConfigurer,
                                                             CustomWebExceptionHandler customWebExceptionHandler) {
        customWebExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        customWebExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        customWebExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return customWebExceptionHandler;
    }
}