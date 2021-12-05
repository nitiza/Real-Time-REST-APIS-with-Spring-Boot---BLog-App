package com.springboot.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	
	private String resourceName;
	private String fieldName;
	private long fieldValue;
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
		
		super(String.format("%s not found with an %s : '%s'", resourceName, fieldName, fieldValue)); //Post not found with  an id : 1
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}


	public String getResourceName() {
		return resourceName;
	}


	public String getFieldName() {
		return fieldName;
	}


	public long getFieldValue() {
		return fieldValue;
	}
	
	
	
	
	
	

}
