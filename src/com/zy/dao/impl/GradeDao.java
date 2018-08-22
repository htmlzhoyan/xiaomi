package com.zy.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import com.qianfeng.utils.JdbcUtil;

import com.zy.dao.IGradeDao;

import com.zy.entity.Goods;
import com.zy.entity.Grade;
import com.zy.vo.GradeGood;
public class GradeDao implements IGradeDao {

	@Override
	public List<Grade> findAll(int grade) {
		// TODO Auto-generated method stub
		String sql = "select * from t_grade where grade=?";
		QueryRunner qRunner = JdbcUtil.getQueryRunner();
		List<Grade> list = null;
		
		return null;
	}

	@Override
	public List<Grade> findAllGrade() {
		// TODO Auto-generated method stub
		String sql = "select * from t_grade order by id desc";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		List<Grade> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<>(Grade.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	

	@Override
	public List<GradeGood> findByGoodGradeId(int grade) {
		// TODO Auto-generated method stub
	
		String sql = "select distinct gr1.id, gr1.gradename, go.goodsName,go.imgPath,go.price from t_grade gr1 " + 
				"left join t_grade gr2 " + 
				"on gr1.id=gr2.parentId " + 
				"left JOIN t_goods go " + 
				"on go.gradeId=gr2.id " +
					"where gr1.grade=?";
	
	QueryRunner qr = JdbcUtil.getQueryRunner();
	List<GradeGood> query = null;
	try {
		// ResultSetHandler 可以实现自定义的封装
		query = qr.query(sql, new ResultSetHandler<List<GradeGood>>() {
			@Override
			public List<GradeGood> handle(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				List<GradeGood> list = new ArrayList<>();
				while(rs.next()){
					int flag = 0;
					for (GradeGood gradeGoods : list) {
						int id = rs.getInt("id");
						// 如果分类已经存在，添加商品信息
						if(gradeGoods.getId() == id){
							flag = 1;
							List<Goods> goodsList = gradeGoods.getGoodsList();
							if(rs.getString("goodsName") != null){
								Goods goods = new Goods();
							
								goods.setGoodsName(rs.getString("goodsName"));
								goods.setImgPath(rs.getString("imgPath"));
								goods.setPrice(rs.getDouble("price"));
					
								goodsList.add(goods);
							}
							break;
						}
					}
					if(flag == 0){
						GradeGood gg = new GradeGood();
						gg.setId(rs.getInt("id"));
						gg.setGradeName(rs.getString("gradename"));
						List<Goods> glist = new ArrayList<>();
						if(rs.getString("goodsName") != null){
							Goods goods = new Goods();		
							goods.setGoodsName(rs.getString("goodsName"));
							goods.setImgPath(rs.getString("imgPath"));
							goods.setPrice(rs.getDouble("price"));
						
							glist.add(goods);
						}
						gg.setGoodsList(glist);
						
						list.add(gg);
					}
					
					
				}
				
				return list;
			}
			
		}, grade);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return query;
	}
	@Override
	public List<Grade> findGradeType(int grade) {
		// TODO Auto-generated method stu
		String sql ="select * from t_grade where grade = ?";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		List<Grade> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<>(Grade.class),grade);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void add(Grade res) {
		String sql = "insert into t_grade(gradeName,grade,parentId) values(?,?,?)";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		Object[] params = new Object[] {
			res.getGradeName(),
			res.getGrade(),
			res.getParentId()
		};
	
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(int id) {
		String sql = "delete from t_grade where id = ?";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		try {
			qr.update(sql,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	@Override
	public List<GradeGood> findGradeGoodsById(int gradeId) {
		// TODO Auto-generated method stub
		String sql = "select distinct gr1.id, gr1.gradename,go.id gid, go.goodsName,go.price,go.imgPath,go.comment from t_grade gr1 " + 
				"inner JOIN t_goods go " + 
				"on go.gradeId=gr1.id " +
				"where go.gradeId=?";
	
		QueryRunner qr = JdbcUtil.getQueryRunner();
		List<GradeGood> query = null;
		try {
			// ResultSetHandler 可以实现自定义的封装
			query = qr.query(sql, new ResultSetHandler<List<GradeGood>>() {
				@Override
				public List<GradeGood> handle(ResultSet rs) throws SQLException {
					// TODO Auto-generated method stub
					List<GradeGood> list = new ArrayList<>();
					while(rs.next()){
						int flag = 0;
						for (GradeGood gradeGoods : list) {
							int id = rs.getInt("id");
							// 如果分类已经存在，添加商品信息
							if(gradeGoods.getId() == id){
								flag = 1;
								List<Goods> goodsList = gradeGoods.getGoodsList();
								if(rs.getString("goodsName") != null){
									Goods goods = new Goods();
									goods.setId(rs.getInt("gid"));// 通过别名获取对应的值
									goods.setGoodsName(rs.getString("goodsName"));
									goods.setImgPath(rs.getString("imgPath"));
									goods.setPrice(rs.getDouble("price"));
									goods.setComment(rs.getString("comment"));
									goodsList.add(goods);
								}
								break;
							}
						}
						if(flag == 0){
							GradeGood gg = new GradeGood();
							gg.setId(rs.getInt("id"));
							gg.setGradeName(rs.getString("gradename"));
							List<Goods> glist = new ArrayList<>();
							if(rs.getString("goodsName") != null){
								Goods goods = new Goods();
								goods.setId(rs.getInt("gid"));
								goods.setGoodsName(rs.getString("goodsName"));
								goods.setImgPath(rs.getString("imgPath"));
								goods.setPrice(rs.getDouble("price"));
								goods.setComment(rs.getString("comment"));
								glist.add(goods);
							}
							gg.setGoodsList(glist);
							
							list.add(gg);
						}
						
						
					}
					
					return list;
				}
				
			}, gradeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return query;
	}	
	@Override
	public List<Grade> search(int grade, String gradeName) {
		String sql = "";
		List<Grade> list = null;
		QueryRunner qr = JdbcUtil.getQueryRunner();
		
		if(grade==0 && gradeName==null) {
			sql = "select * from t_grade";
			try {
				list = qr.query(sql, new BeanListHandler<>(Grade.class));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(grade==0) {
			sql = "select * from t_grade where gradeName = ?";	
			try {	
				list = qr.query(sql, new BeanListHandler<>(Grade.class), gradeName);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(gradeName==null) {
			sql = "select * from t_grade where grade = ?";	
			try {	
				list = qr.query(sql, new BeanListHandler<>(Grade.class), grade);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			sql = "select * from t_grade where grade = ? and gradeName = ?";	
			try {	
				list = qr.query(sql, new BeanListHandler<>(Grade.class), grade, gradeName);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
		
	}

	

	@Override
	public Grade findGradeId(int id) { 
		// TODO Auto-generated method stub
		String sql = "select * from t_grade where id=?";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		Grade gra = null;
		try {
			gra = qr.query(sql, new BeanHandler<>(Grade.class) ,id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gra;
	}

	@Override
	public void update(Grade grade, int id) {
		String sql = "update t_grade set gradeName=?,grade=?,parentId=? where id=?";
		QueryRunner qr = JdbcUtil.getQueryRunner();
		Object[] params = new Object[] {
			grade.getGradeName(),
			grade.getGrade(),
			grade.getParentId(),
			id
		};
	
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		


}
