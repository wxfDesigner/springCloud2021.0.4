package com.springCloud.k8s.provider.app;

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
 * @Description: 提供者应用启动器
 * @author WangXf
 * @date 2023年3月1日 下午3:42:23
 *
 */
@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
@EnableReactiveFeignClients({ "com.springCloud.k8s" })
//@EnableFeignClients({ "com.springCloud.k8s" })
@EnableWebFlux
//@EnableCircuitBreaker
@ComponentScan({"com.springCloud.k8s","com.tdh.gps.console"})
public class Application {
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(WebApplicationType.REACTIVE).run(args);
		log.info("springCloud-k8s-provider-app started successfully");
	}

}
