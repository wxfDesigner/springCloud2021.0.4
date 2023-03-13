package com.springCloud.k8s.consumer.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 
 * @ClassName: ConsumerUserVO  
 * @Description: 消费者的用户信息VO对象
 * @author WangXf  
 * @date 2023年3月1日 下午5:51:44  
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@ApiModel(value = "ConsumerUserVO", description = "消费者的用户信息VO对象")
public class ConsumerUserVO implements Serializable {
	
	/**  
	 * @Fields serialVersionUID : 序列号
	 */  
	private static final long serialVersionUID = -5437014373921726242L;

	@ApiModelProperty("用户id")
	private Long id;
	
	@ApiModelProperty("用户姓名")
	private String name;
	
	@ApiModelProperty("用户年龄")
	private Integer age;
	
	@ApiModelProperty("备注")
	private String remark;

}
