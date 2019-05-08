package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public User getById(Long id) {
		 User one = userDao.getOne(id);
		return one;
	}

	@Override
	public User save(User user) {
		User save = userDao.save(user);
		return save;
	}
	
}
