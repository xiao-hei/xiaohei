package com.baizhi.entity;

import java.util.List;

public class PictureDto {
	private List<Picture> rows;
	private int total;
	public List<Picture> getRows() {
		return rows;
	}
	public void setRows(List<Picture> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
