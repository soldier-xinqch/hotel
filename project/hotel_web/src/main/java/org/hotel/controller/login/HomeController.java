package org.hotel.controller.login;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.hotel.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Controller
@RequestMapping("/home")
public class HomeController {
//	private Logger logger = LogManager.getLogger(HomeController.class);
	private final String urlStr = "home";
	@Autowired
	private CacheManager cacheManager;
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String homeIndex(HttpServletRequest request){
		request.setAttribute("menuKey", urlStr+"index");
//		logger.info("【用户{}进入主页】",user.getUsername());
		return "server/index/home_server";
	}
	
	@RequestMapping(value="title",method=RequestMethod.GET)
	public String title(HttpServletRequest request){
		Cache cache = cacheManager.getCache("userCache");
		Element element =  cache.get("LoginUserKey");
		if(null  == element){
			Subject currentUser = SecurityUtils.getSubject();       
			currentUser.logout();
			return null;
		} 
		User user = (User) element.getValue();
		request.setAttribute("menuKey", urlStr+"index");
		request.setAttribute("user", user);
//		logger.info("【用户{}进入主页】",user.getUsername());
		return "server/index/head_title";
	}
}
