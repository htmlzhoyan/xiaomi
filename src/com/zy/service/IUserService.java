package com.zy.service;

import com.zy.entity.User;

public interface IUserService {
	/**
	 * 登录判断
	 * @param name 用户名
	 * @param password 密码
	 * @param role 角色
	 * @param validateCode 验证码
	 * @return 
	 */
	public User login(String name,String password, int role, String validateCode);
	public User byLoginName(String name);
	public void addService(User user);
}
