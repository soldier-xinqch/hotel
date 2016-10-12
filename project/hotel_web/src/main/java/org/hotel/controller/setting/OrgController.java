package org.hotel.controller.setting;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hotel.common.BaseController;
import org.hotel.common.CommEnum.AUTHFLAG;
import org.hotel.common.CommEnum.RESULTFLAG;
import org.hotel.model.Org;
import org.hotel.model.User;
import org.hotel.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 组织
 * @author xinqch
 *
 */
@Controller
@RequestMapping("org")
public class OrgController extends BaseController<Org>{

	private final String urlStr ="org";
	@Autowired
	private IOrgService orgService;
	@Autowired
	private CacheManager cacheManager;
	
	private User user;

	@RequestMapping(value="index",method=RequestMethod.GET)
	public String orgIndex(HttpServletRequest request){
//		List<Org> orgs = orgService.findAll();
		Cache cache = cacheManager.getCache("userCache");
		Element element =  cache.get("LoginUserKey");
		if(null  == element){
			Subject currentUser = SecurityUtils.getSubject();       
			currentUser.logout();
			return null;
		} 
		User user = (User) element.getValue();
		List<Org> orgs = orgService.findOrgListById(user.getOrgId());
		this.user = user;
		request.setAttribute("orgs", orgs);
		request.setAttribute("menuKey", urlStr+"index");
		return "server/org/org_setting";
	}
	
	@RequestMapping(value="orgTree",method=RequestMethod.POST)
	public @ResponseBody List<Org> orgTree(HttpServletRequest request){
//		List<Org> orgs = orgService.findAll();
		Cache cache = cacheManager.getCache("userCache");
		Element element =  cache.get("LoginUserKey");
		if(null  == element){
			Subject currentUser = SecurityUtils.getSubject();       
			currentUser.logout();
			return null;
		} 
		User user = (User) element.getValue();
		List<Org> orgs = orgService.findOrgListById(user.getOrgId());
		return orgs;
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
		dealOrgMsg(org);
		int isSuccess = orgService.insert(org);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		
		return map;
	}
	
	@RequestMapping(value="modifyOrg",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyOrg(HttpServletRequest request,Org org){
		Map<String,Object> map = Maps.newHashMap();
		org.setModifyUser(user.getId());
		dealOrgMsg(org);
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
	
	@RequestMapping(value="validate",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> validate(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		try {
			String orgName = request.getParameter("orgName");
			String status = request.getParameter("status");
			if(status.equals(AUTHFLAG.ADD.getValue())&&(StringUtils.isEmpty(orgName))){
				map.put("message","缺少必要参数");
				map.put("type",RESULTFLAG.ERROR.getValue());
				return map;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("type",RESULTFLAG.SUCCESS.getValue());
		return map;
	}
	
	private User dealOrgMsg(Org org){
		String orgId = org.getOrgId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = orgId.split("-");
			org.setParentId(idAndName[0]);
			org.setParentName(idAndName[1]);
		}
		if(StringUtils.isEmpty(org.getOrgId())){
			org.setParentId(user.getOrgId());
			org.setParentName(user.getOrgName());
		}
		return user;
	}
}
