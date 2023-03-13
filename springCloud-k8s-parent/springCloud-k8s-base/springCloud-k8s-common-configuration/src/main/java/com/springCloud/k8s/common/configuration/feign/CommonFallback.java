package com.springCloud.k8s.common.configuration.feign;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.springCloud.k8s.common.utils.ReflectUtils;

import reactivefeign.FallbackFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @ClassName: CommonFallback
 * @Description: 通用的feign服务降级处理接口类
 * @author WangXf
 * @date 2023年3月9日 下午3:48:21
 * 
 * @param <T>
 */
public interface CommonFallback<T> extends FallbackFactory<T> {
	
	Log logger = LogFactory.getLog(CommonFallback.class);

	/**
	 * 服务降级处理
	 */
	@SuppressWarnings("unchecked")
	@Override
	default T apply(Throwable cause) {

		final Class<?> clazz = ReflectUtils.getInterfaceGenericType(this.getClass(), CommonFallback.class, 0);
		return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				logger.error("服务降级： " + clazz.getName() + " -> " + method.getName());
				if(method.getReturnType().isAssignableFrom(Flux.class)) {
					return Flux.empty();
				}else if(method.getReturnType().isAssignableFrom(Mono.class)) {
					return Mono.empty();
				}
				return method.getReturnType().newInstance();
			}
		});
	}
}
