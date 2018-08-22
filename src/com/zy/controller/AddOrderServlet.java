package com.zy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.IOP.IOR;


import com.qianfeng.utils.JsonUtils;
import com.zy.dao.IOrderDao;
import com.zy.dao.impl.OrderDao;
import com.zy.entity.User;
import com.zy.service.IOrderService;
import com.zy.service.impl.OrderService;
import com.zy.vo.GoodsInfo;

/**
 * Servlet implementation class AddOrderServlet
 */
@WebServlet("/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOrderService orderService = new OrderService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String[] ids = request.getParameterValues("goodsId");
		String[] nums = request.getParameterValues("num");
		String imgPath = request.getParameter("imgPath");
		List<GoodsInfo> list = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			GoodsInfo info = new GoodsInfo();
			info.setGoodsId(Integer.parseInt(ids[i]));
			info.setNum(Integer.parseInt(nums[i]));
			list.add(info);
		}
		User  u = (User) request.getSession().getAttribute("user");
		try {
			
			orderService.addOrder(u.getId(),list,imgPath);
			JsonUtils.writeJsonUtils(1, null, response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JsonUtils.writeJsonUtils(0, e.getMessage(), response);
			throw new RuntimeException("请登录页面");
		}
		
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
