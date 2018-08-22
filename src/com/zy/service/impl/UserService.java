package com.zy.service.impl;

import com.qianfeng.utils.MD5Utils;
import com.qianfeng.utils.StringUtils;
import com.zy.dao.IUserDao;
import com.zy.dao.impl.UserDao;

import com.zy.entity.User;
import com.zy.service.IUserService;

public class UserService implements IUserService {
	private IUserDao userDao = new UserDao();
	@Override
	public User login(String name, String password, int role, String validateCode) {
		if(StringUtils.isEmpty(name)) {
			throw new RuntimeException("用户名不能为空");
		}
		if(StringUtils.isEmpty(password)) {
			throw new RuntimeException("密码不能为空");
			
		}
		User user = null;
		try {
			user = userDao.findByname(name);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		System.out.println(user);
		if(user==null) {
			throw new RuntimeException("输入用户名错误");
		}
		if(role == 1) {
			if(!user.getPassword().equals(password)) {
				throw new RuntimeException("输入的密码错误");
			}else {
				
			}
		}
		return user;		
	}
	@Override
	public User byLoginName(String name) {
		// TODO Auto-generated method stub
		return userDao.findByname(name);
	}
	@Override
	public void addService(User user) {
		if(user == null) {
			throw new RuntimeException("用户数据为空");
		}
		if(StringUtils.isEmpty(user.getUsername())) {
			throw new RuntimeException("用户名不能为空");
		}
		if(StringUtils.isEmpty(user.getPassword())) {
			throw new RuntimeException("密码不能为空");
		}
		//if(!user.getPassword().equals(user.get))
		User user2 = userDao.findByname(user.getUsername());
		if(user2 != null) {
			throw new RuntimeException("用户名已经注册");
		}
		try {
			userDao.addUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
