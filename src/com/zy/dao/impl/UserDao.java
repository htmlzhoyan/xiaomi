package com.zy.dao.impl;

import java.sql.SQLException;


import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;


import com.qianfeng.utils.JdbcUtil;

import com.zy.dao.IUserDao;
import com.zy.entity.User;

public class UserDao implements IUserDao{

	@Override
	public void addUser(User user) {
		String sql = "insert into t_user(username,password,phone,sex,createDate,role) values (?,?,?,?,?,?)";
		QueryRunner qRunner = JdbcUtil.getQueryRunner();
		Object[] params = new Object[] {
				user.getUsername(),
				user.getPassword(),
				user.getPhone(),
				user.getSex(),
				user.getCreateDate(),
				user.getRole()
		};
		try {
			qRunner.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public User findByname(String name) {
		System.out.println(name);
		String sql = "select * from t_user where username=?";
		QueryRunner qRunner = JdbcUtil.getQueryRunner();
		User us = null;
		try {
			us =  qRunner.query(sql, new BeanHandler<>(User.class),name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return us;
	}

}
