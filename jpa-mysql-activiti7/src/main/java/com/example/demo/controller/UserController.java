package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.common.Pagination;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

/**
 * @version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	 
	@Autowired
	private UserService userService;
 
	@RequestMapping(value="/listByPage")
	@ResponseBody
	private Pagination<User> listByPage(User user,Integer pageSize,Integer pageIndex) {
		Pagination<User> listByPage = userService.listByPage(user,pageSize,pageIndex);
		return listByPage;
	}
	
	@RequestMapping(value="/save")
	@ResponseBody
	private User save(User user) {
		User save = userService.save(user);
		return save;
	}
	@RequestMapping(value="/roleAssignment")
	@ResponseBody
	private User roleAssignment(User user) {
		User save = userService.findById(user.getId());
		save.setRoleIds(user.getRoleIds());
		User save2 = userService.save(save);
		return save2;
	}

	@RequestMapping(value="/info")
	@ResponseBody
	private User info(Long id) {
		User save = userService.findById(id);
		return save;
	}
	
	 
}

