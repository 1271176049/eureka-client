package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DcController {

	@Autowired
	DiscoveryClient discoveryClient;

	// http://localhost:2001/dc
	/**
	 * 由于返回前延迟了5秒，而服务消费方触发了服务请求超时异常，
	 * <p>
	 * 服务消费者就通过HystrixCommand注解中指定的降级逻辑进行执行，
	 * <p>
	 * 因此该请求的结果返回了fallback。这样的机制， 对自身服务起到了
	 * <p>
	 * 基础的保护，同时还为异常情况提供了自动的服务降级切换机制。
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	@GetMapping("/dc")
	public String dc() throws InterruptedException {
//		Thread.sleep(5000L);
		String services = "Services: " + discoveryClient.getServices();
		System.out.println(services);
		return services;
	}

}