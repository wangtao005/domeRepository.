package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_user")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class User extends BaseEntity{
	
	/**
	 *序号
	 * wt
	 * 2019年5月9日
	 */
	private static final long serialVersionUID = -6916325931499732793L;
	
	@Column(name = "name",length=50)
	private String name;

	@Column(name = "age",length=3)
	private Integer age;

	@Column(name = "sex",length=2)
	private int sex=0;
	
	@Column(name = "address",length=50)
	private String address;
	
	@Column(name = "phone",length=50)
	private String phone;
	
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
