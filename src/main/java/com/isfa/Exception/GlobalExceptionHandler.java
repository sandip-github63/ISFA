package com.isfa.Exception;


import java.io.IOException;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.isfa.clientadminpanel.leave.response.BaseResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<?> handleIOException(Exception ex){
		logger.info("GlobalExceptionHandler handling exception method executing");
		BaseResponse<?> resp = new BaseResponse<>();
		resp.setMessage("Sorry, An IO exception occured while processing the request: "+ ex);
		logger.info("\n\nSorry, An IO exception occured while processing the request: "+ ex+"\n\n");
		resp.setStatus("500");
		logger.info("GlobalExceptionHandler handling exception method completed");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<?> handleHTTPException(Exception ex){
		logger.info("GlobalExceptionHandler handling exception method executing");
		BaseResponse<?> resp = new BaseResponse<>();
		resp.setMessage("Sorry, Required request body is missing: ");
		logger.info("Sorry, Required request body is missing: ");
		resp.setStatus("500");
		logger.info("GlobalExceptionHandler handling exception method completed");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
	}
	
	
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<?> handleSQLException(Exception ex){
		logger.info("GlobalExceptionHandler handling exception method executing");
		BaseResponse<?> resp = new BaseResponse<>();
		resp.setMessage("Sorry, An SQL exception occured while processing the request: "+ ex);
		logger.info("Sorry, An SQL exception occured while processing the request: "+ ex+"\n\n");
		resp.setStatus("500");
		logger.info("GlobalExceptionHandler handling exception method completed");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Exception ex){
		logger.info("GlobalExceptionHandler handling exception method executing");
		BaseResponse<?> resp = new BaseResponse<>();
		resp.setMessage("Sorry, An error occured while processing the request: "+ ex);
		logger.info("Sorry, An error occured while processing the request: "+ ex+"\n\n");
		resp.setStatus("500");
		logger.info("GlobalExceptionHandler handling exception method completed");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
	}
	
}
