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
import com.example.demo.dao.RoleDao;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;


@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Serializable> implements RoleService{

	@Autowired
	private RoleDao roleDao;
	@Override
	public Pagination<Role> listByPage(Role role, Integer pageSize, Integer pageIndex) {
		Specification<Role> spec = new Specification<Role>() {
			/*** 序号 */
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> lstPredicates = new ArrayList<Predicate>();
				Predicate[] arrayPredicates = new Predicate[lstPredicates.size()];
				
				if (!StringUtils.isBlank(role.getName())) {
					lstPredicates.add(criteriaBuilder.like(root.get("name").as(String.class),"%"+role.getName()+"%"));
				}
				
				return criteriaBuilder.and(lstPredicates.toArray(arrayPredicates));
			}
		};
		
  
		
	 	@SuppressWarnings("deprecation")
		Page<Role> findAll = roleDao.findAll(spec, new PageRequest(pageIndex, pageSize, Sort.Direction.ASC, "id"));
	 	Pagination<Role> pagination = new Pagination<Role>();
	 	pagination.setData(findAll.getContent());
	 	pagination.setTotal(findAll.getTotalElements());
		return pagination;
	}
 
}
