package com.springCloud.k8s.gateway.app;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: Application
 * @Description: 网关应用启动器
 * @author WangXf
 * @date 2023年3月16日 上午10:32:35
 *
 */
@Slf4j
//@EnableWebFlux要求Spring从WebFluxConfigurationSupport引入Spring WebFlux 配置。
//如果你的依赖中引入了spring-boot-starter-webflux，Spring WebFlux 将自动配置，不需要添加该注解。
//但如果你只使用Spring WebFlux而没有使用Spring Boot，这是需要添加@EnableWebFlux启动Spring WebFlux自动化配置。
//@EnableWebFlux
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({ "com.springCloud.k8s", "com.tdh.gps.console" })
public class Application {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(WebApplicationType.REACTIVE).run(args);
		log.info("springCloud-k8s-gateway-app started successfully");
	}

	/**
	 * 
	 * @Title: reactiveResilience4JCircuitBreakerFactory  
	 * @Description: Resilience4J 熔断器初始化
	 * @param circuitBreakerRegistry
	 * @param timeLimiterRegistry  
	 * @return ReactiveResilience4JCircuitBreakerFactory    返回类型  
	 * @throws
	 */
	@Bean
	public ReactiveResilience4JCircuitBreakerFactory reactiveResilience4JCircuitBreakerFactory(
			CircuitBreakerRegistry circuitBreakerRegistry, TimeLimiterRegistry timeLimiterRegistry) {
		return new ReactiveResilience4JCircuitBreakerFactory(circuitBreakerRegistry, timeLimiterRegistry);
	}
	
}
