package org.hotel.controller.login;

import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.hotel.common.CommEnum.RESULTFLAG;
import org.hotel.utils.MD5Util;
import org.hotel.utils.VaildCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.bingoohuang.patchca.service.Captcha;
import com.github.bingoohuang.patchca.service.CaptchaService;
import com.google.common.collect.Maps;

/**
 * 登录页
 * @author xinqch
 *
 */
@Controller
@RequestMapping("/login")
public class IndexController {
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String homeIndex(HttpServletRequest request){
		return "server/index/login_server";
	}
	
	/** 
     * 验证用户名和密码 
     * @param String username,String password
     * @return 
     */  
    @RequestMapping(value="/login",method=RequestMethod.POST)  
    public @ResponseBody Map<String,String> checkLogin(HttpServletRequest request,HttpServletResponse response,String username,String password) {  
    	Map<String,String> map = Maps.newHashMap();
        try{
        	String sessionValidCodeStr =  (String) request.getSession().getAttribute("validCodeStr");
        	 //获取用户请求表单中输入的验证码  
            String validCodeStr = WebUtils.getCleanParam(request, "verufucatCode"); 
        	if(StringUtils.isEmpty(validCodeStr)){
        		map.put("type", RESULTFLAG.ERROR.getValue());
        		map.put("message", "请输入验证码");
        		return map;
        	}else if(!validCodeStr.equals(sessionValidCodeStr)){
        		map.put("type", RESULTFLAG.ERROR.getValue());
        		map.put("message", "验证码输入错误");
        		return map;
        	}else if(StringUtils.isEmpty(username)){
        		map.put("type", RESULTFLAG.ERROR.getValue());
        		map.put("message", "用户名不能为空");
        		return map;
        	}else if(StringUtils.isEmpty(password)){
        		map.put("type", RESULTFLAG.ERROR.getValue());
        		map.put("message", "密码不能为空");
        		return map;
        	}
            try {  
            	Subject currentUser = SecurityUtils.getSubject();
            	if (!currentUser.isAuthenticated()){
            		UsernamePasswordToken token = new UsernamePasswordToken(username, MD5Util.encodeMD5Hex(password));  
            		token.setRememberMe(true);  
//                	currentUser.login(token);//验证角色和权限  
            		//在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
                    //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
                    //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
                    System.out.println("对用户[" + username + "]进行登录验证..验证开始");  
                    currentUser.login(token);  
                    System.out.println("对用户[" + username + "]进行登录验证..验证通过");  
            		map.put("type", RESULTFLAG.SUCCESS.getValue());
             		map.put("message", "登陆成功");
            	}else{
            		map.put("type", RESULTFLAG.SUCCESS.getValue());
             		map.put("message", "登陆成功");
            	} 
            }catch(UnknownAccountException uae){  
                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");  
                request.setAttribute("message_login", "未知账户");  
                map.put("type", RESULTFLAG.SUCCESS.getValue());
         		map.put("message", "未知账户");
            }catch(IncorrectCredentialsException ice){  
                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");  
                request.setAttribute("message_login", "密码不正确");  
                map.put("type", RESULTFLAG.SUCCESS.getValue());
         		map.put("message", "密码不正确");
            }catch(LockedAccountException lae){  
                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");  
                request.setAttribute("message_login", "账户已锁定");  
                map.put("type", RESULTFLAG.SUCCESS.getValue());
         		map.put("message", "账户已锁定");
            }catch(ExcessiveAttemptsException eae){  
                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");  
                request.setAttribute("message_login", "用户名或密码错误次数过多");  
                map.put("type", RESULTFLAG.SUCCESS.getValue());
         		map.put("message", "用户名或密码错误次数过多");
            }catch(AuthenticationException ae){  
                //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
                System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");  
                ae.printStackTrace();  
                request.setAttribute("message_login", "用户名或密码不正确");  
                map.put("type", RESULTFLAG.SUCCESS.getValue());
         		map.put("message", "用户名或密码不正确");
            }  
        }catch(Exception ex){
//            throw new BusinessException(LuoErrorCode.LOGIN_VERIFY_FAILURE);
        }
        return map;
    }  

    /** 
     * 退出登录
     */  
    @RequestMapping(value="logout",method=RequestMethod.POST)    
    public @ResponseBody Map<String,String> logout() {   
        Map<String, String> map = new HashMap<String, String>();
        try {
			Subject currentUser = SecurityUtils.getSubject();       
			currentUser.logout();
			map.put("type", RESULTFLAG.SUCCESS.getValue());
    		map.put("message", "登出成功");
		} catch (Exception e) {
			map.put("type", RESULTFLAG.ERROR.getValue());
    		map.put("message", "登出失败");
			e.printStackTrace();
		}    
        return map;
    }  
    
    private CaptchaService captchaService = null;
	
	public IndexController(){
		VaildCodeUtil util = new VaildCodeUtil();
		this.captchaService = util.getCaptchaService(25, 23, 5, 5, 100, 30);
	}
	
	@RequestMapping(value = "verificat", method = RequestMethod.GET)  
	public void validIndex(HttpServletRequest request,HttpServletResponse response){
		 //设置页面不缓存  
		response.setHeader("cache", "no-cache");
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
		response.setContentType("image/png");
		try {
			OutputStream out = response.getOutputStream();
			// 得到验证码对象，有验证码图片和验证码字符串
			Captcha captcha = captchaService.getCaptcha();
			//获取验证码字符串
			request.getSession().setAttribute("validCodeStr", captcha.getChallenge());
			//取得验证码图片并输出
			BufferedImage validCodeImg = captcha.getImage();
			ImageIO.write(validCodeImg, "png",out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
