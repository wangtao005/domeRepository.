package com.example.base.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Service;

import com.example.base.dao.BaseRepository;
import com.example.base.entity.BaseEntity;
import com.example.base.service.BaseService;
@NoRepositoryBean
public class BaseServiceImpl<T extends BaseEntity,ID extends Serializable> implements BaseService<T , Serializable>  {

	@Autowired
	private BaseRepository<T, Serializable> basetRepository;
	
	@Override
	public T  save(T entity) {
		Long id = entity.getId();
		if(id==null) {
			entity.setCreateDate(new Date());
			entity.setCreateUserName("WT");
			entity.setCreateUserId(1l);
		}else {
			T temp = this.findById(entity.getId());
			entity.setCreateDate(temp.getCreateDate());
			entity.setCreateUserName(temp.getCreateUserName());
			entity.setCreateUserId(temp.getCreateUserId());
			entity.setUpdataDate(new Date());
			entity.setUpdataUserId(1l);
			entity.setUpdataUserName("WT");
		}
		T save = basetRepository.save(entity);
		return save;
	}

	@Override
	public List<T> findAll() {
		List<T> findAll = basetRepository.findAll();
		return findAll;
	}

	@Override
	public List<T> findAll(Sort sort) {
		List<T> findAll = basetRepository. findAll(sort);
		return findAll;
	}

	@Override
	public List<T> findAllById(Iterable<Serializable> ids) {
		List<T> findAllById = basetRepository.findAllById(ids);
		return findAllById;
	}

	@Override
	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		List<S> saveAll = basetRepository.saveAll(entities);
		return saveAll;
	}

	@Override
	public void flush() {
		basetRepository.flush();
	}

	@Override
	public <S extends T> S saveAndFlush(S entity) {
		S saveAndFlush = basetRepository.saveAndFlush(entity);
		return saveAndFlush;
	}

	@Override
	public void deleteInBatch(Iterable<T> entities) {
		basetRepository.deleteInBatch(entities);
		
	}

	@Override
	public void deleteAllInBatch() {
		basetRepository.deleteAllInBatch();
		
	}

	@Override
	public T getOne(Serializable id) {
		T one = basetRepository.getOne(id);
		return one;
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example) {
		List<S> findAll = basetRepository.findAll(example);
		return findAll;
	}

	@Override
	public <S extends T> List<S> findAll(Example<S> example, Sort sort) {
		List<S> findAll = basetRepository.findAll(example, sort);
		return findAll;
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		Page<T> findAll = basetRepository.findAll(pageable);
		return findAll;
	}

	@Override
	public <S extends T> Optional<S> findOne(Example<S> example) {
		Optional<S> findOne = basetRepository.findOne(example);
		return findOne;
	}

	@Override
	public <S extends T> Page<S> findAll(Example<S> example, Pageable pageable) {
		Page<S> findAll = basetRepository.findAll(example, pageable);
		return findAll;
	}

	@Override
	public <S extends T> long count(Example<S> example) {
		long count = basetRepository.count(example);
		return count;
	}

	@Override
	public <S extends T> boolean exists(Example<S> example) {
		boolean exists = basetRepository.exists(example);
		return exists;
	}

	@Override
	public boolean existsById(Serializable id) {
		boolean existsById = basetRepository.existsById(id);
		return existsById;
	}

	@Override
	public long count() {
		long count = basetRepository.count();
		return count;
	}

	@Override
	public void deleteById(Serializable id) {
		basetRepository.deleteById(id);
		
	}

	@Override
	public void delete(T entity) {
		basetRepository.delete(entity);
		
	}

	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		basetRepository.deleteAll(entities);
		
	}

	@Override
	public void deleteAll() {
		basetRepository.deleteAll();
	}

	@Override
	public boolean del(Serializable id) {
		 try {
			 basetRepository.deleteById(id);
			 return true;
		} catch (Exception e) {
			 e.printStackTrace();
			 return false;
		}
	}

	@Override
	public T findById(Serializable id) {
	  Optional<T> findById = basetRepository.findById(id);
	  T one = findById.get();
		return one;
	}
	 
	
}
