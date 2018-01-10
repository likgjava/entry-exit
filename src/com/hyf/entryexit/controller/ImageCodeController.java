package com.hyf.entryexit.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hyf.entryexit.util.ImageCodeUtil;

/**
 * 生成验证码
 * @author HuangYongFeng
 */
@Controller
public class ImageCodeController {
	/**
	 * 生成验证码
	 * @return
	 */
	@RequestMapping("/createImageCode.form")
	public void createImageCode(HttpSession session,HttpServletResponse response){
		try {
			//通过工具类获取验证码图片
			Map<String, BufferedImage> map = ImageCodeUtil.createImage();
			//获取图片里的验证码
			String code = map.keySet().iterator().next();
			//获取session,把图片里的验证码放到session里
			session.setAttribute("code", code);
			//获取验证码图片，并输出页面
			response.setContentType("image/jpeg");//设置消息头
			OutputStream os = response.getOutputStream();//获取输出流
			BufferedImage image = map.get(code);//获取图片
			ImageIO.write(image, "jpeg", os);//画验证码图片
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 验证验证码是否正确，正确返回true 否则返回false
	 * @param imageCode 页面传来的验证码
	 * @return boolean
	 */
	@RequestMapping("/validateCode.form")
	@ResponseBody
	public boolean validateCode(String imageCode,HttpSession session){
		String code = (String) session.getAttribute("code");
		if(code.equalsIgnoreCase(imageCode)){
			return true;
		}
		return false;
	}
	
	
}
