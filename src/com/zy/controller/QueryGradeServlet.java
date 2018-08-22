package com.zy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qianfeng.utils.JsonUtils;

import com.zy.service.IGradeService;
import com.zy.service.impl.GradeService;
import com.zy.vo.GradeGood;

/**
 * Servlet implementation class QueryGradeServlet
 */
@WebServlet("/QueryGradeServlet")
public class QueryGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IGradeService gradeService = new GradeService(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryGradeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取分类的级别
//		List<Grade> list = grad
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("grade");
		String gradeId = request.getParameter("gradeId");
		if(gradeId == null){
			gradeId = "0";
		}
		
		try {
			List<GradeGood> list = gradeService.findGradeGoods(Integer.parseInt(id), Integer.parseInt(gradeId));
			JsonUtils.writeJsonUtils(1, list, response);
	
		
		} catch (NumberFormatException e) {
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
