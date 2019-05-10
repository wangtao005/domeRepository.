package com.example.demo.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.base.service.impl.BaseServiceImpl;
import com.example.common.Pagination;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;


@Service
public class UserServiceImpl extends BaseServiceImpl<User, Serializable> implements UserService{

	@Autowired
	private UserDao userDao;
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
				
//				if (!StringUtils.isEmpty(planNodeName)) {
//					lstPredicates.add(criteriaBuilder.like(planNode.get("nodeName").as(String.class),"%"+planNodeName+"%"));
//				}
				
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
 
}
