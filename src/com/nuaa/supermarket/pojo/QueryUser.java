package com.nuaa.supermarket.pojo;
/**
* @author 刘超明
* @version 创建时间：2019年11月1日 上午9:45:13
* 类说明
*/
public class QueryUser {
	
	private String queryname;
	private Integer queryUserRole;
	// 当前页码数
	private Integer page = 1;
	// 数据库从哪一条数据开始查
/*	private Integer start;*/
	// 每页显示数据条数
	private Integer rows = 10;
	
	
	public String getQueryname() {
		return queryname;
	}
	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}
	public Integer getQueryUserRole() {
		return queryUserRole;
	}
	public void setQueryUserRole(Integer queryUserRole) {
		this.queryUserRole = queryUserRole;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
/*	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}*/
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	
	
}
