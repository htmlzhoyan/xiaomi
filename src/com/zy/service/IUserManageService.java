package com.zy.service;

import java.util.List;

import com.zy.entity.User;

public interface IUserManageService {
	public List<User> byAllUserName();
	public void deleteService(int id);
	public List<User> searchService(String name,String sex);
}
