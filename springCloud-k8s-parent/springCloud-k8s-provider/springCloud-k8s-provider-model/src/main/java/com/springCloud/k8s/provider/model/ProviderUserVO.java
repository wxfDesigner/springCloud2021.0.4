package com.springCloud.k8s.provider.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 
 * @ClassName: ProviderUserVO
 * @Description: 提供者的用户信息VO对象
 * @author WangXf
 * @date 2023年3月1日 下午5:51:24
 *
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ToString
@ApiModel(value = "ProviderUserVO", description = "提供者的用户信息VO对象")
public class ProviderUserVO implements Serializable {

	/**
	 * @Fields serialVersionUID : 序列号
	 */
	private static final long serialVersionUID = -6491152517422212979L;

	@ApiModelProperty("用户id")
	private Long id;

	@ApiModelProperty("用户姓名")
	private String name;

	@ApiModelProperty("用户年龄")
	private Integer age;

	@ApiModelProperty("备注")
	private String remark;

}
