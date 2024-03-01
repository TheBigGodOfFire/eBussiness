package com.ch.ebusiness.controller.before;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class ValidateCodeController {
	/**
	 * 生成运算验证码
	 */
	@RequestMapping("/validateCode")
	public void validateCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 设置响应报头信息
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		// 设置响应的MIME类型
		response.setContentType("image/jpeg");
		// 验证码图片边框宽度
		final int WIDTH = 100;
		// 验证码图片边框高度
		final int HEIGHT = 38;
		// 验证码字体高度
		int FONT_HEIGHT = HEIGHT - 12;
		// 验证码干扰线条数
		int INTERFERENCE_LINE = 4;
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);
		Font mFont = new Font("Arial", Font.TRUETYPE_FONT, 18);
		Graphics g = image.getGraphics();
		Random rd = new Random();
		// 设置背景颜色
		g.setColor(new Color(rd.nextInt(55) + 200, rd.nextInt(55) + 200, rd
				.nextInt(55) + 200));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		// 设置字体
		g.setFont(mFont);
		// 画边框
		g.setColor(Color.black);
		g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);	
		// 验证码字符串
		String text = getText();
		// 运算表达式
		String operationExpression = text.substring(0, text.lastIndexOf("@") - 1);
		// 计算结果
		String result = text.substring(text.lastIndexOf("@") + 1, text.length());
		HttpSession se = request.getSession();
		se.setAttribute("rand", result);
		g.setColor(new Color(rd.nextInt(200), rd.nextInt(200), rd
				.nextInt(200)));
		// 根据画笔颜色绘制字符
		g.drawString(operationExpression, 5, FONT_HEIGHT);
		int r = 0;
        int gg = 0;
        int b = 0;
		// 绘制干扰线
		int x1, y1, x2, y2;
		for (int i = 0; i < INTERFERENCE_LINE; i++) {
			// 随机生成rgb颜色值，并设置画笔颜色
			r = rd.nextInt(255);
			gg = rd.nextInt(255);
			b = rd.nextInt(255);
			g.setColor(new Color(r, gg, b));
			x1 = rd.nextInt(WIDTH);
			y1 = rd.nextInt(HEIGHT);
			x2 = rd.nextInt(WIDTH);
			y2 = rd.nextInt(HEIGHT);
			// 绘制线条
			g.drawLine(x1, y1, x2, y2);
		}
		// 释放图形资源
		g.dispose();
		try {
			OutputStream os = response.getOutputStream();
			// 输出图像到页面
			ImageIO.write(image, "JPEG", os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取运算验证码
	 */
	public  String getText() {
		Random random = new Random(System.currentTimeMillis());
		StringBuilder result = new StringBuilder(); // 运算验证码结果
		int x = random.nextInt(51);
		int y = random.nextInt(51);
		int operationalRules = random.nextInt(4);
		switch (operationalRules) {
		case 0:
			result = add(x, y);
			break;
		case 1:
			result = subtract(x, y);
			break;
		case 2:
			result = multiply(x, y);
			break;
		case 3:
			result = divide(x, y);
			break;
		}
		return result.toString();
	}
	/**
	 * 加法运算
	 */
	private  StringBuilder add(int x, int y) {
		StringBuilder result = new StringBuilder(); // 运算验证码结果
		result.append(x);
		result.append(" + ");
		result.append(y);
		result.append(" = ?@");
		result.append(x + y);
		return result;
	}
	/**
	 * 减法运算
	 */
	private  StringBuilder subtract(int x, int y) {
		StringBuilder result = new StringBuilder(); // 运算验证码结果
		int max = Math.max(x, y);
		int min = Math.min(x, y);
		result.append(max);
		result.append(" - ");
		result.append(min);
		result.append(" = ?@");
		result.append(max - min);
		return result;
	}
	/**
	 * 乘法运算
	 */
	private  StringBuilder multiply(int x, int y) {
		StringBuilder result = new StringBuilder(); // 运算验证码结果
		int value = x * y;
		result.append(x);
		result.append(value > 100 ? " + " : " * ");
		result.append(y);
		result.append(" = ?@");
		result.append(value > 100 ? x + y : x * y);
		return result;
	}
	/**
	 * 除法运算
	 */
	private  StringBuilder divide(int x, int y) {
		StringBuilder result = new StringBuilder(); // 运算验证码结果
		int max = Math.max(x, y);
		int min = Math.min(x, y);
		if (min == 0) {
			multiply(max, min);
		} else if (max % min == 0) {
			result.append(max);
			result.append(" / ");
			result.append(min);
			result.append(" = ?@");
			result.append(max / min);
		} else {
			result.append(max);
			result.append(" % ");
			result.append(min);
			result.append(" = ?@");
			result.append(max % min);
		}
		return result;
	}
}