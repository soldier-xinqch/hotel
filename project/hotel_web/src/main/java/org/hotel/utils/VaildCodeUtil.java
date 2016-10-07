package org.hotel.utils;

import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.bingoohuang.patchca.background.MyCustomBackgroundFactory;
import com.github.bingoohuang.patchca.color.RandomColorFactory;
import com.github.bingoohuang.patchca.custom.ConfigurableCaptchaService;
import com.github.bingoohuang.patchca.filter.ConfigurableFilterFactory;
import com.github.bingoohuang.patchca.filter.library.AbstractImageOp;
import com.github.bingoohuang.patchca.filter.library.WobbleImageOp;
import com.github.bingoohuang.patchca.font.RandomFontFactory;
import com.github.bingoohuang.patchca.service.CaptchaService;
import com.github.bingoohuang.patchca.text.renderer.BestFitTextRenderer;
import com.github.bingoohuang.patchca.text.renderer.TextRenderer;
import com.github.bingoohuang.patchca.utils.encoder.EncoderHelper;
import com.github.bingoohuang.patchca.word.RandomWordFactory;


public class VaildCodeUtil {

	private static final String DEFAULT_CHARACTERS = "0123456789";
//			"123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"; // 自己设置！
	
	private ConfigurableCaptchaService captchaService = null;
	private RandomColorFactory colorFactory = null;
	private RandomFontFactory fontFactory = null;
	private RandomWordFactory wordFactory = null;
	private TextRenderer textRenderer = null;
	
	/**
	 * 验证码字体最大尺寸
	 */
	private int maxFontSize;

	/**
	 * 验证码字体最小尺寸
	 */
	private int minFontSize;
	/**
	 * 验证码字符最大长度
	 */
	private int maxCharsLength;
	/**
	 * 验证码字符最小长度
	 */
	private int mixCharsLength;
	/**
	 *  验证码图片宽度
	 */
	private int imageWidth;
	/**
	 *  验证码图片高度
	 */
	private int imageHeight;
	
	/**
	 *  初始化生成验证码服务
	 * @return
	 */
	private ConfigurableCaptchaService initVaildCode(){
		captchaService = new ConfigurableCaptchaService();
		/*
		 *  颜色创建工厂，使用一定范围内的随机色
		 *  若为了效率可以把常用颜色封装到一个集合中然后colorFactory通过集合获取到颜色 例如：
		 *  public ColorFactory nextColorFactory() {
		 *	int index = RANDOM.nextInt(colorList.size());
		 *	return (ColorFactory) colorList.get(index);}
		 */
		colorFactory = new RandomColorFactory();
		captchaService.setColorFactory(colorFactory);
		
		//随机字体生成器
		fontFactory = new RandomFontFactory();
		fontFactory.setMaxSize(maxFontSize);
		fontFactory.setMinSize(minFontSize);
		captchaService.setFontFactory(fontFactory);
		
		//随机字符生成器，去除容易混淆的字母和数字例如：o 和0
		wordFactory = new RandomWordFactory();
		wordFactory.setCharacters(DEFAULT_CHARACTERS);
		wordFactory.setMaxLength(maxCharsLength);
		wordFactory.setMinLength(mixCharsLength);
		captchaService.setWordFactory(wordFactory);
		
		//自定义验证码图片背景(最下边的内部类) 包里有这个类，也可以自己写一个类集成backgroundFactory就可以
		MyCustomBackgroundFactory customBgFactory = new MyCustomBackgroundFactory();
		captchaService.setBackgroundFactory(customBgFactory);
		
		//图片滤镜设置
		ConfigurableFilterFactory filterFactory = new ConfigurableFilterFactory();
		List<BufferedImageOp> filters = new ArrayList<BufferedImageOp>();
		WobbleImageOp wobbleImageOp = new WobbleImageOp();
		wobbleImageOp.setEdgeMode(AbstractImageOp.EDGE_MIRROR);
		wobbleImageOp.setxAmplitude(2.0);
		wobbleImageOp.setyAmplitude(1.0);
		filters.add(wobbleImageOp);
		filterFactory.setFilters(filters);
		captchaService.setFilterFactory(filterFactory);
		
		//文字渲染器设置
		textRenderer = new BestFitTextRenderer();
		textRenderer.setBottomMargin(3);
		textRenderer.setTopMargin(3);
		captchaService.setTextRenderer(textRenderer);
		
		//验证码图片的大小
		captchaService.setWidth(imageWidth);
		captchaService.setHeight(imageHeight);
		return captchaService;
	}
	/**
	 *  销毁验证码服务
	 */
	public void destroyConfigurableCaptchaService(){
		wordFactory = null;
		colorFactory = null;
		fontFactory = null;
		textRenderer = null;
		captchaService = null;
	}
	
//	public synchronized String getVaildCodeImage() throws FileNotFoundException, IOException{
//		// 得到验证码对象，有验证码图片和验证码字符串
//		Captcha captcha = captchaService.getCaptcha();
//		//获取验证码字符串
//		String validCodeStr = captcha.getChallenge();
//		//取得验证码图片并输出
//		BufferedImage validCodeImg = captcha.getImage();
//		ImageIO.write(validCodeImg, "png", new FileOutputStream(new File("mycustom.png")));
//		return "";
//	}
	
	public CaptchaService getCaptchaService(int maxFontSize,int minFontSize,int maxCharsLength,int mixCharsLength,int imageWidth,int imageHeight){
		this.maxFontSize = maxFontSize;
		this.minFontSize = minFontSize;
		this.maxCharsLength = maxCharsLength;
		this.mixCharsLength = mixCharsLength;
		this.imageWidth = imageWidth;
		this.imageHeight = imageHeight;
		if(captchaService == null){
			this.captchaService = initVaildCode();//初始化生成验证码的服务
		}
		return captchaService;
	}
	
	public static void main(String[] args) throws IOException {
		VaildCodeUtil util = new VaildCodeUtil();
		CaptchaService service = util.getCaptchaService(10, 10, 4, 4, 50, 20);
		for (int i = 0; i < 10; ++i) {
			FileOutputStream fos = null;
			try {
				String filePath = "D:/code";
				File file = new File(filePath);
				if(!file.exists()){
					file.mkdirs();
				}
				filePath = "D:/code/" +"none_" + i + ".png";
				fos = new FileOutputStream(filePath);
				String captcha = EncoderHelper.getChallangeAndWriteImage(
						service, "png", fos);
				System.out.println(captcha);
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (Exception e) {
					}
				}
			}
		}
	}


}
