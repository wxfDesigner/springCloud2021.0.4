package com.springCloud.k8s.common.configuration;

import java.net.BindException;

import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.springCloud.k8s.common.model.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import reactivefeign.client.ReadTimeoutException;

/**
 * 
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理器
 * @author WangXf
 * @date 2023年3月9日 下午2:27:37
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> bindException(BindException exception) {
		log.error(exception.getMessage(), exception);
		return ResponseEntity.fail().setBody("socket端口绑定异常！");
	}

	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
		log.error(exception.getMessage(), exception);
		return ResponseEntity.fail().setBody("方法参数无效异常！");
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> missingServletRequestParameterException(
			MissingServletRequestParameterException exception) {
		log.error(exception.getMessage(), exception);
		return ResponseEntity.fail().setBody("缺少Servlet请求参数异常！");
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> nullPointerException(NullPointerException exception) {
		log.error(exception.getMessage(), exception);
		return ResponseEntity.fail().setBody("空指针异常！");
	}

	@ExceptionHandler(NoFallbackAvailableException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> noFallbackAvailableException(NoFallbackAvailableException exception) {
		log.error("服务繁忙，请稍后再重试！{}",exception.getMessage());
		return ResponseEntity.fail().setBody("服务繁忙，请稍后再重试！");
	}
	
	@ExceptionHandler(ReadTimeoutException.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> readTimeoutException(ReadTimeoutException exception) {
		log.error("服务繁忙，请稍后再重试！{}",exception.getMessage());
		return ResponseEntity.fail().setBody("服务繁忙，请稍后再重试！");
	}
		

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Object> exception(Exception exception) {
		log.error(exception.getMessage(), exception);
		return ResponseEntity.fail().setBody(exception.getMessage());
	}
}
