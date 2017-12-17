package com.cappuccino.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.cappuccino.dao.SysUserDao;
import com.cappuccino.entity.SysUserEntity;
import com.cappuccino.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SqlSessionTemplate sqlsession;

	@Override
	public boolean getUserInfo(String user_name, String user_password) {
		SysUserDao mapper = sqlsession.getMapper(SysUserDao.class);
		int count = mapper.getUserInfo(user_name, user_password);
		if (1 == count) {
			return true;
		}
		return false;
	}

	@Override
	public List<SysUserEntity> getAllUserInfo() {
		SysUserDao mapper = sqlsession.getMapper(SysUserDao.class);
		return mapper.getAllUserInfo();
	}

}
