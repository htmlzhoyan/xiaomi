package com.zy.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qianfeng.utils.JdbcUtil;
import com.zy.dao.IOrderDetailDao;
import com.zy.entity.Order;
import com.zy.entity.OrderDetail;

public class OrderDeatilDao implements IOrderDetailDao {

	@Override
	public void add(OrderDetail od) {
		// TODO Auto-generated method stub
		String sql = "insert into t_orderdetail(orderId,num,goodsId) values(?,?,?)";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		try {
			qr.update(sql, od.getOrderId(), od.getNum(), od.getGoodsId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Order> findAllByUid(int uid) {
		// TODO Auto-generated method stub
		String sql = "select * from t_order where uid = ? order by id desc";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		List<Order> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<>(Order.class), uid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	

}
