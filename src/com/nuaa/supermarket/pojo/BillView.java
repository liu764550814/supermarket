package com.nuaa.supermarket.pojo;

import java.math.BigDecimal;

/**
* @author 刘超明
* @version 创建时间：2019年11月5日 上午9:28:13
* 类说明
*/
public class BillView {
	private Long id;
    private String billcode;

    private String productname;
    
    private String productunit;

    private BigDecimal productcount;

    private BigDecimal totalprice;

    private Integer ispayment;
    
    private String proname;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillcode() {
		return billcode;
	}

	public void setBillcode(String billcode) {
		this.billcode = billcode;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getProductunit() {
		return productunit;
	}

	public void setProductunit(String productunit) {
		this.productunit = productunit;
	}

	public BigDecimal getProductcount() {
		return productcount;
	}

	public void setProductcount(BigDecimal productcount) {
		this.productcount = productcount;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

	public Integer getIspayment() {
		return ispayment;
	}

	public void setIspayment(Integer ispayment) {
		this.ispayment = ispayment;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}
    
    
}
