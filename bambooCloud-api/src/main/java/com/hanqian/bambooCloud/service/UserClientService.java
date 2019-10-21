package com.hanqian.bambooCloud.service;

import com.hanqian.bambooCloud.entity.User;
import com.hanqian.bambooCloud.service.fallback.UserServiceFallback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/17 。
 * ============================================================================
 */
@Service
@FeignClient(value = "bambooCloud-user", fallback = UserServiceFallback.class)
public interface UserClientService {

	@RequestMapping(value = "/user/get/{id}", method = RequestMethod.GET)
	public User get(@PathVariable("id") String id);

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public List<User> list();

	@RequestMapping(value = "user/add", method = RequestMethod.POST)
	public boolean add(User user);

}
