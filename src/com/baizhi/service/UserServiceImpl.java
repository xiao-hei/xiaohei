package com.baizhi.service;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;

@Service("userService")
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class UserServiceImpl implements UserService {

	@Resource(name="userDao")
	UserDao ud;
	
	public List<User> findAll() {
		return ud.selectAll();
	}
	
	@Transactional
	public void addUser(User u) {
		u.setId(UUID.randomUUID().toString());
		ud.insertUser(u);
	}

	
}
