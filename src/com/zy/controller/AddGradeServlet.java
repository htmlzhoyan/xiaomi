package com.zy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.mchange.v2.beans.BeansUtils;
import com.qianfeng.utils.JsonUtils;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zy.entity.Grade;
import com.zy.service.IGradeService;
import com.zy.service.impl.GradeService;

/**
 * Servlet implementation class AddGradeServlet
 */
@WebServlet("/addGrade")
public class AddGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IGradeService igr = new GradeService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGradeServlet() {
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
			List<Grade> list= null;
			try {
				list = igr.findAllGrade();
				System.out.println(list);
				response.setContentType("text/html;charset=utf-8");
				JsonUtils.writeJsonUtils(1, list, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "add":
			Grade gradeObject = new Grade();
			
			String gradeName = request.getParameter("typename");
			String grade1 = request.getParameter("grade");
			String parentId = request.getParameter("parentId");
			gradeObject.setGradeName(gradeName);
			gradeObject.setGrade(Integer.parseInt(grade1));
			gradeObject.setParentId(Integer.parseInt(parentId));	
			igr.addService(gradeObject);
			JsonUtils.writeJsonUtils(1, null, response);
			break;
		case "searchType":
			String grade = request.getParameter("grade");
			List<Grade> list1= null;
			list1 = igr.findGradeTypeService(Integer.valueOf(grade));
			
			response.setContentType("text/html;charset=utf-8");
			JsonUtils.writeJsonUtils(1, list1, response);
			break;
		case "delete":
			String id = request.getParameter("id");
			
			igr.deleteService(Integer.parseInt(id));

			JsonUtils.writeJsonUtils(1, null, response);
			break;
		case "search":
			String grade2 = request.getParameter("grade");
			String gradeName2 = request.getParameter("gradeName");	
			List<Grade> list2= null;
			if(grade2.equals("")) {
				grade2 = "0";
			}
			if(gradeName2.equals("")) {
				gradeName2 = null;
			}
			
			list2 = igr.searchService(Integer.valueOf(grade2), gradeName2);
			
			response.setContentType("text/html;charset=utf-8");
			JsonUtils.writeJsonUtils(1, list2, response);
			break;
		case "searchId":
			String id3 = request.getParameter("id");
			Grade grade3 =null;
			grade3 = igr.findGradeIdServce(Integer.valueOf(id3));
			JsonUtils.writeJsonUtils(1, grade3, response);
			break;
		case "update":
			String id4 = request.getParameter("id");
			Grade gradeObject1 = new Grade();
			
			String gradeName1 = request.getParameter("typename");
			String grade11 = request.getParameter("grade");
			String parentId1 = request.getParameter("parentId");
			gradeObject1.setGradeName(gradeName1);
			gradeObject1.setGrade(Integer.parseInt(grade11));
			gradeObject1.setParentId(Integer.parseInt(parentId1));	
			igr.updateService(gradeObject1, Integer.valueOf(id4));
			JsonUtils.writeJsonUtils(1, null, response);
			break;
		default:
			break;
		}
		
		//BeanUtils.populate(grade, properties);
		
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
