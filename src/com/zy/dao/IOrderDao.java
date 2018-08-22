package com.zy.dao;

import com.zy.entity.Order;

public interface IOrderDao {
	public long add(Order order);
	public void update(Integer id,Integer status);
}
