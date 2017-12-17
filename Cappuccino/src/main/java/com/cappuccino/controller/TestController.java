package com.cappuccino.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cappuccino.entity.SysUserEntity;
import com.cappuccino.service.SysUserService;
import com.cappuccino.util.ResultMessageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("user")
public class TestController {

	@Resource
	private SysUserService user_servcice;

	@ResponseBody
	@RequestMapping(value = "/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {

		boolean login = user_servcice.getUserInfo(username, password);
		if (login) {
			return "success";
		}
		return "error";
	}

	/**
	 * 
	 * @param page
	 *            页数
	 * @param page_number
	 *            每页显示条数
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/user_info")
	public ResultMessageUtil getUserInfo(@RequestParam("page") Integer page,
			@RequestParam("page_number") Integer page_number) {

		// http://localhost:8080/YUNWEI/user/user_info?page=2&page_number=2

		ResultMessageUtil result = new ResultMessageUtil();
		PageHelper.startPage(page, page_number);
		List<SysUserEntity> user_info = user_servcice.getAllUserInfo();
		PageInfo<SysUserEntity> pageInfo = new PageInfo<SysUserEntity>(user_info);
		result.setData(user_info);
		result.setStatus(1);
		System.out.println(pageInfo.getTotal() + "::::" + pageInfo.getPages());
		return result;

	}
}
