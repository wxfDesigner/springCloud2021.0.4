package com.springCloud.k8s.provider.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springCloud.k8s.consumer.api.ConsumerUserApi;
import com.springCloud.k8s.consumer.model.ConsumerUserVO;
import com.springCloud.k8s.provider.model.ProviderUserVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * 
 * @ClassName: ProviderUserController
 * @Description: 提供者用户控制层
 * @author WangXf
 * @date 2023年3月1日 上午11:43:20
 *
 */
@Slf4j
@Api(tags = "ProviderUserController", description = "提供者用户控制层")
@RestController
@RequestMapping("/providerUserApi")
public class ProviderUserController {

	@Autowired
	private ConsumerUserApi consumerUserApi;

	/**
	 * 
	 * @Title: list 
	 * @Description: 查询提供者的用户信息VO对象列表 
	 * @return Flux<ProviderUserVO> 返回类型
	 * @throws
	 */
	@ApiOperation("提供者用户控制层->查询提供者的用户信息VO对象列表")
	@GetMapping("/list")
	public Flux<ProviderUserVO> list() {
		log.info("提供者用户控制层->查询提供者的用户信息VO对象列表");
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
		return Flux.just(new ProviderUserVO().setId(1L).setName("哈哈").setAge(11).setRemark("hello world"),
				new ProviderUserVO().setId(2L).setName("呵呵").setAge(11).setRemark("hello world"));
	}

	/**
	 * 
	 * @Title: listConsumerUserVOs 
	 * @Description: 查询消费者的用户信息VO对象列表 
	 * @return List<ConsumerUserVO> 返回类型 
	 * @throws
	 */
	@ApiOperation("提供者用户控制层->查询消费者的用户信息VO对象列表")
	@GetMapping("/listConsumerUserVOs")
	public List<ConsumerUserVO> listConsumerUserVOs() {
		log.info("提供者用户控制层->查询消费者的用户信息VO对象列表");
		return consumerUserApi.list().toStream().collect(Collectors.toList());
	}

}
