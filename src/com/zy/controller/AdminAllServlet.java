package com.zy.controller;

import java.io.IOException;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.utils.JsonUtils;

import com.zy.entity.User;
import com.zy.service.IUserManageService;

import com.zy.service.impl.UserManageService;

/**
 * Servlet implementation class AdminAllServlet
 */
@WebServlet("/findAll")
public class AdminAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IUserManageService ius = new UserManageService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		switch (method) {
		case "list":
			
			List<User> list= null;
			list = ius.byAllUserName();
		
			response.setContentType("text/html;charset=utf-8");
			JsonUtils.writeJsonUtils(1, list, response);	
			break;
		case "delete":
			System.out.println(request.getParameter("id"));
			Integer ID =  Integer.valueOf(request.getParameter("id")); 
			try {
				ius.deleteService(ID);
				JsonUtils.writeJsonUtils(1, null, response);	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		case "search":
			String username = request.getParameter("name");
			String sex = request.getParameter("radio");
			List<User> list2= null;
			
			if(username!=null&&username.equals("")) {
				username = null;
			}
			if(sex!=null&&sex.equals("")) {
				sex = null;
			}
			list2 = ius.searchService(username, sex);
			response.setContentType("text/html;charset=utf-8");
			JsonUtils.writeJsonUtils(1, list2, response);
		default:
			break;
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
