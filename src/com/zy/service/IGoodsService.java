package com.zy.service;

import java.util.Date;
import java.util.List;

import com.zy.entity.Goods;
import com.zy.entity.Grade;
import com.zy.vo.GoodAndGrade;

public interface IGoodsService {
	public void addGoods(Goods goods);
	public Goods findGoodsById(int id);
	public Goods findGoodsByName(String name);
	
	public List<GoodAndGrade> findAllService();
	public void deleteServe(int id);
	public List<GoodAndGrade> searchService(String name,Date createDate,Date createDates);
	public void updateServe(Goods goods,int id);
}

