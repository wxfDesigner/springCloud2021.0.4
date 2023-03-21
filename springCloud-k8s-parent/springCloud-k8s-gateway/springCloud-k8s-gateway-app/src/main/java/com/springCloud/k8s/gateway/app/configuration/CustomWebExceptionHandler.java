/**  
 * All rights Reserved, Designed By 15821434334@163.com
 * @Title: CustomWebExceptionHandler.java   
 * @Package com.springCloud.k8s.gateway.app.configuration   
 * @Description: 自定义异常处理  
 * @author: WangXf     
 * @date: 2023年3月21日 下午5:08:23   //2023/03/21 17:08:23
 * @version V1.0 
 * @Copyright: 2023 15821434334@163.com Inc. All rights reserved. 
 * 
 */
package com.springCloud.k8s.gateway.app.configuration;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import com.springCloud.k8s.common.model.ResponseEntity;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

/**
 * 
 * @ClassName: CustomWebExceptionHandler   
 * @Description: 自定义异常处理   
 * @author: WangXf 
 * @date: 2023年3月21日 下午5:08:23   
 *     
 * @Copyright: 2023 15821434334@163.com Inc. All rights reserved. 
 *
 */
@Slf4j
@Component
public class CustomWebExceptionHandler implements ErrorWebExceptionHandler {

	/**
	 * MessageReader
	 */
	private List<HttpMessageReader<?>> messageReaders = Collections.emptyList();

	/**
	 * MessageWriter
	 */
	private List<HttpMessageWriter<?>> messageWriters = Collections.emptyList();

	/**
	 * ViewResolvers
	 */
	private List<ViewResolver> viewResolvers = Collections.emptyList();

	/**
	 * 存储处理异常后的信息
	 */
	private ThreadLocal<ResponseEntity<Object>> exceptionHandlerResult = new ThreadLocal<>();

	/**
	 * 
	 * @Title: setMessageReaders  
	 * @Description: 参考AbstractErrorWebExceptionHandler
	 * @param @param messageReaders     
	 * @return void    返回类型  
	 * @throws
	 */
	public void setMessageReaders(List<HttpMessageReader<?>> messageReaders) {
		Assert.notNull(messageReaders, "'messageReaders' must not be null");
		this.messageReaders = messageReaders;
	}
	
	/**
	 * 
	 * @Title: setViewResolvers  
	 * @Description: 参考AbstractErrorWebExceptionHandler 
	 * @param viewResolvers      
	 * @return void    返回类型  
	 * @throws
	 */
	public void setViewResolvers(List<ViewResolver> viewResolvers) {
		this.viewResolvers = viewResolvers;
	}

	/**
	 * 
	 * @Title: setMessageWriters  
	 * @Description: 参考AbstractErrorWebExceptionHandler 
	 * @param messageWriters
	 * @return void    返回类型  
	 * @throws
	 */
	public void setMessageWriters(List<HttpMessageWriter<?>> messageWriters) {
		Assert.notNull(messageWriters, "'messageWriters' must not be null");
		this.messageWriters = messageWriters;
	}

	/**
	 * 
	 * <p>Title: handle</p>   
	 * <p>Description: 异常处理</p>   
	 * @param exchange
	 * @param ex
	 * @return   
	 * @see org.springframework.web.server.WebExceptionHandler#handle(org.springframework.web.server.ServerWebExchange, java.lang.Throwable)   
	 *
	 */
	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		// 按照异常类型进行处理
		HttpStatus httpStatus;
//		String body;
		if (ex instanceof ResponseStatusException) {
			ResponseStatusException responseStatusException = (ResponseStatusException) ex;
			httpStatus = responseStatusException.getStatus();
//			body = responseStatusException.getMessage();
		}
//        else if (ex instanceof BlockException) { // Sentinel Block 异常
//            httpStatus = HttpStatus.TOO_MANY_REQUESTS;
//            body = "服务器压力山大，请稍后再试！";
//        } 
		else {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//			body = httpStatus.name();
		}

		ServerHttpRequest request = exchange.getRequest();
		log.error("[全局异常处理] 异常请求路径:{}, 记录异常信息:{}", request.getPath(), ex.getMessage());

		// 参考AbstractErrorWebExceptionHandler
		if (exchange.getResponse().isCommitted()) {
			return Mono.error(ex);
		}

		ServerRequest newRequest = ServerRequest.create(exchange, this.messageReaders);
		exceptionHandlerResult.set(response(httpStatus.value(), this.buildMessage(newRequest, ex)));
		return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse).route(newRequest)
				.switchIfEmpty(Mono.error(ex)).flatMap((handler) -> handler.handle(newRequest))
				.flatMap((response) -> write(exchange, response));

	}

	/**
	 * 
	 * @Title: renderErrorResponse  
	 * @Description: 参考DefaultErrorWebExceptionHandler 在这里定义返回的是Json还是Html
	 * @param request 
	 * @return Mono<ServerResponse>    返回类型  
	 * @throws
	 */
	protected Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
		ResponseEntity<Object> result = exceptionHandlerResult.get();
		return ServerResponse.status(Integer.valueOf(result.getCode())).contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(result));
	}

	/**
	 * 
	 * @Title: write  
	 * @Description: 参考AbstractErrorWebExceptionHandler
	 * @param exchange
	 * @param response 
	 * @return Mono<? extends Void>    返回类型  
	 * @throws
	 */
	private Mono<? extends Void> write(ServerWebExchange exchange, ServerResponse response) {
		exchange.getResponse().getHeaders().setContentType(response.headers().getContentType());
		return response.writeTo(exchange, new ResponseContext());
	}
	
	/**
	 * 
	 * @ClassName: ResponseContext  
	 * @Description: 参考AbstractErrorWebExceptionHandler 
	 * @author WangXf  
	 * @date 2023年3月21日 下午4:32:27  
	 *
	 */
	private class ResponseContext implements ServerResponse.Context {

		@Override
		public List<HttpMessageWriter<?>> messageWriters() {
			return CustomWebExceptionHandler.this.messageWriters;
		}

		@Override
		public List<ViewResolver> viewResolvers() {
			return CustomWebExceptionHandler.this.viewResolvers;
		}
	}

	/**
	 * 
	 * @Title: buildMessage  
	 * @Description: 构建异常信息 
	 * @param request
	 * @param ex  
	 * @return String    返回类型  
	 * @throws
	 */
	private String buildMessage(ServerRequest request, Throwable ex) {
		StringBuilder message = new StringBuilder("Failed to handle request [");
		message.append(request.methodName());
		message.append(" ");
		message.append(request.uri());
		message.append("]");
		if (ex != null) {
			message.append(": ");
			message.append(ex.getMessage());
		}
		return message.toString();
	}

	/**
	 * 
	 * @Title: response  
	 * @Description: 构建返回的JSON数据格式
	 * @param status 状态码
	 * @param errorMessage 异常信息
	 * @return ResponseEntity<Object>    返回类型  
	 * @throws
	 */
	public ResponseEntity<Object> response(int status, String errorMessage) {
		return new ResponseEntity<Object>(status + "", errorMessage, null);
	}
}