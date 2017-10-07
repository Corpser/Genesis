package com.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 返回结果包装类
 * @author fengtao
 *
 */
@ApiModel
public class Message {
	
	@ApiModelProperty(value="代码")
	private String code;
	
	@ApiModelProperty(value="描述")
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
