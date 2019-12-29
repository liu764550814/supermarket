package com.nuaa.supermarket.service;
/**
* @author 刘超明
* @version 创建时间：2019年10月30日 下午3:02:04
* 类说明
*/

import com.nuaa.supermarket.pojo.SmbmsUser;

public interface LoginService {
	
	/**
	 * 
	    * @Title: getUserByQuery
	    * @Description: 查询数据库用户的用户或者密码是否正确
	    * 
	    * @param @param userCode
	    * @param @param userPassword
	    * @param @return    参数
	    * @return SmbmsUser    返回类型
	    * @throws
	 */
	SmbmsUser getUserByQuery(String userCode,String userPassword);
}
