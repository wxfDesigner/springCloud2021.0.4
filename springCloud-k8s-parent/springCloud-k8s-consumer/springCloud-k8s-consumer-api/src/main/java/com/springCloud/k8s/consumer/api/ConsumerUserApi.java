package com.springCloud.k8s.consumer.api;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import com.springCloud.k8s.common.configuration.feign.CommonFallback;
import com.springCloud.k8s.consumer.model.ConsumerUserVO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;

/**
 * 
 * @ClassName: ConsumerUserApi  
 * @Description: 消费者用户Api接口  
 * @author WangXf  
 * @date 2023年3月1日 下午1:59:15  
 *
 */
//@FeignClient(name = "springCloud-k8s-consumer-app", path = "/consumerUserApi")
@ReactiveFeignClient(name = "springCloud-k8s-consumer-app", path = "/consumerUserApi",fallbackFactory = ConsumerUserApi.ConsumerUserApiFallback.class)
@CircuitBreaker(name = "tools")
//@TimeLimiter(name = "tools")
public interface ConsumerUserApi {
	
	/**
	 * 
	 * @Title: list  
	 * @Description: 查询消费者的用户信息VO对象列表  
	 * @return Flux<ConsumerUserVO>    返回类型  
	 * @throws
	 */
	@GetMapping("/list")
	public Flux<ConsumerUserVO> list();
	
	/**
	 * 
	 * @ClassName: ConsumerUserApiFallback  
	 * @Description: 消费者用户Api接口服务降级处理实现类 
	 * @author WangXf  
	 * @date 2023年3月9日 下午3:53:00  
	 *
	 */
	@Component
	class ConsumerUserApiFallback implements CommonFallback<ConsumerUserApi>{
		
	}

}
