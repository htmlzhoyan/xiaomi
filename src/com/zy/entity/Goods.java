package com.zy.entity;

import java.util.Date;

public class Goods {
	private Integer id;
	private String goodsName;
	private Integer score;
	private Double price;
	private Date createDate;
	private String comment;
	private String imgPath;
	private Integer gradeId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", goodsName=" + goodsName + ", score=" + score + ", price=" + price
				+ ", createDate=" + createDate + ", comment=" + comment + ", imgPath=" + imgPath + ", gradeId="
				+ gradeId + "]";
	}
	
}
