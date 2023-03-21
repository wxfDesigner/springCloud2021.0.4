/**  
 * All rights Reserved, Designed By 15821434334@163.com
 * @Title: HelloController.java   
 * @Package com.springCloud.k8s.gateway.app.controller   
 * @Description: 异常测试控制层 
 * @author: WangXf     
 * @date: 2023年3月21日 下午5:24:22   //2023/03/21 17:24:22
 * @version V1.0 
 * @Copyright: 2023 15821434334@163.com Inc. All rights reserved. 
 * 
 */
package com.springCloud.k8s.gateway.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**   
 * @ClassName: HelloController   
 * @Description: 异常测试控制层   
 * @author: WangXf 
 * @date: 2023年3月21日 下午5:24:22   
 *     
 * @Copyright: 2023 15821434334@163.com Inc. All rights reserved. 
 * 
 */
@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String hello() {
		throw new RuntimeException("hello exception !");
//		return "hello world";
	}

}
