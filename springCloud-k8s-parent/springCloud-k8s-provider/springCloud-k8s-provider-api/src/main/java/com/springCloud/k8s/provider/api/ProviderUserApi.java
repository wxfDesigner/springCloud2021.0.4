package com.springCloud.k8s.provider.api;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.springCloud.k8s.common.configuration.feign.CommonFallback;
import com.springCloud.k8s.provider.model.ProviderUserVO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

/**
 * 
 * @ClassName: ProviderUserApi
 * @Description: 提供者用户Api接口
 * @author WangXf
 * @date 2023年3月1日 下午2:00:07
 *
 */
//@FeignClient(name = "springCloud-k8s-provider-app", path = "/providerUserApi")
@ReactiveFeignClient(name = "springCloud-k8s-provider-app", path = "/providerUserApi", fallbackFactory = ProviderUserApi.ProviderUserApiFallback.class)
@CircuitBreaker(name = "tools")
//@TimeLimiter(name = "tools")
public interface ProviderUserApi {

	/**
	 * 
	 * @Title: list  
	 * @Description: 查询提供者的用户信息VO对象列表   
	 * @return Flux<ProviderUserVO>    返回类型  
	 * @throws
	 */
	@GetMapping("/list")
	public Flux<ProviderUserVO> list();
	
	/**
	 * 
	 * @ClassName: ProviderUserApiFallback  
	 * @Description: 提供者用户Api接口服务降级处理实现类  
	 * @author WangXf  
	 * @date 2023年3月9日 下午3:58:19  
	 *
	 */
	@Component
	class ProviderUserApiFallback implements CommonFallback<ProviderUserApi>{
		
	}

}
