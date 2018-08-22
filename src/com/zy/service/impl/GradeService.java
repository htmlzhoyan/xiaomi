package com.zy.service.impl;

import java.util.List;

import com.zy.dao.IGradeDao;

import com.zy.dao.impl.GradeDao;

import com.zy.entity.Grade;
import com.zy.service.IGradeService;
import com.zy.vo.GradeGood;

public class GradeService implements IGradeService {
	private IGradeDao gradeDao = new GradeDao();

	@Override
	public List<Grade> findName(int grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Grade> findAllGrade() {
		// TODO Auto-generated method stub
		return gradeDao.findAllGrade();
	}

	@Override
	public List<GradeGood> findAllGradeGood(int grade) {
		// TODO Auto-generated method stub
		return gradeDao.findByGoodGradeId(grade);
	}

	@Override
	public List<Grade> findGradeTypeService(int grade) {
		// TODO Auto-generated method stub
		return gradeDao.findGradeType(grade);
	}

	@Override
	public void addService(Grade grade) {
		gradeDao.add(grade);
		
	}

	@Override
	public void deleteService(int id) {
		gradeDao.delete(id);
		
	}

	@Override
	public List<Grade> searchService(int grade, String gradeName) {
		// TODO Auto-generated method stub
		return gradeDao.search(grade, gradeName);
	}

	@Override
	public List<GradeGood> findGradeGoods(int grade, int gradeId) {
		// TODO Auto-generated method stub
		List<GradeGood> list = null;
		try {
			if(grade == 1){
				// 查询所有的一级分类及其相关的商品数据，使用左连接
				list = gradeDao.findByGoodGradeId(grade);
			}else{
				// 查询指定的二级分类及其相关的商品数据， 使用内连接
				list = gradeDao.findGradeGoodsById(gradeId);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Grade findGradeIdServce(int id) {
		// TODO Auto-generated method stub
		return gradeDao.findGradeId(id);
	}

	@Override
	public void updateService(Grade grade, int id) {
		// TODO Auto-generated method stub
		gradeDao.update(grade, id);
	}

	

	

	

}
