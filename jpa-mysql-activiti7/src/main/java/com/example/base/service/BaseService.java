package com.example.base.service;

import java.io.Serializable;

public interface BaseService<T,ID extends Serializable> {
	
	
	T save(T entity);

	T del(Long id);
	
	T findById(Long id);
}
