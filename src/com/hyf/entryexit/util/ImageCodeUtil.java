package com.hyf.entryexit.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 生成验证码
 * @author HuangYongFeng
 */
public class ImageCodeUtil {
	/**随机抽取的验证码数组*/
//	private static final char[] chars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
//					  'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
//					  'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
//					  '9' };
	private static final char[] chars = { '1', '1', '1', '1'};
	/**图片的宽*/
	private static final int WIDTH = 80;
	/**图片的高*/
	private static final int HEIGHT = 45;
	/**验证码数量*/
	private static final int SIZE = 4;
	/**干扰线数量*/
	private static final int LINES  = 8;
	/** 字体大小*/
	private static final int FONT_SIZE  = 28;
	private static Random r = new Random();
	/**
	 * 生成随机验证码
	 * @return
	 */
	public static Map<String,BufferedImage> createImage(){
		//用于存储随机生成的验证码
		StringBuffer sb = new StringBuffer();
		//1、图片的内存映像
		BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		//1.1、获得画笔对象
		Graphics graphic = image.getGraphics(); 
		graphic.setColor(Color.white);//设置画笔颜色
		graphic.fillRect(0, 0, WIDTH, HEIGHT);//填冲
		//2、随机画字符，并放到StringBuffer里
		for (int i = 0; i < SIZE; i++) {
			int c = r.nextInt(chars.length);
			graphic.setColor(getRandomColor());//随机生成验证码
			graphic.setFont(new Font("宋体",Font.BOLD,FONT_SIZE));//设置字体
			graphic.drawString(chars[c] + "", i * WIDTH/SIZE, HEIGHT/2 + 5);//画字符
			sb.append(chars[c]);//将字符保存，存入session
		}
		//3、画干拢线
		for (int i = 0; i < LINES; i++) {
			graphic.setColor(getRandomColor());
			graphic.drawLine(r.nextInt(WIDTH), r.nextInt(HEIGHT), r.nextInt(WIDTH), r.nextInt(HEIGHT));
		}
		//4、把生成的验证码保存在map里
		Map<String,BufferedImage> map = new HashMap<String,BufferedImage>();
		map.put(sb.toString(), image);//key:生成的验证码 value:生成的图片
		return map;
	}
	/**
	 * 随机生成颜色
	 * @return
	 */
	public static Color getRandomColor(){
		return new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
	}
	/**
	 * 获取验证码
	 * @param image BufferedImage 图片对象
	 * @return 返回InputStream流
	 * @throws IOException
	 */
	public static InputStream getImageCode(BufferedImage image) throws IOException{
		//1、设置图片格式，压缩成jpeg格式
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(baos);
		//2、把BufferedImage对象中的图像信息编码后，向创建该对象(encoder)时指定的输出流输出
		encoder.encode(image);
		InputStream is = new ByteArrayInputStream(baos.toByteArray());
		return is;
	}

}
