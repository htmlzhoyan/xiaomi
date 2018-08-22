package com.zy.service.impl;

import java.util.List;

import com.zy.dao.IUserManageDao;
import com.zy.dao.impl.UserManageDao;
import com.zy.entity.User;
import com.zy.service.IUserManageService;

public class UserManageService implements IUserManageService{
	IUserManageDao ius = new UserManageDao();
	@Override
	public List<User> byAllUserName() {
		// TODO Auto-generated method stub
		return ius.findAllDao();
	}
	@Override
	public void deleteService(int id) {
		ius.deleteDao(id);
		
	}
	@Override
	public List<User> searchService(String name, String sex) {
		return ius.search(name,sex);
		
	}
	
}
