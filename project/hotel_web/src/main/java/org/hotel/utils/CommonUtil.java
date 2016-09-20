package org.hotel.utils;

import java.util.UUID;

/**
 *  公共工具类
 * @author xinqch
 *
 */
public class CommonUtil {

	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		String id = CommonUtil.getUUID();
		System.out.println(id +"--#@@#---"+id.length());
		
	}
}
