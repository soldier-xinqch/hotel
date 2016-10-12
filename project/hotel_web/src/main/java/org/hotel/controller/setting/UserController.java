package org.hotel.controller.setting;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hotel.common.CommEnum.AUTHFLAG;
import org.hotel.common.CommEnum.RESULTFLAG;
import org.hotel.model.Org;
import org.hotel.model.Role;
import org.hotel.model.User;
import org.hotel.service.IOrgService;
import org.hotel.service.IRoleService;
import org.hotel.service.IUserService;
import org.hotel.utils.MD5Util;
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
 * 用户
 * @author xinqch
 *
 */
@Controller
@RequestMapping("user")
public class UserController {

	private final String urlStr ="user";
	@Autowired
	private IUserService userService;
	@Autowired
	private IOrgService orgService;
	@Autowired
	private IRoleService roleService;
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
		
//		List<Role> roles = roleService.findAll();
		List<Role> roles = roleService.findRoleByOrgId(orgs);
		request.setAttribute("orgs", orgs);
		request.setAttribute("roles", roles);
		request.setAttribute("menuKey", urlStr+"index");
		return "server/user/user_setting";
	}
	
	@RequestMapping(value="/roleData",method=RequestMethod.GET)
	public @ResponseBody Map<String,String> roles(){
		Map<String,Object> map = Maps.newHashMap();
		List<Role> roles = roleService.findAll();
		for (int i = 0; i < roles.size(); i++) {
			map.put(String.valueOf(i), roles.get(i));
		}
		return null;
	}
	
	@RequestMapping(value="/pageData",method=RequestMethod.GET)
	public @ResponseBody Map<String, Object> pageDatas(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String pq_curpage = request.getParameter("pq_curpage");
		String pq_rpp = request.getParameter("pq_rpp");
		PageHelper.startPage(Integer.valueOf(pq_curpage), Integer.valueOf(pq_rpp));
		List<User> users = userService.findUserByOrgs(orgs);
		PageInfo<User> baseInfos = new PageInfo<User>(users);
		map.put("list", baseInfos.getList());
		map.put("pageSize", baseInfos.getPageSize());
		map.put("pageNo", baseInfos.getPageNum());
		map.put("total", baseInfos.getTotal());
		return map;
	}
	
	@RequestMapping(value="addUser",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> addOrg(HttpServletRequest request,User user){
		Map<String,Object> map = Maps.newHashMap();
		dealUserMsg(user);
		user.setCreateUser(user.getId());
		int isSuccess = userService.insert(user);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		
		return map;
	}
	
	@RequestMapping(value="modifyUser",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyOrg(HttpServletRequest request,User user){
		Map<String,Object> map = Maps.newHashMap();
		dealUserMsg(user);
		user.setModifyUser(user.getId());
		int isSuccess = userService.modify(user);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	
	@RequestMapping(value="deleteUser",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> deleteOrg(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		int isSuccess = userService.logicDelete(id);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	@RequestMapping(value="modifyPasswd",method=RequestMethod.POST)
	public  @ResponseBody Map<String,Object> modifyPasswd(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		String id = request.getParameter("id");
		String newPasswd = request.getParameter("newPasswd");
		User user = new User();
		user.setId(id);
		user.setPassword(MD5Util.encodeMD5Hex(newPasswd));
		int isSuccess = userService.modify(user);
		map.put("type", isSuccess>0 ?RESULTFLAG.SUCCESS.getValue():RESULTFLAG.ERROR.getValue());
		return map;
	}
	@RequestMapping(value="validate",method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> validate(HttpServletRequest request){
		Map<String,Object> map = Maps.newHashMap();
		try {
			String username = request.getParameter("username");
			String status = request.getParameter("status");
			if(status.equals(AUTHFLAG.ADD.getValue())&&(StringUtils.isEmpty(username))){
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
	
	
	private User dealUserMsg(User users){
		String orgId = users.getOrgId();
		if(!StringUtils.isEmpty(orgId)){
			String[] idAndName = orgId.split("-");
			users.setOrgId(idAndName[0]);
			users.setOrgName(idAndName[1]);
		}
		if(StringUtils.isEmpty(users.getOrgId())){
			users.setOrgId(user.getOrgId());
			users.setOrgName(user.getOrgName());
		}
		return users;
	}
}
