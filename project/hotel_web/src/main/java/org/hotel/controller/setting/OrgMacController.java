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
import org.hotel.model.OrgMac;
import org.hotel.model.User;
import org.hotel.service.IOrgMacService;
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
 *  组织设备
 * @author xinqch
 *
 */
@Controller
@RequestMapping("orgMac")
public class OrgMacController extends BaseController<Org>{

	private final String urlStr ="orgMac";
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IOrgMacService orgMacService;
	@Autowired
	private CacheManager cacheManager;
	
	private List<Org> orgs;
	
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
		this.orgs =orgs; 
		this.user =user; 
		request.setAttribute("orgs", orgs);
		request.setAttribute("menuKey", urlStr+"index");
		return "server/org/org_mac_setting";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String pq_curpage = request.getParameter("pq_curpage");
		String pq_rpp = request.getParameter("pq_rpp");
		PageHelper.startPage(Integer.valueOf(pq_curpage), Integer.valueOf(pq_rpp));
		List<OrgMac> orgMacs = orgMacService.findOrgMacByOrgs(orgs);
		PageInfo<OrgMac> baseInfos = new PageInfo<OrgMac>(orgMacs);
		map.put("list", baseInfos.getList());
		map.put("pageSize", baseInfos.getPageSize());
		map.put("pageNo", baseInfos.getPageNum());
		map.put("total", baseInfos.getTotal());
		return map;
	}
	
	@RequestMapping(value="addOrgMac",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrg(HttpServletRequest request,OrgMac orgMac){
		Map<String,Object> map = Maps.newHashMap();
		orgMac.setCreateUser(user.getId());
		dealOrgMacMsg(orgMac);
		int isSuccess = orgMacService.insert(orgMac);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		
		return map;
	}
	
	@RequestMapping(value="modifyOrgMac",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyOrgMac(HttpServletRequest request,OrgMac orgMac){
		Map<String,Object> map = Maps.newHashMap();
		orgMac.setModifyUser(user.getId());
		dealOrgMacMsg(orgMac);
		int isSuccess = orgMacService.modify(orgMac);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
	@RequestMapping(value="deleteOrgMac",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> deleteOrg(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		int isSuccess = orgMacService.logicDelete(id);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
	private OrgMac dealOrgMacMsg(OrgMac orgMac){
		String orgId = orgMac.getOrgId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = orgId.split("-");
			orgMac.setOrgId(idAndName[0]);
			orgMac.setOrgName(idAndName[1]);
		}
		if(StringUtils.isEmpty(orgMac.getOrgId())){
			orgMac.setOrgId(user.getOrgId());
			orgMac.setOrgName(user.getOrgName());
		}
		return orgMac;
	}
	
	@RequestMapping(value="validate",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> validate(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		try {
			String mac = request.getParameter("mac");
			String macName = request.getParameter("macName");
			String status = request.getParameter("status");
			if(status.equals(AUTHFLAG.ADD.getValue())&&(StringUtils.isEmpty(mac)||StringUtils.isEmpty(macName))){
				map.put("message","缺少必要参数");
				map.put("type",RESULTFLAG.ERROR.getValue());
				return map;
			}else if(status.equals(AUTHFLAG.UPDATE.getValue())){
				OrgMac isHasMac = null;
				if(!StringUtils.isEmpty(mac)){
					isHasMac = orgMacService.findOrgMacByMac(mac);
					if(null !=isHasMac){
						map.put("message","设备mac地址不能重复");
						map.put("type",RESULTFLAG.ERROR.getValue());
						return map;
					}
				}else if(!StringUtils.isEmpty(macName)){
					isHasMac = orgMacService.findOrgMacByMacName(macName);
					if(null !=isHasMac){
						map.put("message","设备名称不能重复");
						map.put("type",RESULTFLAG.ERROR.getValue());
						return map;
					}
				}else{
					map.put("message","缺少必要参数");
					map.put("type",RESULTFLAG.ERROR.getValue());
					return map;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		map.put("type",RESULTFLAG.SUCCESS.getValue());
		return map;
	}
}
