package org.hotel.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hotel.common.BaseController;
import org.hotel.common.CommEnum.RESULTFLAG;
import org.hotel.common.PageEntity;
import org.hotel.model.Org;
import org.hotel.model.User;
import org.hotel.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Controller
@RequestMapping("department")
public class DepartmentController extends BaseController<Org>{

	private final String urlStr ="department";
	@Autowired
	private IOrgService orgService;
	
	@Autowired
	private CacheManager cacheManager;
	
	private User user;

	@RequestMapping(value="index",method=RequestMethod.GET)
	public String orgIndex(HttpServletRequest request){
		Cache cache = cacheManager.getCache("userCache");
		Element element =  cache.get("LoginUserKey");
		User user = (User) element.getValue();
		List<Org> orgs = orgService.findOrgListById(user.getOrgId());
		this.user = user;
		request.setAttribute("orgs", orgs);
		request.setAttribute("menuKey", urlStr+"index");
		return "department/org_setting";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String pq_curpage = request.getParameter("pq_curpage");
		String pq_rpp = request.getParameter("pq_rpp");
		PageHelper.startPage(Integer.valueOf(pq_curpage), Integer.valueOf(pq_rpp));
		List<Org> orgs = orgService.findOrgListById(user.getOrgId());
		PageInfo<Org> baseInfos = new PageInfo<Org>(orgs);
		map.put("list", baseInfos.getList());
		map.put("pageSize", baseInfos.getPageSize());
		map.put("pageNo", baseInfos.getPageNum());
		map.put("total", baseInfos.getTotal());
		return map;
	}
	
	@RequestMapping(value="addOrg",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrg(HttpServletRequest request,Org org){
		Map<String,Object> map = Maps.newHashMap();
		org.setCreateUser(user.getId());
		int isSuccess = orgService.insert(org);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		
		return map;
	}
	
	@RequestMapping(value="modifyOrg",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyOrg(HttpServletRequest request,Org org){
		Map<String,Object> map = Maps.newHashMap();
		org.setModifyUser(user.getId());
		int isSuccess = orgService.modify(org);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
	@RequestMapping(value="deleteOrg",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> deleteOrg(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		int isSuccess = orgService.logicDelete(id);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
}
