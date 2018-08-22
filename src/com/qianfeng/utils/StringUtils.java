package com.qianfeng.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class StringUtils {
	/**
	 * 判断字符串为空
	 * @param info
	 * @return
	 */
	public static boolean isEmpty(String info) {
		if(info==null || info.trim().equals("")) {
			return true;
		}else {
			return false;
		}
		
		
	}

	public static String createOrderCodeId() {
		// TODO Auto-generated method stub
		String uuid = UUID.randomUUID().toString().replace("-", "");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(new Date());
		String orderCode = uuid + time;
		return orderCode;
	}
}