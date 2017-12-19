package com.baizhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baizhi.entity.Picture;
import com.baizhi.entity.Special;

public interface SpecialDao {
	void insertSpecial(Special s);
	List<Special> selectAll(@Param("curpage") int curpage,@Param("rows") int rows);
	int selectCount();
	void deleteSpecial(String id);
	Special selectById(String id);
	void updateSpecial(Special s);
	List<Special> queryAll();
}
