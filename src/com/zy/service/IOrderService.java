package com.zy.service;

import java.util.List;

import com.zy.entity.Order;
import com.zy.vo.GoodsInfo;

public interface IOrderService {
	
	public void addOrder(int uid, List<GoodsInfo> infos,String imgPath);

	public List<Order> findAllOrdersByUid(int uid);
	
	public void updateByid(Integer id, Integer status);


}
