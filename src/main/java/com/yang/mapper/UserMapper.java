package com.yang.mapper;

import com.yang.entity.User;
public interface UserMapper {
	User findUserByName(String name);
	User findUserById(Integer id);
}
