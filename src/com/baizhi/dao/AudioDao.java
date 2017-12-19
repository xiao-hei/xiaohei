package com.baizhi.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baizhi.entity.Audio;
import com.baizhi.entity.Special;

public interface AudioDao {
	void insertAudio(Audio s);
	List<Audio> selectAll(@Param("curpage") int curpage,@Param("rows") int rows);
	int selectCount();
	void deleteAudio(String id);
	void deletBySpecialId(String special_id);
	Audio selectById(String id);
	void updateAudio(Audio s);
}
