package com.yang.service;

import com.yang.entity.User;

public interface UserService {
	User findUserByName(String name);
	User findUserById(Integer id);
}
