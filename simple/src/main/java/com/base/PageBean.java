package com.base;

import com.github.pagehelper.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@ApiModel(value = "分页信息")
public class PageBean<T> implements Serializable {

	private static final long serialVersionUID = 4326537611829962152L;

	@ApiModelProperty(value = "总记录数")
	private long total; // 总记录数

	@ApiModelProperty(value = "结果集")
	private List<T> data; // 结果集

	@ApiModelProperty(value = "第几页")
	private int pageNum; // 第几页

	@ApiModelProperty(value = "每页记录数")
	private int pageSize; // 每页记录数

	@ApiModelProperty(value = "总页数")
	private int pages; // 总页数

	@ApiModelProperty(value = "当前页的数量")
	private int size; // 当前页的数量 <= pageSize，该属性来自ArrayList的size属性

	/**
	 * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理， 而出现一些问题。
	 * 
	 * @param list
	 *            page结果
	 * @param navigatePages
	 *            页码数量
	 */
	public PageBean(List<T> list) {
		if (list instanceof Page) {
			Page<T> page = (Page<T>) list;
			this.pageNum = page.getPageNum();
			this.pageSize = page.getPageSize();
			this.total = page.getTotal();
			this.pages = page.getPages();
			this.data = page;
			this.size = page.size();
		} else if (list instanceof Collection) {
			this.pageNum = 1;
			this.pageSize = list.size();
			this.pages = 1;
			this.data = list;
			this.size = list.size();
			this.total = list.size();
		}
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
