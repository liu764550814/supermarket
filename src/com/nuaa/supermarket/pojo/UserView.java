package com.nuaa.supermarket.pojo;

import java.util.Date;

/**
* @author 刘超明
* @version 创建时间：2019年11月4日 上午9:23:21
* 查看用户类
*/
public class UserView {
	private long id;
	private String userid;
	private String phone;
    private String usercode;

    private String username;
    
    private Integer gender;

    private Date birthday;
    private String sbirthday;
    private String address;

    private String rolename;

    private Integer userrole;
    
    
    
    
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getUserrole() {
		return userrole;
	}

	public void setUserrole(Integer userrole) {
		this.userrole = userrole;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSbirthday() {
		return sbirthday;
	}

	public void setSbirthday(String sbirthday) {
		this.sbirthday = sbirthday;
	}

	@Override
	public String toString() {
		return "UserView [id=" + id + ", userid=" + userid + ", phone=" + phone + ", usercode=" + usercode
				+ ", username=" + username + ", gender=" + gender + ", birthday=" + birthday + ", sbirthday="
				+ sbirthday + ", address=" + address + ", rolename=" + rolename + ", userrole=" + userrole + "]";
	}
    


}
