package com.zy.dao;


import java.util.List;


import com.zy.entity.User;

public interface IUserManageDao {
	public List<User> findAllDao();
	public void deleteDao(int id);
	public List<User> search(String name, String sex);
	
}	
