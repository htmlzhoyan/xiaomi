package com.zy.dao.impl;

import java.sql.SQLException;
import java.util.List;



import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qianfeng.utils.JdbcUtil;
import com.zy.dao.IUserManageDao;
import com.zy.entity.Grade;
import com.zy.entity.User;

public class UserManageDao implements IUserManageDao{

	@Override
	public List<User> findAllDao() {
		// TODO Auto-generated method stub
		String sql = "select * from t_user";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		List<User> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void deleteDao(int id) {
		String sql = "delete from t_user where id=?";
		QueryRunner qr =  JdbcUtil.getQueryRunner();
		try {
			qr.update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> search(String username, String sex) {
		String sql = "";
		List<User> list = null;
		QueryRunner qr = JdbcUtil.getQueryRunner();
		System.out.println("====="+username+sex);
		if(username==null && sex==null) {
			sql = "select * from t_user";
			try {
				list = qr.query(sql, new BeanListHandler<>(User.class));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(username==null) {
			sql = "select * from t_user where sex = ?";	
			try {	
				list = qr.query(sql, new BeanListHandler<>(User.class), sex);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(sex==null) {
			sql = "select * from t_user where username = ?";	
			try {	
				list = qr.query(sql, new BeanListHandler<>(User.class), username);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			sql = "select * from t_user where sex = ? and username = ?";	
			try {	
				list = qr.query(sql, new BeanListHandler<>(User.class), sex, username);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
		// TODO Auto-generated method stub
	
	}

}
