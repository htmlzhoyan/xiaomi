package com.zy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.utils.JsonUtils;
import com.zy.entity.Cart;
import com.zy.service.ICarService;
import com.zy.service.impl.CarService;


/**
 * Servlet implementation class AddCartServlet
 */
@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ICarService ics = new CarService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ids = request.getParameter("goodsId");
		String nums = request.getParameter("num");
		String gradeId = request.getParameter("gradeId");
		Cart cart = new Cart();
		cart.setGoodId(Integer.parseInt(ids));
		cart.setGoodNum(Double.valueOf(nums));
		cart.setUid(Integer.parseInt(gradeId));
		ics.addCar(cart);
		JsonUtils.writeJsonUtils(1, null, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
