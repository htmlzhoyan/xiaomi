package com.zy.dao;

import java.util.List;

import com.zy.entity.Grade;
import com.zy.vo.GradeGood;

public interface IGradeDao {
	/**
	 * 根据级别
	 * @param grade 1 一级 2  2级
	 * @return
	 */
	public List<Grade> findAll(int grade);
	
	public List<Grade> findGradeType(int grade);
	
	public List<Grade> findAllGrade();
	/**
	 * 差分类及分类下的商品数据
	 * @param grade
	 * @return
	 */
	public List<GradeGood> findByGoodGradeId(int grade);
	public List<GradeGood> findGradeGoodsById(int gradeId);
	public void add(Grade grade);
	public void delete(int id);
	public List<Grade> search(int grade,String gradeName);
	public void update(Grade grade,int id);
	public Grade findGradeId(int id);

	
}
