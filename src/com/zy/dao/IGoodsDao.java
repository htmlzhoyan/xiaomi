package com.zy.dao;

import java.util.Date;
import java.util.List;

import com.zy.entity.Goods;
import com.zy.entity.Grade;
import com.zy.vo.GoodAndGrade;

public interface IGoodsDao {
	public void add(Goods goods);
	public void delete(int id);
	public Goods findById(int id);
	public Goods findByName(String name);
	
	public List<GoodAndGrade> findAll();
	public List<GoodAndGrade> search(String name,Date createDate, Date createDates);
	public void update(Goods goods,int id);
}
