package com.springCloud.k8s.consumer.app.controller;

import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.springCloud.k8s.common.model.ResponseEntity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: NacosController
 * @Description: k8s pod preStop hook函数调用请求控制层
 * @author WangXf
 * @date 2023年3月7日 下午3:45:07
 *
 */
@Api(tags = "NacosController", description = "k8s pod preStop hook函数调用请求控制层")
@Slf4j
@RestController
@RequestMapping("/nacos")
public class NacosController {

	@Resource
	private NacosDiscoveryProperties nacosDiscoveryProperties;

	/**
	 * 
	 * @Title: stop  
	 * @Description: nacos下线当前服务实例 
	 * @return ResponseEntity<Object>    返回类型  
	 * @throws NacosException
	 */
	@ApiOperation("nacos下线当前服务实例")
	@PostMapping("/stop")
	public ResponseEntity<Object> stop() throws NacosException {
		// 当前 Nacos 权重设为 0
		Properties properties = new Properties();
		properties.put(PropertyKeyConst.NAMESPACE, nacosDiscoveryProperties.getNamespace());
		properties.put(PropertyKeyConst.SERVER_ADDR, nacosDiscoveryProperties.getServerAddr());
		properties.put(PropertyKeyConst.USERNAME, nacosDiscoveryProperties.getUsername());
		properties.put(PropertyKeyConst.PASSWORD, nacosDiscoveryProperties.getPassword());
		String serviceName = nacosDiscoveryProperties.getService();
		NamingService namingService = NacosFactory.createNamingService(properties);
		List<Instance> instanceList = namingService.getAllInstances(serviceName);
		for (Instance instance : instanceList) {
			log.info(instance.toString());
			if (instance.getIp().equals(nacosDiscoveryProperties.getIp())) {
				instance.setWeight(0);
				namingService.registerInstance(serviceName, instance);
			}
		}
		log.info("Nacos 服务权重为 0");
		return ResponseEntity.ok();
	}
}