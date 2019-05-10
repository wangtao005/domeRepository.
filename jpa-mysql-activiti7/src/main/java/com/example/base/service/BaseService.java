package com.example.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface BaseService<T,ID extends Serializable> {
	
	
	T save(T entity);

	boolean del(ID id);
	
	T findById(ID id);
 
	List<T> findAll();
 
	List<T> findAll(Sort sort);
	 
	List<T> findAllById(Iterable<ID> ids);

	<S extends T> List<S> saveAll(Iterable<S> entities);
	 /**
	  * 刷新数据库中所有挂起的更改。
	  * 作者:wt
	  * 日期:2019年5月10日
	  */
	void flush();
	/**
	 * 保存实体并立即刷新更改。
	 * 作者:wt
	 * 日期:2019年5月10日
	 * @param entity
	 * @return
	 */
	<S extends T> S saveAndFlush(S entity);
	/**
	 * 删除批处理中的给定实体，
	 * 作者:wt
	 * 日期:2019年5月10日
	 * @param entities
	 */
	void deleteInBatch(Iterable<T> entities);

	void deleteAllInBatch();
 
	T getOne(ID id);

	<S extends T> List<S> findAll(Example<S> example);
 
	<S extends T> List<S> findAll(Example<S> example, Sort sort);

	Page<T> findAll(Pageable pageable);
	
	<S extends T> Optional<S> findOne(Example<S> example);
 
	<S extends T> Page<S> findAll(Example<S> example, Pageable pageable);
 
	<S extends T> long count(Example<S> example);
 
	<S extends T> boolean exists(Example<S> example);
	
	boolean existsById(ID id);

	long count();

	void deleteById(ID id);

	void delete(T entity);

	void deleteAll(Iterable<? extends T> entities);

	void deleteAll();
}
