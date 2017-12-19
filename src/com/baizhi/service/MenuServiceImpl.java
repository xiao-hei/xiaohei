package com.baizhi.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baizhi.dao.MenuDao;
import com.baizhi.entity.Menu;

@Service("menuService")
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class MenuServiceImpl implements MenuService {
	
	@Resource(name="menuDao")
	private MenuDao md;
	
	public List<Menu> findAll() {
		return md.queryAll();
	}

}
