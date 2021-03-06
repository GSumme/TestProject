package com.cappuccino.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
@RequestMapping("captcha")
public class CaptchaController {

	@Resource
	private Producer captchaProducer;

	@RequestMapping("getCaptchaCode")
	public ModelAndView getCaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();

		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");

		// 生成验证码文本
		String capText = captchaProducer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
		System.out.println("生成验证码文本====" + capText);
		// 利用生成的字符串构建图片
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);

		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}

	@RequestMapping(value = "checkCaptchaCode", produces = { "text/html;charset=UTF-8;", "application/json;" })
	@ResponseBody
	public String checkCaptchaCode(HttpServletRequest request, @RequestParam("captchaCode") String captchaCode) {
		System.out.println("页面输入验证码====" + captchaCode);
		String generateCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		String result = "";
		if (generateCode.equals(captchaCode)) {
			result = "验证成功";
		} else {
			result = "输入错误";
		}
		return result;

	}

}
