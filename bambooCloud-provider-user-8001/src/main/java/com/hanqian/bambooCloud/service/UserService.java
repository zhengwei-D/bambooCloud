package com.hanqian.bambooCloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hanqian.bambooCloud.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * newFile
 * ============================================================================
 * author : dzw
 * createDate:  2019/10/16 。
 * ============================================================================
 */
public interface UserService extends IService<User> {

	String addUser(User user);

	List<User> list();

}
