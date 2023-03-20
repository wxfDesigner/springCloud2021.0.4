package com.springCloud.k8s.gateway.app.swagger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

/**
 * 
 * @ClassName: Swagger3ResourceProvider
 * @Description: swagger Api接口文档配置类
 * @author WangXf
 * @date 2023年3月17日 下午5:21:25
 *
 */
@Slf4j
@Primary
@Component
@AllArgsConstructor
public class Swagger3ResourceProvider implements SwaggerResourcesProvider {

	/**
	 * swagger默认的url后缀
	 */
	public static final String API_URI = "/v2/api-docs";

	/**
	 * 网关路由
	 */
	private final RouteLocator routeLocator;

	/**
	 * 网关配置项，对应配置文件中配置的spring.cloud.gateway所有子项
	 */
//    private final GatewayProperties gatewayProperties;

	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		StringBuilder path = new StringBuilder();
		routeLocator.getRoutes().subscribe(route -> {
			path.delete(0, path.length());
			path.append("/").append(route.getUri().getHost()).append(API_URI);
			resources.add(swaggerResource(route.getUri().getHost(), path.toString(), "3.0.0"));
		});
		log.info("swagger3 api docs:{}", JSON.toJSONString(resources));
		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}

}
