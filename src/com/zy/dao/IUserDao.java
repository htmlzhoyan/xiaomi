package com.zy.dao;

import com.zy.entity.User;

public interface IUserDao {
	public void addUser(User user);
	public User findByname(String name);

}
