package com.nuaa.supermarket.pojo;
/**
* @author 刘超明
* @version 创建时间：2019年10月31日 下午9:11:03
* 类说明
*/
public class QueryPro {
	// 参数供应商编码
	private String queryProCode;
	// 参数供应商名字
	private String queryProName;
	// 当前页码数
	private Integer page = 1;
/*	// 数据库从哪一条数据开始查
	private Integer start;*/
	// 每页显示数据条数
	private Integer rows = 10;
	
	public String getQueryProCode() {
		return queryProCode;
	}
	public void setQueryProCode(String queryProCode) {
		this.queryProCode = queryProCode;
	}
	public String getQueryProName() {
		return queryProName;
	}
	public void setQueryProName(String queryProName) {
		this.queryProName = queryProName;
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
