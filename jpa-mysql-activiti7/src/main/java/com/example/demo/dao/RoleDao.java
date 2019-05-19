package com.example.demo.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.example.base.dao.BaseRepository;
import com.example.demo.entity.Role;

@Repository
public interface RoleDao  extends BaseRepository<Role, Serializable> {

}
