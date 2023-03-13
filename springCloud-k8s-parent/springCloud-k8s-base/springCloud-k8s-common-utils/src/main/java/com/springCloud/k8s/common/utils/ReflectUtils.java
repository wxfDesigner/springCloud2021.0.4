package com.springCloud.k8s.common.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @ClassName: ReflectUtils  
 * @Description: 类反射工具类  
 * @author WangXf  
 * @date 2023年3月9日 下午4:29:15  
 *
 */
@Slf4j
public class ReflectUtils {

	/**
	 * 
	 * @Title: getSuperClassGenericType  
	 * @Description: 获取父类指定位置的泛型类型
	 * @param clazz
	 * @param index  
	 * @return Class<?>    返回类型  
	 * @throws
	 */
	public static Class<?> getSuperClassGenericType(Class<?> clazz, int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			log.warn(String.format("Warn: %s's superclass not ParameterizedType", clazz.getSimpleName()));
			return Object.class;
		}
		return indexOfGenericType(clazz, (ParameterizedType) genType, index);
	}

	/**
	 * 
	 * @Title: getInterfaceGenericType  
	 * @Description: 获取指定接口指定位置的泛型类型
	 * @param clazz
	 * @param target
	 * @param index  
	 * @return Class<?>    返回类型  
	 * @throws
	 */
	public static Class<?> getInterfaceGenericType(Class<?> clazz, Class<?> target, int index) {
		for (Type genericInterface : clazz.getGenericInterfaces()) {
			if (genericInterface instanceof ParameterizedType) {
				if (((ParameterizedType) genericInterface).getRawType() == target) {
					return indexOfGenericType(clazz, (ParameterizedType) genericInterface, index);
				}
			} else if (genericInterface == target) {
				log.warn(String.format("Warn: %s's interface not ParameterizedType", clazz.getSimpleName()));
				return Object.class;
			}
		}
		return Object.class;
	}

	/**
	 * 
	 * @Title: indexOfGenericType  
	 * @Description: 获取指定接口泛型类型指定位置的泛型类型
	 * @param clazz
	 * @param type
	 * @param index 
	 * @return Class<?>    返回类型  
	 * @throws
	 */
	public static Class<?> indexOfGenericType(Class<?> clazz, ParameterizedType type, int index) {
		Type[] params = type.getActualTypeArguments();
		if (index >= params.length || index < 0) {
			log.warn(String.format("Warn: Index: %s, Size of %s's Parameterized Type: %s .", index,
					clazz.getSimpleName(), params.length));
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			log.warn(String.format("Warn: %s not set the actual class on superclass generic parameter",
					clazz.getSimpleName()));
			return Object.class;
		}
		return (Class<?>) params[index];
	}
}
