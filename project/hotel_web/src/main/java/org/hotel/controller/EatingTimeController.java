package org.hotel.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hotel.common.BaseController;
import org.hotel.common.CommEnum.RESULTFLAG;
import org.hotel.common.PageEntity;
import org.hotel.model.EatTime;
import org.hotel.service.IEatTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

/**
 * 开餐时间
 * @author xinqch
 *
 */
@Controller
@RequestMapping("eatingTime")
public class EatingTimeController extends BaseController<EatTime>{

	private final String urlStr ="eatingTime";
	@Autowired
	private IEatTimeService eatTimeService;
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String orgIndex(HttpServletRequest request){
		request.setAttribute("menuKey", urlStr+"index");
		return "restaurant/eating_time";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		List<EatTime> orgs = eatTimeService.findAll();
//		PageEntity<EatTime> baseInfos = BaseController.pageMethod(orgs, 0, 10, null);
//		map.put("list", baseInfos.getPageDatas());
//		map.put("pageSize", baseInfos.getPageSize());
//		map.put("pageNo", baseInfos.getPageNo());
//		map.put("total", baseInfos.getTotalRecord());
		return map;
	}
	
	@RequestMapping(value="addEatTime",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrg(HttpServletRequest request,EatTime eatTime){
		Map<String,Object> map = Maps.newHashMap();
		dealStaffMsg(eatTime);
		int isSuccess = eatTimeService.insert(eatTime);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	@RequestMapping(value="modifyEatTime",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyOrg(HttpServletRequest request,EatTime eatTime){
		Map<String,Object> map = Maps.newHashMap();
		dealStaffMsg(eatTime);
		int isSuccess = eatTimeService.modify(eatTime);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	private EatTime dealStaffMsg(EatTime eatTime){
		String orgId = eatTime.getOrgId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = orgId.split("-");
			eatTime.setOrgId(idAndName[0]);
			eatTime.setOrgName(idAndName[1]);
		}
		return eatTime;
	}
	
}
