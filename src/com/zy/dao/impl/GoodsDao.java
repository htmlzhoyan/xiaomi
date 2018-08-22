package com.zy.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.qianfeng.utils.JdbcUtil;
import com.zy.dao.IGoodsDao;
import com.zy.entity.Goods;
import com.zy.entity.Grade;
import com.zy.vo.GoodAndGrade;

public class GoodsDao implements IGoodsDao {

	@Override
	public void add(Goods goods) {
		String sql = "insert into t_goods(goodsName,price,score,createDate,comment,imgPath,gradeId) values(?,?,?,?,?,?,?)";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		Object[] params = new Object[] {
			goods.getGoodsName(),
			goods.getPrice(),
			goods.getScore(),
			goods.getCreateDate(),
			goods.getComment(),
			goods.getImgPath(),
			goods.getGradeId(),
		};
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public Goods findById(int id) {
		String sql = "select * from t_goods where id=?";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		Goods goods = null;
		try {
			goods = qr.query(sql, new BeanHandler<>(Goods.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
		
		
	}
	@Override
	public Goods findByName(String name) {
		// TODO Auto-generated method stub
		String sql = "select * from t_goods where goodsName=?";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		Goods goods = null;
		try {
			goods = qr.query(sql, new BeanHandler<>(Goods.class),name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goods;
	}
	@Override
	public List<GoodAndGrade> findAll() {
	
		String sql = "select t.*,g.gradeName from t_goods t INNER JOIN t_grade g on t.gradeId = g.id order by id desc";
		QueryRunner qRunner = JdbcUtil.getQueryRunner();
		List<GoodAndGrade> list = null;
		try {
			list = qRunner.query(sql, new BeanListHandler<>(GoodAndGrade.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void delete(int id) {
		String sql = "delete from t_goods where id=?";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		try {
			qr.update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	@Override
	public List<GoodAndGrade> search(String name,Date createDate,Date createDates) {
		List<GoodAndGrade> list = null;
		QueryRunner qr = JdbcUtil.getQueryRunner();
		String sql = "";
		// TODO Auto-generated method stub
		if((name==null||name.equals(""))&&(createDate==null||createDate.equals(""))){
			sql = "select t.*,g.gradeName from t_goods t INNER JOIN t_grade g on t.gradeId = g.id";
			try {
				list = qr.query(sql, new BeanListHandler<>(GoodAndGrade.class));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(name==null||name.equals("")) {
			sql = "select t.*,g.gradeName from t_goods t INNER JOIN t_grade g on t.gradeId = g.id where t.createDate>=? and t.createDate<=?";
			try {
				list = qr.query(sql, new BeanListHandler<>(GoodAndGrade.class),createDate,createDates);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(createDate==null) {
			sql = "select t.*,g.gradeName from t_goods t INNER JOIN t_grade g on t.gradeId = g.id where t.goodsName=?";
			try {
				list = qr.query(sql, new BeanListHandler<>(GoodAndGrade.class), name);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return list;
	}


	@Override
	public void update(Goods goods, int id) {
		// TODO Auto-generated method stub

		String sql = "update t_goods set goodsName=?,price=?,score=?,createDate=?,comment=?,imgPath=? where id=?";
		QueryRunner qRunner = JdbcUtil.getQueryRunner();
		Object[] params = new Object[] {
				goods.getGoodsName(),
				goods.getPrice(),
				goods.getScore(),
				goods.getCreateDate(),
				goods.getComment(),
				goods.getImgPath(),
				id
		};
		try {
			qRunner.update(sql,params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	

}
