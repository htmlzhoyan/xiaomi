package com.zy.service;

import java.util.List;


import com.zy.entity.Grade;
import com.zy.vo.GradeGood;

public interface IGradeService {
	public List<Grade> findName(int grade);
	public List<Grade> findAllGrade();
	public List<GradeGood> findAllGradeGood(int grade);
	public List<Grade> findGradeTypeService(int grade);
	public void addService(Grade grade);
	public void deleteService(int id);
	public List<Grade> searchService(int grade, String gradeName);
	public Grade findGradeIdServce(int id);
	public void updateService(Grade grade,int id);
	/**
	 * 
	 * @param grade 分类等级 1， 2
	 * @param gradeId 数据库中分类数据的id
	 * @return
	 */
	public List<GradeGood> findGradeGoods(int grade, int gradeId);
}
