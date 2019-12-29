package com.nuaa.supermarket.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuaa.supermarket.mapper.SmbmsUserMapper;
import com.nuaa.supermarket.pojo.QueryUser;
import com.nuaa.supermarket.pojo.SmbmsUser;
import com.nuaa.supermarket.pojo.SmbmsUserExample;
import com.nuaa.supermarket.pojo.UserAndRole;
import com.nuaa.supermarket.pojo.UserView;
import com.nuaa.supermarket.service.UserService;
import com.nuaa.supermarket.utils.Page;

/**
* @author 刘超明
* @version 创建时间：2019年11月1日 上午9:42:09
* 类说明
*/
@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private SmbmsUserMapper userMapper;
	
	@Override
	public Page<UserAndRole> getUserByCondition(QueryUser user) {	
		SmbmsUserExample userExample = new SmbmsUserExample();
		SmbmsUserExample.Criteria criteria = userExample.createCriteria();
		//设置分页限制
		userExample.setStart((user.getPage()-1)*user.getRows());
		userExample.setRows(user.getRows());
		//添加条件
		if(user.getQueryname() != null &&  user.getQueryname() != ""){
			criteria.andUsernameLike("%"+ user.getQueryname() +"%");
		}
		if (user.getQueryUserRole() != null) {
			criteria.andUserroleEqualTo(user.getQueryUserRole());
		}		
		
		//SmbmsUser temp = new SmbmsUser();
		System.out.println("        测试开始运行！");
		List<UserAndRole> list = userMapper.getUserAndRole(userExample);
		for (UserAndRole userAndRole : list) {
			
			System.out.println("=======rolename======="+userAndRole.getRolename());
			
		}
		
		
		//接收查询的数据并且封装
		Page<UserAndRole> page = new Page<UserAndRole>(user.getPage(),userMapper.countByExample(userExample),user.getRows(),list);	
		return page;
	}
	
	
	@Override
	public String updatePassWord(String userpw,SmbmsUser user) {
		//在对象里设置新的密码
		user.setUserpassword(userpw);
		try {
			//更改密码
			int size = userMapper.updateByPrimaryKeySelective(user);
			
			if(size > 0){
				System.out.println("密码设置成功");
				return "密码设置成功";
			}else{
				System.out.println("密码设置失败");
				return "密码设置失败";
			}
		} catch (Exception e) {
			System.out.println("密码设置失败");
		}
		System.out.println("密码设置失败");
		return "密码设置失败";
	}


	@Override
	public int getCountByCondition(String userCode) {
		//添加查询条件
		SmbmsUserExample userExample = new SmbmsUserExample();
		SmbmsUserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUsercodeEqualTo(userCode);
		//执行查询
		int count=userMapper.countByExample(userExample);
		
		return count;
	}


	@Override
	public UserView selectOneById(long id) {
		
		UserView userView = userMapper.selectOneById(id);
			if (userView == null) {
				return null;
			}else {
				return userView;
			}	
	}


	@Override
	public int updateuser(UserView userView,long modifyById) {
		//设置要更新的内容
		SmbmsUser user = new SmbmsUser();
		//把id转换为int
		int id = Integer.valueOf(userView.getUserid());
		user.setId((long) id);
		user.setUsercode(userView.getUsercode());
		user.setUsername(userView.getUsername());
		user.setGender(userView.getGender());
		System.out.println("updateuser 3");
		//把日期转换为date
		Date date = null;
		DateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = dFormat.parse(userView.getSbirthday());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		user.setBirthday(date);	
		user.setPhone(userView.getPhone());
		user.setAddress(userView.getAddress());
		user.setUserrole(userView.getUserrole());
		System.out.println("updateuser 4");
		user.setModifydate(new Date());
		System.out.println("updateuser 5");
		//执行更新
		int count = userMapper.updateByPrimaryKeySelective(user);
		
		return count;
	}


	@Override
	public int insertUser(SmbmsUser user) {
		//执行插入操作并得到返回值
		int count = userMapper.insertSelective(user);
		return count;
	}


	@Override
	public String deleteUser(long id) {
		
		int count = -1;
		try {
			count = userMapper.deleteByPrimaryKey(id);
			if (count == 0) {
				return "notexist";
			}else if(count > 0){
				return "true";
			}
		} catch (Exception e) {
			return "false";
		}
		return "false";
	}

}
