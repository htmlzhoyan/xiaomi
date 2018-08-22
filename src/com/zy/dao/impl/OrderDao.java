package com.zy.dao.impl;

import java.math.BigInteger;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.qianfeng.utils.JdbcUtil;
import com.zy.dao.IOrderDao;
import com.zy.entity.Order;

public class OrderDao implements IOrderDao{

	@Override
	public long add(Order order) {
		// TODO Auto-generated method stub
		String sql = "insert into t_order(orderCode,uid,createDate,money,status,imgPath) values(?,?,?,?,?,?)";
		// 获取自增的id
		String sql2 = "select @@identity";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		Object[] params = new Object[]{
				order.getOrderCode(),
				order.getUid(),
				order.getCreateDate(),
				order.getMoney(),
				order.getStatus(),
				order.getImgPath()
		};
		BigInteger num = null;
		try {
			qr.update(sql, params);
			//
			num = (BigInteger) qr.query(sql2,new ScalarHandler(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return num.longValue();
	}

	@Override
	public void update(Integer id, Integer status) {
		String sql = "update t_order set status=? where id=?";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		try {
			qr.update(sql,status,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
