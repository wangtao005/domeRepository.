package com.example.demo.service;

import java.io.Serializable;

import com.example.base.service.BaseService;
import com.example.common.Pagination;
import com.example.demo.entity.User;

public interface UserService extends BaseService<User,Serializable>  {

	Pagination<User> listByPage(User user, Integer pageSize, Integer pageIndex);

	// User findById(Long id);
	 
}
