package com.zy.dao;

import java.util.List;

import com.zy.entity.Cart;
import com.zy.vo.CarandGood;

public interface ICartDao {
	public void add(Cart car);
	public List<CarandGood> findAll();
	public int count();
	public void delete(int id);
}
