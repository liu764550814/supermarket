package com.nuaa.supermarket.service;

import com.nuaa.supermarket.pojo.QueryUser;
import com.nuaa.supermarket.pojo.SmbmsUser;
import com.nuaa.supermarket.pojo.UserAndRole;
import com.nuaa.supermarket.pojo.UserView;
import com.nuaa.supermarket.utils.Page;

/**
* @author 刘超明
* @version 创建时间：2019年11月1日 上午9:41:45
* 用户管理服务类
*/
public interface UserService {
	
	
	/**
	 * 
	 * 
	    * @Title: getCountByCondition
	    * @Description: 按条件查询符合条件的记录数
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	public int getCountByCondition(String userCode);
	
	/**
	 * 
	    * @Title: getUserByCondition
	    * @Description: 条件查询所有数据记录
	    * @param @param user
	    * @param @return    参数
	    * @return Page<SmbmsUser>    返回类型
	    * @throws
	 */
	
	
	
	public Page<UserAndRole> getUserByCondition(QueryUser user);
	
	
	/**
	 * 
	    * @Title: updatePassWord
	    * @Description: 修改密码
	    * @param @param userpw
	    * @param @param user
	    * @param @return    参数
	    * @return String    返回类型
	    * @throws
	 */
	public String updatePassWord(String userpw,SmbmsUser user);
	
	/**
	 * 
	    * @Title: selectOneById
	    * @Description: 查看用户信息
	    * @param @param id
	    * @param @return    参数
	    * @return UserView    返回类型
	    * @throws
	 */
	public UserView selectOneById(long id);
	
	//修改用户
	public int updateuser(UserView userView,long modifyById);
	//添加用户
	public int insertUser(SmbmsUser user);
	//删除用户
	public String deleteUser(long id);
}
