package com.example.demo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.base.service.impl.BaseServiceImpl;
import com.example.common.Pagination;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;


@Service
public class UserServiceImpl extends BaseServiceImpl<User, Serializable> implements UserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleService roleService;
	@Override
	public Pagination<User> listByPage(User user, Integer pageSize, Integer pageIndex) {
		Specification<User> spec = new Specification<User>() {
			/*** 序号 */
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> lstPredicates = new ArrayList<Predicate>();
				Predicate[] arrayPredicates = new Predicate[lstPredicates.size()];
				
				if (!StringUtils.isBlank(user.getName())) {
					lstPredicates.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+user.getName()+"%"));
				}
				
				return criteriaBuilder.and(lstPredicates.toArray(arrayPredicates));
			}
		};
		
  
		
	 	@SuppressWarnings("deprecation")
		Page<User> findAll = userDao.findAll(spec, new PageRequest(pageIndex, pageSize, Sort.Direction.ASC, "id"));
	 	Pagination<User> pagination = new Pagination<User>();
	 	pagination.setData(findAll.getContent());
	 	pagination.setTotal(findAll.getTotalElements());
		return pagination;
	}
	@Override
	public User info(Long id) {
			User findById = super.findById(id);
			List<Role> roles = new ArrayList<Role>();
			findById.setRoles(roles);
			String roleIds = findById.getRoleIds();
			String[] split = roleIds.split(",");
			for (String roleId : split) {
				Role findById2 = roleService.findById(roleId);
				roles.add(findById2);
			}
			
		return findById;
	}
 
}
