package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 角色
 * @author wt
 */
@Entity
@Table(name = "t_role")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Role extends BaseEntity{
	
	/**
	 *序号
	 * wt
	 * 2019年5月18日
	 */
	private static final long serialVersionUID = 8412055495915429279L;

	@Column(name = "name",columnDefinition=" varchar(100) COMMENT '角色名称'")
	private String name;

	@Column(name = "rank",columnDefinition=" int(2) COMMENT '角色级别'")
	private int rank=0;

	@Column(name = "remark",columnDefinition=" varchar(500) COMMENT '备注信息'")
	private String remark;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	 
}
