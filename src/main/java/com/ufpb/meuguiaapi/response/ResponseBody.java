package com.ufpb.meuguiaapi.response;

import com.ufpb.meuguiaapi.exception.StandardError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBody<T> {
	private T data;
	private List<StandardError> errors;

	public ResponseBody(T data){
	    this.data = data;
    }
}
