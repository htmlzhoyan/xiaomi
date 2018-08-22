package com.zy.dao;


import java.util.List;

import com.zy.entity.Order;
import com.zy.entity.OrderDetail;

public interface IOrderDetailDao {
	public void add(OrderDetail OrderDetail);

	public List<Order> findAllByUid(int uid);
}
