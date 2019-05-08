package com.example.demo.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.example.base.dao.BaseRepository;
import com.example.demo.entity.User;

@Repository
public interface UserDao  extends BaseRepository<User, Serializable> {

}
