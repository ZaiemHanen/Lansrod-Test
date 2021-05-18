package com.testapp.companymanagement.usefull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public final class Response<T>{
	
	private Response(ResponseTypes status , String message) {
		this.status = status;
		this.message = message;
	}
	
	private Response(ResponseTypes status , T data) {
		this.status = status;
		this.data = data;
	}
	
	
	private ResponseTypes status;
	private String message;
	private T data;
	

	public static Response of(ResponseTypes status, String message) {
		return new Response(status, message);
	}
	
	public static <T> Response<T> of(ResponseTypes status, T data){
		return new Response<T>(status, data);
	}
}
