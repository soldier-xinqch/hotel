package org.hotel.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hotel.utils.VaildCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.bingoohuang.patchca.service.Captcha;
import com.github.bingoohuang.patchca.service.CaptchaService;

@Controller
@RequestMapping("/valid")
public class VerificationController{
	
	private CaptchaService captchaService = null;
	
	public VerificationController(){
		VaildCodeUtil util = new VaildCodeUtil();
		this.captchaService = util.getCaptchaService(25, 23, 5, 5, 100, 30);
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)  
	public void validIndex(HttpServletRequest request,HttpServletResponse response){
		 //设置页面不缓存  
		response.setHeader("cache", "no-cache");
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
		response.setContentType("image/png");
		HttpSession session = request.getSession(true);
		try {
			OutputStream out = response.getOutputStream();
			// 得到验证码对象，有验证码图片和验证码字符串
			Captcha captcha = captchaService.getCaptcha();
			//获取验证码字符串
			String validCodeStr = captcha.getChallenge();
			session.setAttribute("validCodeStr", validCodeStr);
			//取得验证码图片并输出
			BufferedImage validCodeImg = captcha.getImage();
			ImageIO.write(validCodeImg, "png",out);
			out.flush();
			out.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}