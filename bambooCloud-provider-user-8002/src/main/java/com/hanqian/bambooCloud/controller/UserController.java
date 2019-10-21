package com.hanqian.bambooCloud.controller;

import com.hanqian.bambooCloud.entity.User;
import com.hanqian.bambooCloud.service.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/16 。
 * ============================================================================
 */
@RestController
public class UserController {

	@Autowired
	private UserClientService userService;

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value = "user/list", produces = "application/json; charset=utf-8")
	public List<User> list(){
		System.out.println("----------8002----------");
		return userService.list();
	}

	@RequestMapping(value = "user/add", produces = "application/json; charset=utf-8")
	public String add(User user){
		userService.addUser(user);
		return "阿发的个";
	}

}
