package org.hotel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 首页类
 * 初始化权限和菜单列表
 * @author xinqch
 *
 */
@Controller
@RequestMapping("/home")
public class IndexController {

	@RequestMapping(value="index",method=RequestMethod.POST)
	public String homeIndex(HttpServletRequest request){
		String userName = request.getParameter("username");
		String passwd = request.getParameter("passwd");
		String verufucatCode = request.getParameter("verufucatCode");
		System.out.println(userName+passwd+verufucatCode);
		return "server/index/home_server";
	}
}
