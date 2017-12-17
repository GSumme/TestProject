package com.cappuccino.service;

import java.util.List;

import com.cappuccino.entity.SysUserEntity;

public interface SysUserService {

	public boolean getUserInfo(String user_name, String user_password);

	public List<SysUserEntity> getAllUserInfo();

}
