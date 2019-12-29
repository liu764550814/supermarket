package com.nuaa.supermarket.utils;

import java.util.List;

/**
* @author 刘超明
* @version 创建时间：2019年10月31日 上午9:16:32
* 类说明
*/
public class Page<T> {
	private int page;//当前页码
	private int total;//共有多少条数据
	private int size;//每页显示多个条记录
	private List<T> list;//查询到的记录
	
	
	
	
	public Page() {
		super();
	}
	public Page(int page, int total, int size, List<T> list) {
		super();
		this.page = page;
		this.total = total;
		this.size = size;
		this.list = list;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	
	
}
