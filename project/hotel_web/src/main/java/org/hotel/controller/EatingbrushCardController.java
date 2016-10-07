package org.hotel.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hotel.common.BaseController;
import org.hotel.common.PageEntity;
import org.hotel.model.Org;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

/**
 * 刷卡查询
 * @author xinqch
 *
 */
@Controller
@RequestMapping("eatingBrushCard")
public class EatingbrushCardController {

	private final String urlStr ="eatingBrushCard";
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String orgIndex(HttpServletRequest request){
		request.setAttribute("menuKey", urlStr+"index");
		return "restaurant/eating_brush_card";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
//		String pq_curpage = request.getParameter("pq_curpage");
//		String pq_rpp = request.getParameter("pq_rpp");
//		PageEntity<Org> baseInfos = BaseController.pageMethod(orgs,Integer.valueOf(pq_curpage),Integer.valueOf(pq_rpp), null);

		return map;
	}
}
