package com.eCommerce.springboot.app.model;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseModelArray  extends ResponseModel{

	private List<String> arreglo;
	
	public ResponseModelArray(String message, HttpStatus httpStatus, List<String> arreglo) {
		super(message, httpStatus);
		this.arreglo = arreglo;
	}

	public List<String> getArreglo() {
		return arreglo;
	}

	public void setArreglo(List<String> arreglo) {
		this.arreglo = arreglo;
	}

	
	
}
