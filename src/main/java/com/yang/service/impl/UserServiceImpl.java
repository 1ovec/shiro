package com.yang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.entity.User;
import com.yang.mapper.UserMapper;
import com.yang.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUserByName(String name) {
		return userMapper.findUserByName(name);
	}

	@Override
	public User findUserById(Integer id) {
		return userMapper.findUserById(id);
	}

}
