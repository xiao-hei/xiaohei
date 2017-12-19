package com.baizhi.service;

import java.util.List;

import com.baizhi.entity.User;

public interface UserService {
	List<User> findAll();
	void addUser(User u);
}
