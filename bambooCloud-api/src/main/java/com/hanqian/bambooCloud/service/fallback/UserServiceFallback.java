package com.hanqian.bambooCloud.service.fallback;

import com.hanqian.bambooCloud.entity.User;
import com.hanqian.bambooCloud.service.UserClientService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/17 。
 * ============================================================================
 */
@Component
@RequestMapping("fallback/userServiceFallback")
public class UserServiceFallback implements UserClientService {


	@Override
	public User get(String id) {
		System.out.println("服务降级 get");
		return null;
	}

	@Override
	public List<User> list() {
		System.out.println("服务降级 list");
		return null;
	}

	@Override
	public boolean add(User user) {
		System.out.println("服务降级 add");
		return false;
	}
}
