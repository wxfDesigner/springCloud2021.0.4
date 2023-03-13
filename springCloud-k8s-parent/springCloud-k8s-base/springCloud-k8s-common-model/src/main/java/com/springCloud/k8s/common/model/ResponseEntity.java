package com.springCloud.k8s.common.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 
 * @ClassName: ResponseEntity
 * @Description: 通用的响应实体类
 * @author WangXf
 * @date 2023年3月7日 下午2:55:20
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@ApiModel(value = "ResponseEntity", description = "通用的响应实体类")
public class ResponseEntity<T> implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列号
	 */
	private static final long serialVersionUID = 8981118259322227754L;

	@ApiModelProperty("响应代码")
	private String code;
	@ApiModelProperty("响应信息")
	private String message;
	@ApiModelProperty("响应实体")
	private T body;
	
	/**
	 * 
	 * <p>Title: ResponseEntity</p>  
	 * <p>Description: 通用的响应实体类构造函数 </p>  
	 * @param code 响应代码
	 * @param message 响应信息
	 */
	public ResponseEntity(String code,String message) {
		this.code=code;
		this.message=message;
	}
	
	/**
	 * 
	 * @Title: ok  
	 * @Description: 通用无响应实体的成功响应信息
	 * @return ResponseEntity<Object>    返回类型  
	 * @throws
	 */
	public static final ResponseEntity<Object> ok() {
		return new ResponseEntity<Object>(ResponseCodes.SUCCESS.getCode(), ResponseCodes.SUCCESS.getMessage());
	}
	
	/**
	 * 
	 * @Title: ok  
	 * @Description: 通用有响应实体的成功响应信息
	 * @param body
	 * @return ResponseEntity<Object>    返回类型  
	 * @throws
	 */
	public static final ResponseEntity<Object> ok(Object body) {
		return new ResponseEntity<Object>(ResponseCodes.SUCCESS.getCode(), ResponseCodes.SUCCESS.getMessage(), body);
	}
	
	/**
	 * 
	 * @Title: fail  
	 * @Description: 通用的失败响应信息  
	 * @return ResponseEntity<Object>    返回类型  
	 * @throws
	 */
	public static final ResponseEntity<Object> fail() {
		return new ResponseEntity<Object>(ResponseCodes.ERROR.getCode(),ResponseCodes.ERROR.getMessage());
	}
	

}
