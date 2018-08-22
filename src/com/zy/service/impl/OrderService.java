package com.zy.service.impl;


import java.util.Date;
import java.util.List;



import com.qianfeng.utils.StringUtils;
import com.zy.dao.IGoodsDao;
import com.zy.dao.IOrderDao;
import com.zy.dao.IOrderDetailDao;
import com.zy.dao.impl.GoodsDao;
import com.zy.dao.impl.OrderDao;
import com.zy.dao.impl.OrderDeatilDao;
import com.zy.entity.Goods;
import com.zy.entity.Order;
import com.zy.entity.OrderDetail;
import com.zy.service.IOrderService;
import com.zy.vo.GoodsInfo;

public class OrderService implements IOrderService {
	private IOrderDao orderDao = new OrderDao();
	private IOrderDetailDao detailDao = new OrderDeatilDao();
	private IGoodsDao igood = new GoodsDao();
	
	@Override
	public void addOrder(int uid,List<GoodsInfo> infos,String imgPath) {
		// TODO Auto-generated method stub
		
		// 先向订单表中插入数据
		String orderCode = StringUtils.createOrderCodeId();
		// 计算总金额
		Double totalMoney = 0.0;
		for (GoodsInfo gi : infos) {
			Goods goods = igood.findById(gi.getGoodsId());
			totalMoney += gi.getNum() * goods.getPrice();
		}
		Order order = new Order();
		order.setOrderCode(orderCode);
		order.setMoney(totalMoney);
		order.setCreateDate(new Date());
		order.setUid(uid);
		order.setStatus(1);// 未支付
		order.setImgPath(imgPath);
		
		// 添加订单数据，获取自增的id
		int orderId = (int)orderDao.add(order);
		
		// 再向明细中插入所有数据
		for (GoodsInfo gi : infos) {
			OrderDetail od = new OrderDetail();
			od.setOrderId(orderId);
			od.setNum(gi.getNum());
			od.setGoodsId(gi.getGoodsId());
			
			detailDao.add(od);
		}
	}

	@Override
	public List<Order> findAllOrdersByUid(int uid) {
		// TODO Auto-generated method stub
		return detailDao.findAllByUid(uid);
	}

	@Override
	public void updateByid(Integer id, Integer status) {
		orderDao.update(id, status);
	}


}
