package com.example.base.dao;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.base.entity.BaseEntity;
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity,ID extends Serializable> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T>{
	
	
	
 
	 
}
