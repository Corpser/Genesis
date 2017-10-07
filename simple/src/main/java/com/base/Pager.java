package com.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Pager {

	/**
	 * 数据集大小
	 */
	@ApiModelProperty(value = "数据集大小")
	private Integer rows;
	/**
	 * 页码
	 */
	@ApiModelProperty(value = "页码")
	private Integer page;

	/**
	 * 排序字段
	 */
	@ApiModelProperty(value = "排序字段")
	private String sortby;

	/**
	 * 排序类型
	 */
	@ApiModelProperty(value = "排序类型")
	private String order;

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSortby() {
		return sortby;
	}

	public void setSortby(String sortby) {
		this.sortby = sortby;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
}
