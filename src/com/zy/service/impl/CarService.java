package com.zy.service.impl;

import java.util.List;

import com.zy.dao.ICartDao;
import com.zy.dao.impl.CarDao;
import com.zy.entity.Cart;
import com.zy.service.ICarService;
import com.zy.vo.CarandGood;

public class CarService implements ICarService {
	private ICartDao cart = new CarDao();
	
	@Override
	public void addCar(Cart car) {	
		try {
			cart.add(car);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<CarandGood> findAllCar() {
		// TODO Auto-generated method stub
		return cart.findAll();
	}

	@Override
	public int countCar() {
		// TODO Auto-generated method stub
		return cart.count();
	}

	@Override
	public void deleteService(int id) {
		cart.delete(id);
		
	}

}
