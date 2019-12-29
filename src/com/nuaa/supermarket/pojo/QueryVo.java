package com.nuaa.supermarket.pojo;
/**
* @author 刘超明
* @version 创建时间：2019年10月31日 上午9:38:51
* billlist 的参数接收类
*/
public class QueryVo {
	
	private String queryProductName;
	private Integer queryProviderId;
	private Integer queryIsPayment;
	// 当前页码数
	private Integer page = 1;
	// 数据库从哪一条数据开始查
	//private Integer start;
	// 每页显示数据条数
	private Integer rows = 10;
	public String getQueryProductName() {
		return queryProductName;
	}
	public void setQueryProductName(String queryProductName) {
		this.queryProductName = queryProductName;
	}
	public Integer getQueryProviderId() {
		return queryProviderId;
	}
	public void setQueryProviderId(Integer queryProviderId) {
		this.queryProviderId = queryProviderId;
	}
	public Integer getQueryIsPayment() {
		return queryIsPayment;
	}
	public void setQueryIsPayment(Integer queryIsPayment) {
		this.queryIsPayment = queryIsPayment;
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
