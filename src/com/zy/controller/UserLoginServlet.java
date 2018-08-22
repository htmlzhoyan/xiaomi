package com.zy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qianfeng.utils.JsonUtils;
import com.zy.entity.User;
import com.zy.service.IUserService;
import com.zy.service.impl.UserService;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/UserLogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserService userService = new UserService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String vcode = request.getParameter("vlidateCode");
		String code = (String)request.getSession(false).getAttribute("validateCode");
	
		if(!vcode.equalsIgnoreCase(code)) {
			JsonUtils.writeJsonUtils(0, "验证码错误", response);
		}else {
			try {
				User use  = userService.login(name, password, 2, null);
				HttpSession session = request.getSession(false);
				session.setMaxInactiveInterval(-1);
				session.setAttribute("user", use);
//				Cookie cookie = new Cookie("JSESSIONID", request.getSession(false).getId());
//				cookie.setMaxAge(30*60);
//				response.addCookie(cookie);
				
				JsonUtils.writeJsonUtils(1, null, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JsonUtils.writeJsonUtils(1, e.getMessage(), response);
			}
			
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
