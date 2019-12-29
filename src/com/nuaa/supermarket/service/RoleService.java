package com.nuaa.supermarket.service;
/**
* @author 刘超明
* @version 创建时间：2019年11月1日 下午3:56:22
* 角色service接口
*/

import java.util.List;

import com.nuaa.supermarket.pojo.SmbmsRole;
import com.nuaa.supermarket.utils.Page;

public interface RoleService {
	/**
	 * 
	    * @Title: getAllRoleNoCondition
	    * @Description: 非条件查询获得所有数据
	    * @param @return    参数
	    * @return List<SmbmsRole>    返回类型
	    * @throws
	 */
	List<SmbmsRole> getAllRoleNoCondition();
	/**
	 * 
	    * @Title: getAllRole
	    * @Description: 获取所有的的role信息
	    * @param @param pageIndex
	    * @param @return    参数
	    * @return Page<SmbmsRole>    返回类型
	    * @throws
	 */
	Page<SmbmsRole> getAllRole(Integer pageIndex);
	
	/**
	 * 
	    * @Title: insertRole
	    * @Description: 添加新的role
	    * @param     参数
	    * @return void    返回类型
	    * @throws
	 */
	String insertRole(long createById,String roleCode,String roleName);
	
	String deleteRole(long id);
	
	SmbmsRole updateRole(long id);
	
	String saveupdateRole(SmbmsRole role);
}
