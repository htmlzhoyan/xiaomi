package com.zy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.utils.JsonUtils;
import com.sun.xml.internal.bind.v2.model.core.ID;
import com.zy.service.IOrderService;
import com.zy.service.impl.OrderService;

/**
 * Servlet implementation class UploadOrderServlet
 */
@WebServlet("/UploadOrder")
public class UploadOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IOrderService ors = new OrderService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Integer id = Integer.valueOf(request.getParameter("id"));
		Integer status = Integer.valueOf(request.getParameter("state"));
	
		ors.updateByid(id,status);
		JsonUtils.writeJsonUtils(1, null, response);
		
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
