package com.springCloud.k8s.consumer.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springCloud.k8s.consumer.model.ConsumerUserVO;
import com.springCloud.k8s.provider.api.ProviderUserApi;
import com.springCloud.k8s.provider.model.ProviderUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * 
 * @ClassName: ConsumerUserController
 * @Description: 消费者用户控制层
 * @author WangXf
 * @date 2023年3月1日 上午11:43:20
 *
 */
@Slf4j
@Api(tags = "ConsumerUserController", description = "消费者用户控制层")
@RestController
@RequestMapping("/consumerUserApi")
public class ConsumerUserController {

	@Autowired
	private ProviderUserApi providerUserApi;

	/**
	 * 
	 * @Title: list  
	 * @Description: 查询消费者的用户信息VO对象列表
	 * @return List<ConsumerUserVO>    返回类型  
	 * @throws
	 */
	@ApiOperation("消费者用户控制层->查询消费者的用户信息VO对象列表")
	@GetMapping("/list")
	public Flux<ConsumerUserVO> list() {
		log.info("消费者用户控制层->查询消费者的用户信息VO对象列表");
		return Flux.just(new ConsumerUserVO().setId(1L).setName("哈哈").setAge(11).setRemark("hello world"),
				new ConsumerUserVO().setId(2L).setName("呵呵").setAge(11).setRemark("hello world"));

	}

	/**
	 * 
	 * @Title: listProviderUserVOs  
	 * @Description: 查询提供者的用户信息VO对象列表  
	 * @return List<ProviderUserVO>    返回类型  
	 * @throws
	 */
	@ApiOperation("消费者用户控制层->查询提供者的用户信息VO对象列表")
	@GetMapping("/listProviderUserVOs")
	public List<ProviderUserVO> listProviderUserVOs() {
		log.info("消费者用户控制层->查询提供者的用户信息VO对象列表");
		return providerUserApi.list().toStream().collect(Collectors.toList());
	}

}
