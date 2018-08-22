package com.zy.service;

import java.util.List;

import com.zy.entity.Cart;
import com.zy.vo.CarandGood;

public interface ICarService {
	public void addCar(Cart car);
	public List<CarandGood> findAllCar();
	public int countCar();
	public void deleteService(int id);
}
