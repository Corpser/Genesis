package com.base;

import java.io.Serializable;
import java.util.List;

public class BasePager<T> implements Serializable {
	
	private static final long serialVersionUID = 7417217309883878129L;

	// 总记录数
	private long total;
	
	// 结果集
	private List<T> rows;
	
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}