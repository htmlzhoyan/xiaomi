package com.qianfeng.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;


import com.zy.vo.PageB;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

public class JsonUtils {
	public static void writeJsonUtils(int code,Object msg, HttpServletResponse response) {
		PageB bean = new PageB();
		bean.setCode(code);
		bean.setMsg(msg);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.write(JSONObject.fromObject(bean).toString());
		writer.flush();
		writer.close();
	}
	public static void writeDateJosnInfo(int code, Object msg, HttpServletResponse response){
		PageB bean = new PageB();
		bean.setCode(code);
		bean.setMsg(msg);
		
		// 注册转换器
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new DateJson("yyyy-MM-dd HH:mm:ss"));
		
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer.write(JSONObject.fromObject(bean, config).toString());
		writer.flush();
		writer.close();
	}
}
