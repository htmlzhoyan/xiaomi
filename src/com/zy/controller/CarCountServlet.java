package com.zy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.utils.JsonUtils;
import com.zy.entity.User;
import com.zy.service.ICarService;
import com.zy.service.impl.CarService;

/**
 * Servlet implementation class CarCountServlet
 */
@WebServlet("/CarCount")
public class CarCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ICarService icar = new CarService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarCountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int count;
		User user = (User)request.getSession().getAttribute("user");
		if(user==null) {
			JsonUtils.writeJsonUtils(0, null, response);
			return;
		}
		try {
			count = icar.countCar();
			JsonUtils.writeJsonUtils(1, count, response);
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
