package com.baizhi.service;

import com.baizhi.entity.Manager;

public interface ManagerService {
	Manager login(String username);
	void register(Manager mg);
}
