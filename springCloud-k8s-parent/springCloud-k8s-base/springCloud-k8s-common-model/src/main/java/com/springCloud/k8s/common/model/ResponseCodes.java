package com.springCloud.k8s.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 
 * @ClassName: ResponseCodes
 * @Description: 通用的响应代码信息枚举类
 * @author WangXf
 * @date 2023年3月7日 下午3:05:13
 *
 */
@Getter
@ApiModel(value = "ResponseCodes", description = "通用的响应代码信息枚举类")
public enum ResponseCodes {

	SUCCESS("0000", "成功"), ERROR("-0000", "失败");

	@ApiModelProperty("响应代码")
	private String code;
	@ApiModelProperty("响应信息")
	private String message;

	/**
	 * 
	 * <p>Title: ResponseCodes</p>  
	 * <p>Description: 通用的响应代码信息枚举类构造函数</p>  
	 * @param code 响应代码
	 * @param message 响应信息
	 */
	private ResponseCodes(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
