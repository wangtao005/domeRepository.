package com.example.common;

import java.io.Serializable;
import java.util.List;

public class Pagination<T> implements Serializable{
	
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6209940733797293575L;
	private long total;
	private List<T> data;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
}
