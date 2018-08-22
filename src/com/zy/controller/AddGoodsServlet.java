package com.zy.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mchange.v2.beans.BeansUtils;
import com.qianfeng.utils.JsonUtils;
import com.zy.entity.Goods;
import com.zy.service.IGoodsService;
import com.zy.service.impl.GoodsService;



/**
 * Servlet implementation class AddGoodsServlet
 */
@WebServlet("/addGoods")
public class AddGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IGoodsService gds = new GoodsService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGoodsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Goods rest = new Goods();
		this.addInput(request,response,rest);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void addInput(HttpServletRequest request, HttpServletResponse response,Goods rest) {
		
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(fileFactory);
		fileUpload.setFileSizeMax(1000 * 1024);
		fileUpload.setHeaderEncoding("utf-8");
		if(ServletFileUpload.isMultipartContent(request)){
			
			try {
				// 将请求中的数据转为列表形式，列表中存储FileItem对象
				@SuppressWarnings("unchecked")
				List<FileItem> items = fileUpload.parseRequest(request);
				for (FileItem item : items) {
					// 判断是否是普通的表单数据
					if(item.isFormField()){
						// 获取提交的数据的name
						String name = item.getFieldName();
						// 获取对应的value
						//String value = item.getString();
						// 普通表单数据的中文乱码解决
						String value = item.getString("utf-8");
						System.out.println(name + ":" + value);
						// 针对beanutils，进行日期转转
						ConvertUtils.register(new Converter() {
				            public Object convert(Class type, Object value) {
				                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				                try {
				                    return simpleDateFormat.parse(value.toString());
				                } catch (ParseException e) {
				                    // TODO Auto-generated catch block
				                    e.printStackTrace();
				                }
				                return null;
				            }
				        }, Date.class);
						
						
						
						
						BeanUtils.setProperty(rest, name, value);
						
					}else{
						// 获取上传的文件的文件名
						String fileName = item.getName();
						
						// 获取uuid
						String uuid = UUID.randomUUID().toString().replace("-", "");
						// 生成新的文件名
						//aa_9ea1beb9ef2245ca8d6c3640967194cd.txt
						String newFileName = fileName.substring(0, fileName.lastIndexOf(".")) + 
								"_" + uuid + fileName.substring(fileName.lastIndexOf("."));
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
						String dateS = sdf.format(new Date());
						// 将上传的文件保存到指定的路径
						// 可以通过在tomcat下配置虚拟路径，借助url进行具体资源文件的访问（理解）
						String path = "D:/upload/"+dateS;
						File pathFile = new File(path);
						// 如果文件夹不存在，创建
						if(!pathFile.exists()){
							pathFile.mkdirs();
						}
						// 获取web应用下相关资源的真实路径
						//String path = this.getServletContext().getRealPath("/upload");
						File file = new File(path, newFileName);
						rest.setImgPath(dateS + "/" + newFileName);
						// 保存文件
						item.write(file);
						// 删除临时文件
						item.delete();
						
					}
				}
				gds.addGoods(rest);
				JsonUtils.writeJsonUtils(1, null, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JsonUtils.writeJsonUtils(0, e.getMessage(), response);
			}
		}
		
	}

}
