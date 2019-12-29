package com.nuaa.supermarket.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nuaa.supermarket.mapper.SmbmsRoleMapper;
import com.nuaa.supermarket.pojo.SmbmsRole;
import com.nuaa.supermarket.pojo.SmbmsRoleExample;
import com.nuaa.supermarket.service.RoleService;
import com.nuaa.supermarket.utils.Page;

/**
* @author 刘超明
* @version 创建时间：2019年11月1日 下午3:57:07
* 类说明
*/
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private SmbmsRoleMapper roleMapper;
	
	@Override
	public List<SmbmsRole> getAllRoleNoCondition() {
		return roleMapper.getAllRoleNoCondition();
	}

	@Override
	public Page<SmbmsRole> getAllRole(Integer pageIndex) {
		
		SmbmsRoleExample roleExample = new SmbmsRoleExample();
		
		System.out.println("11111 = "+pageIndex);

			//设置分页查询时从第几条数据开始查询
		roleExample.setStart((pageIndex-1)*10);

		
		
		System.out.println("2222");
		//设置查询的时候每页有多少数据
		roleExample.setRows(10);
		System.out.println("333 = "+roleMapper.countByExample(null));
		//查询数据，并接收
		
		List<SmbmsRole> list = roleMapper.selectByExample(roleExample);
		for (SmbmsRole smbmsRole : list) {
			System.out.println("name = "+smbmsRole.getRolename());
		}
		System.out.println("4444");
		Page<SmbmsRole> page = new Page<SmbmsRole>(pageIndex,roleMapper.countByExample(null),10,roleMapper.selectByExample(roleExample));
		return page;
	}

	@Override
	public String insertRole(long createById,String roleCode, String roleName) {
		
		//获取当前时间
		//SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String time = dFormat.format(new Date());
		Date time = new Date();
		
		//设置要添加的role的信息
		SmbmsRole role = new SmbmsRole();
		role.setRolecode(roleCode);
		role.setRolename(roleName);
		role.setCreatedby(createById);
		role.setCreationdate(time);

		
		//插入操作
		int size = roleMapper.insertSelective(role);
		//判断是否成功
		if (size>0) {
			System.out.println("添加成功");
			
			return "添加成功";
		}
		System.out.println("添加失败");
		return "添加失败";	
	}

	@Override
	public String deleteRole(long id) {
		//获取删除返回的结果
		int size = 0;
		try {
			//添加条件
			SmbmsRoleExample roleExample = new SmbmsRoleExample();
			SmbmsRoleExample.Criteria criteria = roleExample.createCriteria();
			criteria.andCreatedbyEqualTo(id);
			//查看该用户是否创建过其他用户，如果有则不能删除
			System.out.println("id = "+id);
			int createById = roleMapper.countByExample(roleExample);//.selectByPrimaryKey(id);//.selectByExample(roleExample);//
			//role.getCreatedby();
			
			System.out.println("createById = "+createById);
			if (createById > 0) {
				return String.valueOf(createById);
			}
			//取得删除的影响数据的行数
			size = roleMapper.deleteByPrimaryKey(id);
			if (size > 0) {
				return "true";
			}
			if (size == 0) {
				return "notexist";
			}
			return "false";
			
		} catch (Exception e) {
			return "false";
		}
	}

	@Override
	public SmbmsRole updateRole(long id) {
		
		SmbmsRole role = roleMapper.selectByPrimaryKey(id);
		
		if (role != null) {

			return role;
		}
		return null;
	}

	@Override
	public String saveupdateRole(SmbmsRole role) {
		System.out.println(role.getId()+","+role.getRolename());
		
		//修改数据
		int size = roleMapper.updateByPrimaryKeySelective(role);
		//查看是否修改成功
		if (size > 0) {
			return "修改成功";
		}
		return "修改失败";
	}

}
