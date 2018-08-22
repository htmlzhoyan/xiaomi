package com.zy.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qianfeng.utils.JsonUtils;
import com.zy.entity.User;
import com.zy.service.IUserService;
import com.zy.service.impl.UserService;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/userRegister")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserService ius = new UserService();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		User user = new User();
		String vcode = request.getParameter("vlidateCode");
		String code = (String)request.getSession(false).getAttribute("validateCode");
		if(!vcode.equalsIgnoreCase(code)) {
			JsonUtils.writeJsonUtils(0, "验证码错误", response);
			return;
		}
		try {
			BeanUtils.populate(user, request.getParameterMap());
			user.setCreateDate(new Date());
			user.setRole("2");
			try {
				ius.addService(user);
				JsonUtils.writeJsonUtils(1, null, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JsonUtils.writeJsonUtils(0, e.getMessage(), response);
			}
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
