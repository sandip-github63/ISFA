package com.isfa.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.isfa.payload.response.JwtResponse;
import com.isfa.payload.response.MessageResponse;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponseAuth<T> {

    private String message;
    private String status;
    private T data;

    public BaseResponseAuth() {}

    public BaseResponseAuth(String message, String status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public BaseResponseAuth(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}


	
	

