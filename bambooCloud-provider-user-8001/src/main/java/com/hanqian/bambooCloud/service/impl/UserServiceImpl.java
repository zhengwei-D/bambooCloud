package com.hanqian.bambooCloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanqian.bambooCloud.dao.UserDao;
import com.hanqian.bambooCloud.entity.User;
import com.hanqian.bambooCloud.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/16 。
 * ============================================================================
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

	@Override
	public String addUser(User user) {
		save(user);
		return "123";
	}

	@Override
	@HystrixCommand
	public List<User> list() {
		return super.list();
	}

}
