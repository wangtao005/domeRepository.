package com.example.base.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.base.dao.BaseRepository;
import com.example.base.service.BaseService;
@Service
public class BaseServiceImpl<T,ID extends Serializable> implements BaseService<T, Serializable>  {

	@Autowired
	private BaseRepository<T, Serializable> basetRepository;
	
	@Override
	public T  save(T entity) {
		T save = basetRepository.save(entity);
		return save;
	}

	@Override
	public T del(Long id) {
		 
		basetRepository.deleteById(id);
		T findById = findById(id);
		if(findById==null) {
			
		}
		return null;
	}

	@Override
	public T findById(Long id) {
		T one = basetRepository.getOne(id);
		return one;
	}
	
	
	
	
	
	
}
