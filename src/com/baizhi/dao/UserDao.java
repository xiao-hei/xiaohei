package com.baizhi.dao;

import java.util.List;

import com.baizhi.entity.User;

public interface UserDao {
	List<User> selectAll();
	void insertUser(User u);
}
