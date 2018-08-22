package com.zy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.qianfeng.utils.JsonUtils;
import com.zy.service.ICarService;
import com.zy.service.impl.CarService;
import com.zy.vo.CarandGood;

/**
 * Servlet implementation class QueryCartServlet
 */
@WebServlet("/QueryCart")
public class QueryCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ICarService ics = new CarService();     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		List<CarandGood> list = null;
		list = ics.findAllCar();
		JsonUtils.writeJsonUtils(1, list, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
