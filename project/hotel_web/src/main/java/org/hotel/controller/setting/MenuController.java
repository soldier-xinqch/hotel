package org.hotel.controller.setting;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hotel.common.BaseController;
import org.hotel.common.CommEnum.RESULTFLAG;
import org.hotel.model.Menu;
import org.hotel.model.Org;
import org.hotel.model.RoleAuth;
import org.hotel.model.User;
import org.hotel.service.IMenuService;
import org.hotel.service.IOrgService;
import org.hotel.service.IRoleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
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
 *  菜单
 * @author xinqch
 *
 */
@Controller
@RequestMapping("menu")
public class MenuController extends BaseController<Menu>{

	private final String urlStr ="menu";
	@Autowired
	private IMenuService menuService;
	@Autowired
	private CacheManager cacheManager;
	@Autowired
	private IRoleAuthService roleAuthService;
	@Autowired
	private IOrgService orgService;
	
	private List<Org> orgs;
	
	private User user;
	
	
	public List<Menu> getUserMenus(){
		List<String> menuIds= Lists.newArrayList();
		try {
			Cache cache = cacheManager.getCache("userCache");
			Element element =  cache.get("LoginUserKey");
			if(null  == element){
				Subject currentUser = SecurityUtils.getSubject();       
				currentUser.logout();
				return null;
			} 
			User user = (User) element.getValue();
			this.user = user;
			String roles = user.getRoleDesc();
			String[] roleIds = roles.split(",");
			for (String string : roleIds) {
				if(!StringUtils.isEmpty(string)&&""!=string){
					RoleAuth roleAuth = roleAuthService.findRoleAuthByRoleId(string);
					if(null!=roleAuth&&null!= roleAuth.getElementId()) {
						String elements = roleAuth.getElementId();
						String[] menus = elements.split(",");
						for (String menuId : menus) {
							menuIds.add(menuId);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return menuService.findMenuByIds(menuIds);
	}
	
	@RequestMapping(value="/index/{activeFlag}",method=RequestMethod.GET)
	public String getMenus(HttpServletRequest request,@PathVariable String activeFlag){
		List<Menu> menus = getUserMenus();
//				List<Menu> menus =menuService.findMenus(true);
		Menu activeMenu = menuService.findMenusByFlag(activeFlag);
		request.setAttribute("menus", menus);
		if(null != activeMenu)request.setAttribute("activeMenuFlag", activeMenu.getId());
		request.setAttribute("activeFlag", activeFlag);
		return "server/menu/left_menu";
	}
	
	@RequestMapping(value="/indexPage",method=RequestMethod.GET)
	public String menuIndex(HttpServletRequest request){
		Cache cache = cacheManager.getCache("userCache");
		Element element =  cache.get("LoginUserKey");
		User user = (User) element.getValue();
		List<Org> orgs = orgService.findOrgListById(user.getOrgId());
		this.orgs =orgs; 
		List<Menu> menus = menuService.findMenuByOrgs(orgs);
		request.setAttribute("menuList", menus);
		request.setAttribute("menuKey", urlStr+"indexPage");
		return "server/menu/menu_setting";
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
//		String pq_datatype = request.getParameter("pq_datatype");
		String pq_curpage = request.getParameter("pq_curpage");
		String pq_rpp = request.getParameter("pq_rpp");
		PageHelper.startPage(Integer.valueOf(pq_curpage), Integer.valueOf(pq_rpp));
		List<Menu> menus = menuService.findMenuByOrgs(orgs);
		PageInfo<Menu> baseInfos = new PageInfo<Menu>(menus);
		map.put("list", baseInfos.getList());
		map.put("pageSize", baseInfos.getPageSize());
		map.put("pageNo", baseInfos.getPageNum());
		map.put("total", baseInfos.getTotal());
		return map;
	}
	
	@RequestMapping(value="addMenu",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addMenu(HttpServletRequest request,Menu menu){
		Map<String,Object> map = Maps.newHashMap();
		dealMenuMsg(menu);
		menu.setMenuOrgId(user.getOrgId());
		menu.setCreateUser(user.getId());
		int isSuccess = menuService.insert(menu );
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		
		return map;
	}
	
	@RequestMapping(value="modifyMenu",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyMenu(HttpServletRequest request,Menu menu){
		Map<String,Object> map = Maps.newHashMap();
		dealMenuMsg(menu);
		menu.setModifyUser(user.getId());
		int isSuccess = menuService.modify(menu );
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
	@RequestMapping(value="deleteMenu",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> deleteMenu(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		int isSuccess = menuService.physicsDelete(id);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
	private Menu dealMenuMsg(Menu menu){
		String parentId = menu.getMenuParentId();
		if(!StringUtils.isEmpty(parentId)){
			String[] idAndName = parentId.split("-");
			menu.setMenuParentId(idAndName[0]);
			menu.setMenuParentName(idAndName[1]);
		}
		String menuFlag = "";
		if(!StringUtils.isEmpty(menu.getMenuUrl())){
			String[] temps = menu.getMenuUrl().split("\\/");
			for (String string : temps) {
				menuFlag +=string;
			}
			menu.setMenuFlag(menuFlag);
		}
		return menu;
	}
	
}
