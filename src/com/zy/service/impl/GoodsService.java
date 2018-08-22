package com.zy.service.impl;

import java.util.Date;
import java.util.List;

import com.zy.dao.IGoodsDao;
import com.zy.dao.impl.GoodsDao;
import com.zy.entity.Goods;
import com.zy.entity.Grade;
import com.zy.service.IGoodsService;
import com.zy.vo.GoodAndGrade;

public class GoodsService implements IGoodsService {
	private IGoodsDao GoodsDao = new GoodsDao();
	@Override
	public void addGoods(Goods goods) {
		if(goods == null) {
			throw new RuntimeException();
		}
		try {
			GoodsDao.add(goods);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Goods findGoodsById(int id) {
		// TODO Auto-generated method stub
		try {
			return GoodsDao.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	public List<GoodAndGrade> findAllService() {
		// TODO Auto-generated method stub
		return GoodsDao.findAll();
	}
	@Override
	public void deleteServe(int id) {
		GoodsDao.delete(id);
		
	}
	@Override
	public Goods findGoodsByName(String name) {
		// TODO Auto-generated method stub
		
		try {
			return GoodsDao.findByName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	public List<GoodAndGrade> searchService(String name, Date createDate,Date createDates) {
		// TODO Auto-generated method stub
		return GoodsDao.search(name, createDate,createDates);
	}
	@Override
	public void updateServe(Goods goods, int id) {
		GoodsDao.update(goods, id);
		
	}

}
