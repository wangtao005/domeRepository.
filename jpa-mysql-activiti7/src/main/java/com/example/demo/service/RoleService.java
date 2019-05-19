package com.example.demo.service;

import java.io.Serializable;

import com.example.base.service.BaseService;
import com.example.common.Pagination;
import com.example.demo.entity.Role;

public interface RoleService extends BaseService<Role,Serializable>  {

	Pagination<Role> listByPage(Role Role, Integer pageSize, Integer pageIndex);

	// Role findById(Long id);
	 
}
