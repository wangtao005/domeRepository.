package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.common.Pagination;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;

/**
 * @version 1.0
 */
@Controller
@RequestMapping("/role")
public class RoleController {

private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	 
	@Autowired
	private RoleService RoleService;
 
	@RequestMapping(value="/listByPage")
	@ResponseBody
	private Pagination<Role> listByPage(Role Role,Integer pageSize,Integer pageIndex) {
		Pagination<Role> listByPage = RoleService.listByPage(Role,pageSize,pageIndex);
		return listByPage;
	}
	
	@RequestMapping(value="/save")
	@ResponseBody
	private Role save(Role Role) {
		Role save = RoleService.save(Role);
		return save;
	}

	@RequestMapping(value="/info")
	@ResponseBody
	private Role info(Long id) {
		Role save = RoleService.findById(id);
		return save;
	}
	
	 
}

