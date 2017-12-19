package com.baizhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baizhi.entity.Picture;

public interface PictureDao {
	void insertpicture(Picture p);
	List<Picture> selectAll(@Param("curpage") int curpage,@Param("rows") int rows);
	int selectCount();
	void deletePicture(String id);
	Picture selectById(String id);
	void updatePicture(Picture p);
}
