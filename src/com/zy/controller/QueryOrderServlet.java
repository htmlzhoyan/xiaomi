package com.zy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.qianfeng.utils.JsonUtils;
import com.zy.entity.Order;
import com.zy.entity.User;
import com.zy.service.IOrderService;
import com.zy.service.impl.OrderService;

/**
 * Servlet implementation class QueryOrderServlet
 */
@WebServlet("/queryOrder")
public class QueryOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IOrderService orderService = new OrderService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User)request.getSession().getAttribute("user");
		
		try {
			List<Order> list = orderService.findAllOrdersByUid(user.getId());
			JsonUtils.writeDateJosnInfo(1, list, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JsonUtils.writeJsonUtils(0, e.getMessage(), response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
