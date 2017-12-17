package com.cappuccino.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 校验是否是Ajax请求
 * 
 * @author feng.gao
 * @version v1
 * @date 2017年9月29日-下午2:43:00
 */
public class AjaxRequestCheck {

	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("x-requested-with");
		if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}

}
