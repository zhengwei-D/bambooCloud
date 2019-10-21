package com.hanqian.bambooCloud.controller;

import com.hanqian.bambooCloud.entity.User;
import com.hanqian.bambooCloud.service.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/16 。
 * ============================================================================
 */
@RestController
public class UserController_Consumer {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserClientService userService;

	@RequestMapping(value = "consumer/user/add")
	public boolean add(User user){
//		return restTemplate.postForObject("http://bambooCloud-user/user/add", user, Boolean.class);
		return userService.add(user);
	}

	@RequestMapping(value = "consumer/user/list")
	public List<User> list(){
//		return restTemplate.getForObject("http://bambooCloud-user/user/list",  List.class);
		return userService.list();
	}

	@RequestMapping(value = "consumer/user/get/{id}")
	public User get(@PathVariable("id") String id){
		System.out.println(id);
		return userService.get(id);
	}

}
