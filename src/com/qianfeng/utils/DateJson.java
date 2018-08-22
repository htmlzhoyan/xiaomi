package com.qianfeng.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;


public class DateJson implements JsonValueProcessor{
	private SimpleDateFormat sdf = null;
	public DateJson(String str) {
		sdf = new SimpleDateFormat(str);
	}
	
	@Override
	public Object processArrayValue(Object value, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return sdf.format((Date)value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig arg2) {
		return sdf.format((Date)value);
		// TODO Auto-generated method stub
		
	}
	
}
