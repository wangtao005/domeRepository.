package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.base.entity.BaseEntity;

@Entity
@Table(name = "t_user")
public class User extends BaseEntity{
	

	
	/**
	 *序号
	 * wt
	 * 2019年5月9日
	 */
	private static final long serialVersionUID = -6916325931499732793L;
	@Column(name = "name")
	private String name;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
