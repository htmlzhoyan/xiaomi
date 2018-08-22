package com.zy.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.qianfeng.utils.JdbcUtil;
import com.zy.dao.ICartDao;
import com.zy.entity.Cart;
import com.zy.vo.CarandGood;
import com.zy.vo.GoodAndGrade;

public class CarDao implements ICartDao {

	@Override
	public void add(Cart car) {
		String sql = "insert into t_cart(goodId,goodNum,uid) values(?,?,?)";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		
		Object[] params = new Object[]{
				car.getGoodId(),
				car.getGoodNum(),
				car.getUid(),		
		};
		
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<CarandGood> findAll() {
		// TODO Auto-generated method stub
		String sql = "select g.*,c.goodNum,c.id from t_goods g INNER JOIN t_cart c on g.id = c.goodId order by c.id desc";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		List<CarandGood> list = null;
		try {
			list = qr.query(sql,new BeanListHandler<>(CarandGood.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		String sql = "select count(1) from t_cart";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		long count = 0;
		try {
			// 可以获取返回数据汇总第一行第一列的数据
			// 获取总记录数时，dbutils内部将其作为长整数处理
			count = qr.query(sql, new ScalarHandler<Long>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (int)count;
	}

	@Override
	public void delete(int id) {
		String sql = "delete from t_cart where id=?";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		try {
			qr.update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	

}
