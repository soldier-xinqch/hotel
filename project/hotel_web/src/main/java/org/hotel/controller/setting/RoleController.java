package org.hotel.controller.setting;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hotel.common.CommEnum.AUTHFLAG;
import org.hotel.common.CommEnum.RESULTFLAG;
import org.hotel.model.Menu;
import org.hotel.model.Org;
import org.hotel.model.Role;
import org.hotel.model.RoleAuth;
import org.hotel.model.User;
import org.hotel.service.IMenuService;
import org.hotel.service.IOrgService;
import org.hotel.service.IRoleAuthService;
import org.hotel.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
/**
 *  角色
 * @author xinqch
 *
 */
@Controller
@RequestMapping("role")
public class RoleController {

	private final String urlStr ="role";
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IRoleAuthService roleAuthService;
	@Autowired
	private CacheManager cacheManager;
	
	private List<Org> orgs;
	
	private User user;


	@RequestMapping(value="index",method=RequestMethod.GET)
	public String roleIndex(HttpServletRequest request){
		Cache cache = cacheManager.getCache("userCache");
		Element element =  cache.get("LoginUserKey");
		User user = (User) element.getValue();
		List<Org> orgs = orgService.findOrgListById(user.getOrgId());
		this.orgs =orgs; 
		this.user =user; 
		request.setAttribute("orgs", orgs);
		
		List<Menu> menus = menuService.findMenuByOrgs(orgs);
		request.setAttribute("menuList", menus);
		request.setAttribute("menuKey", urlStr+"index");
		return "server/role/role_setting";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String pq_curpage = request.getParameter("pq_curpage");
		String pq_rpp = request.getParameter("pq_rpp");
		PageHelper.startPage(Integer.valueOf(pq_curpage), Integer.valueOf(pq_rpp));
		List<Role> roles = roleService.findRoleByOrgId(orgs);
		PageInfo<Role> baseInfos = new PageInfo<Role>(roles);
		map.put("list", baseInfos.getList());
		map.put("pageSize", baseInfos.getPageSize());
		map.put("pageNo", baseInfos.getPageNum());
		map.put("total", baseInfos.getTotal());
		return map;
	}
	
	@RequestMapping(value="addRole",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addRole(HttpServletRequest request,Role role){
		Map<String,Object> map = Maps.newHashMap();
		dealRoleMsg(role);
		role.setCreateUser(user.getId());
		int isSuccess = roleService.insert(role);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		
		return map;
	}
	
	@RequestMapping(value="modifyRole",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyRole(HttpServletRequest request,Role role){
		Map<String,Object> map = Maps.newHashMap();
		dealRoleMsg(role);
		role.setModifyUser(user.getId());
		int isSuccess = roleService.modify(role);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
	@RequestMapping(value="deleteRole",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> deleteRole(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		int isSuccess = roleService.physicsDelete(id);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
	@RequestMapping(value="freezeAndActive",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> freezeAndActive(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		int isSuccess = roleService.logicDelete(id);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
	@RequestMapping(value="searchRoleAuth",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> searchRoleAuth(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		RoleAuth roleAuths = roleAuthService.findRoleAuthByRoleId(id);
		map.put("authList", roleAuths);
		return map;
	}
	
	
	@RequestMapping(value="modifyAuthRole",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyAuthRole(HttpServletRequest request){
		Map<String, Object> map= Maps.newHashMap();
		try {
			String roleId = request.getParameter("roleId");
			String authId = request.getParameter("authId");
			String[] authElement = request.getParameterValues("authElement");
			RoleAuth auth = new RoleAuth();
			auth.setRoleId(authId);
			List<String> authElements = Lists.newArrayList();
			for (String string : authElement) {
				authElements.add(string.trim());
			}
			Set<Menu> menus =menuService.findParents(authElements);
			StringBuffer element = new StringBuffer();
			for (Menu menu : menus) {
				element.append(menu.getId()+",");
			}
			RoleAuth roleAuths = roleAuthService.findRoleAuthByRoleId(roleId);
			if(null !=roleAuths)auth.setId(roleAuths.getId());
			auth.setRoleId(roleId);
			auth.setElementId(element.toString());
			auth.setOperateFlag(AUTHFLAG.SEARCH.getValue());
			int isSuccess = 0;
			if(StringUtils.isEmpty(auth.getId())){
				isSuccess = roleAuthService.insert(auth);
			}else {
				isSuccess = roleAuthService.modify(auth);
			}
			map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		} catch (Exception e) {
			map.put("type", RESULTFLAG.ERROR.getValue());
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	private Role dealRoleMsg(Role role){
		String orgId = role.getOrgId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = orgId.split("-");
			role.setOrgId(idAndName[0]);
			role.setOrgName(idAndName[1]);
		}
		return role;
	}
}
