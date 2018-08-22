package com.zy.entity;

public class OrderDetail {
	private Integer id;
	private Integer orderId;
	private Integer num;
	private Integer goodsId;
	
	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", orderId=" + orderId + ", num=" + num + ", goodsId=" + goodsId + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	
	
}
