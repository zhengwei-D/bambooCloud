package com.hanqian.bambooCloud.controller;

import com.hanqian.bambooCloud.entity.User;
import com.hanqian.bambooCloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
	private UserService userService;

	@RequestMapping(value = "user/list", produces = "application/json; charset=utf-8")
	public List<User> list(){
		System.out.println("----------8001----------");
		return userService.list();
	}

	@RequestMapping(value = "user/add", produces = "application/json; charset=utf-8")
	public String add(User user){
		userService.addUser(user);
		return "阿发的个";
	}

}
