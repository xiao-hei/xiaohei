package com.baizhi.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baizhi.dao.ManagerDao;
import com.baizhi.entity.Manager;

@Service("managerService")
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class ManagerServiceImpl implements ManagerService {
	
	@Resource(name="managerDao")
	private ManagerDao md;
	
	public Manager login(String username) {
		return md.queryByUserName(username);
	}
	@Transactional
	public void register(Manager mg) {
		md.insertManager(mg);
	}

}
