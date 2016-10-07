package org.hotel.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.hotel.model.User;
import org.hotel.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class UserRealm extends AuthorizingRealm{
	
	@Autowired
	private IUserService userService;
	@Autowired
	private CacheManager cacheManager;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.addStringPermission("sys:manager");
		info.addStringPermission("user");
		System.out.println("开始授权");
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken=(UsernamePasswordToken) token; 
		String username=upToken.getUsername();
		String password=new String(upToken.getPassword());
		User user=userService.findUserByName(username);
		System.out.println("===========");
		Cache cache = cacheManager.getCache("userCache");
		Element element = new Element("LoginUserKey", user);
		cache.put(element);
		
//		EHCacheUtils cache = new EHCacheUtils();
//		try {
//			cache.addToCache("LoginUserKey", user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		if(user!=null){
			if(user.getPassword().equals(password)){
				return new SimpleAuthenticationInfo(username,password,getName());
			}
		}
		throw new UnauthenticatedException();
	}
	
	

}
