package org.hotel.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

/**
 *  餐次管理
 * @author xinqch
 *
 */
@Controller
@RequestMapping("eatingOrder")
public class EatingOrderController {

	private final String urlStr ="eatingOrder";
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String orgIndex(HttpServletRequest request){
		request.setAttribute("menuKey", urlStr+"index");
		return "restaurant/eating_order";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		return map;
	}
}
