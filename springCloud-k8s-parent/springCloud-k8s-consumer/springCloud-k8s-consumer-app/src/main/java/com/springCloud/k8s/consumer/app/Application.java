package com.springCloud.k8s.consumer.app;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

import lombok.extern.slf4j.Slf4j;
import reactivefeign.spring.config.EnableReactiveFeignClients;

/**
 * 
 * @ClassName: Application
 * @Description: 消费者应用启动器
 * @author WangXf
 * @date 2023年3月1日 下午6:00:40
 *
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
@EnableReactiveFeignClients({ "com.springCloud.k8s" })
//@EnableFeignClients({ "com.springCloud.k8s" })
@EnableWebFlux
//@EnableCircuitBreaker
@ComponentScan({ "com.springCloud.k8s","com.tdh.gps.console" })
public class Application {
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(WebApplicationType.REACTIVE).run(args);
		log.info("springCloud-k8s-consumer-app started successfully");

	}

//	@LoadBalanced // 使用负载均衡机制
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
}
