package com.zy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qianfeng.utils.JsonUtils;
import com.zy.entity.User;
import com.zy.service.IUserService;
import com.zy.service.impl.UserService;
import com.zy.vo.PageB;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class AdminUserServlet
 */
@WebServlet("/adminuser")
public class AdminUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserService ius= new UserService();  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		String name = request.getParameter("username");
		String password = request.getParameter("password");
	
		try {
			User use  = ius.login(name, password, 1, null);		
			JsonUtils.writeJsonUtils(1, null, response);	
			
			HttpSession session = request.getSession(false);
			session.setMaxInactiveInterval(-1);
			session.setAttribute("user", use);
			
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
