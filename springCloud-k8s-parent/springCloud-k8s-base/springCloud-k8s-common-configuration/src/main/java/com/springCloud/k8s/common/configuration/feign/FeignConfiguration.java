package com.springCloud.k8s.common.configuration.feign;

import java.util.stream.Collectors;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

/**
 * 
 * @ClassName: FeignConfiguration  
 * @Description: feign初始化配置类  
 * @author WangXf  
 * @date 2023年3月9日 上午10:16:55  
 *
 */
@Configuration
public class FeignConfiguration {
	
	/**
	 * 
	 * @Title: messageConverters  
	 * @Description: feign调用信息转换器 
	 * @param converters
	 * @return HttpMessageConverters    返回类型  
	 * @throws
	 */
	@Bean
	@ConditionalOnMissingBean
	public HttpMessageConverters messageConverters(ObjectProvider<HttpMessageConverter<?>> converters) {
		return new HttpMessageConverters(converters.orderedStream().collect(Collectors.toList()));
	}
	
//	/**
//	 * 
//	 * @Title: reactiveOptions  
//	 * @Description: 设置一些超时时间  
//	 * @return ReactiveOptions    返回类型  
//	 * @throws
//	 */
//	@Bean
//    public ReactiveOptions reactiveOptions() {
//        return new WebReactiveOptions.Builder()
//                .setWriteTimeoutMillis(2000)
//                .setReadTimeoutMillis(2000)
//                .setConnectTimeoutMillis(1000)
//                .build();
//    }
//	 
//	/**
//	 * 
//	 * @Title: retryOnNext  
//	 * @Description: 重试机制  
//	 * @return ReactiveRetryPolicies    返回类型  
//	 * @throws
//	 */
//    @Bean
//    public ReactiveRetryPolicies retryOnNext() {
//        //不进行重试，retryOnSame是控制对同一个实例的重试策略，retryOnNext是控制对不同实例的重试策略。
//        return new ReactiveRetryPolicies.Builder()
//                .retryOnSame(BasicReactiveRetryPolicy.retryWithBackoff(0, 10))
//                .retryOnNext(BasicReactiveRetryPolicy.retryWithBackoff(2, 10))
//                .build();
//    }

}
