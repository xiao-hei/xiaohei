package com.baizhi.dao;

import com.baizhi.entity.Manager;

public interface ManagerDao {
	Manager queryByUserName(String username);
	void insertManager(Manager mg);
}
